package com.example.calculatordiana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatordiana.ui.theme.ComposeCalculatorTheme
import kotlin.math.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var input by remember { mutableStateOf("0") }
    var result by remember { mutableStateOf("0") }
    var currentOperation by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        // Display area
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = input,
                fontSize = 48.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = result,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        }

        // Buttons grid
        GridButtons(
            onButtonClick = { button ->
                when (button) {
                    "AC" -> {
                        input = "0"
                        result = "0"
                        currentOperation = ""
                    }
                    "C" -> {
                        input = input.dropLast(1).ifEmpty { "0" }
                    }
                    "=" -> {
                        calculateResult(input, currentOperation, result)?.let {
                            result = it
                            input = it
                            currentOperation = ""
                        }
                    }
                    "+", "-", "×", "÷", "^" -> {
                        if (input != "0") {
                            currentOperation = button
                            result = input
                            input = "0"
                        }
                    }
                    "√" -> {
                        input = sqrt(input.toDouble()).toString()
                    }
                    "x²" -> {
                        input = (input.toDouble().pow(2)).toString()
                    }
                    "%" -> {
                        input = (input.toDouble() / 100).toString()
                    }
                    "." -> {
                        if (!input.contains(".")) input += "."
                    }
                    else -> {
                        input = if (input == "0") button else input + button
                    }
                }
            }
        )
    }
}

@Composable
fun GridButtons(onButtonClick: (String) -> Unit) {
    val buttons = listOf(
        "AC", "C", "%", "÷",
        "√", "x²", "^", "×",
        "7", "8", "9", "-",
        "4", "5", "6", "+",
        "1", "2", "3", "=",
        "0", ".", "(", ")"
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        buttons.chunked(4).forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { button ->
                    val isZero = button == "0"
                    CalculatorButton(
                        text = button,
                        onClick = { onButtonClick(button) },
                        modifier = if (isZero) {
                            Modifier
                                .weight(2.2f)
                                .aspectRatio(2.2f)
                        } else {
                            Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isOperator = text in listOf("+", "-", "×", "÷", "=", "^")
    val isFunction = text in listOf("AC", "C", "%", "√", "x²")
    val isNumber = text in listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".")

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = when {
            isOperator -> MaterialTheme.colorScheme.primary
            isFunction -> MaterialTheme.colorScheme.secondary
            isNumber -> MaterialTheme.colorScheme.surfaceVariant
            else -> MaterialTheme.colorScheme.tertiaryContainer
        },
        contentColor = when {
            isOperator -> MaterialTheme.colorScheme.onPrimary
            isFunction -> MaterialTheme.colorScheme.onSecondary
            isNumber -> MaterialTheme.colorScheme.onSurfaceVariant
            else -> MaterialTheme.colorScheme.onTertiaryContainer
        }
    )

    Button(
        onClick = onClick,
        colors = buttonColors,
        shape = if (text == "=") RoundedCornerShape(12.dp) else CircleShape,
        modifier = modifier
            .then(
                if (text == "0") Modifier
                    .aspectRatio(2.2f)
                else Modifier
            ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 4.dp
        )
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = if (isOperator) FontWeight.Bold else FontWeight.Medium
        )
    }
}

private fun calculateResult(input: String, operation: String, currentResult: String): String? {
    return try {
        val num2 = input.toDouble()
        val num1 = currentResult.toDouble()

        when (operation) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "×" -> (num1 * num2).toString()
            "÷" -> if (num2 != 0.0) (num1 / num2).toString() else "Error"
            "^" -> num1.pow(num2).toString()
            else -> null
        }
    } catch (e: Exception) {
        "Error"
    }
}