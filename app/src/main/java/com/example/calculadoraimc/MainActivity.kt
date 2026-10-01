package com.example.calculadoraimc

import android.R.attr.fontWeight
import android.R.attr.text
import android.R.attr.y
import android.graphics.fonts.Font
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier) {

    var alturaInput by remember {
        mutableStateOf("")
    }

    var pesoInput by remember {
        mutableStateOf("")
    }

    var resultadoIMC by remember {
        mutableStateOf(0.0)
    }

    var resultadoStatus by remember {
        mutableStateOf("")
    }

    val corFundoPesoIdeal = Color(0xFF549D6F)
    val corFundoPesoAcima = Color(0xFFFF9100)
    val corFundoPesoCritico = Color(0xFFFF3D00)

    var corCardResultado by remember {
        mutableStateOf(Color(0xFFFDF7FE))
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // -- header --
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(id = R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "Logo APP",
                modifier = Modifier
                    .size(80.dp)
                    .padding(vertical = 16.dp)
            )
            Text(
                text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        // -- Form
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Seus Dados",
                        fontSize = 24.sp,
                        color = colorResource(R.color.cor_app),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    OutlinedTextField(
                        value = alturaInput,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        singleLine = true,
                        onValueChange = { novoValor ->
                            if (novoValor.length <= 3) {
                                alturaInput = novoValor.filter { it.isDigit() }
                            }
                            Log.i("text", novoValor)
                            alturaInput = novoValor
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        label = {
                            Text(
                                text = "Altura",
                                color = colorResource(R.color.cor_app)
                            )
                        },
                        shape = RoundedCornerShape(
                            10.dp
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            cursorColor = colorResource(R.color.cor_app),
                            focusedBorderColor = colorResource(R.color.cor_app),
                            unfocusedTextColor = colorResource(R.color.cor_app),
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = pesoInput,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        singleLine = true,
                        onValueChange = { novoValor ->
                            Log.i("text", novoValor)
                            pesoInput = novoValor
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        label = {
                            Text(
                                text = "Peso",
                                color = colorResource(R.color.cor_app)
                            )
                        },
                        shape = RoundedCornerShape(
                            10.dp
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            // Cor do cursor
                            cursorColor = colorResource(R.color.cor_app),

                            // Cores da borda
                            focusedBorderColor = colorResource(R.color.cor_app),
                            unfocusedBorderColor = colorResource(R.color.cor_app),

                            // Cores do label/rótulo
                            focusedLabelColor = colorResource(R.color.cor_app),
                            unfocusedLabelColor = colorResource(R.color.cor_app)
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            val peso = pesoInput.toDoubleOrNull() ?: 0.0
                            val altura = (alturaInput.toDoubleOrNull() ?: 0.0) / 100
                            resultadoIMC = peso / (altura * altura)

                            if (resultadoIMC <= 18.4) {
                                resultadoStatus = "Abaixo do peso"
                                corCardResultado = corFundoPesoCritico
                            } else if (resultadoIMC >= 18.5 && resultadoIMC < 25) {
                                resultadoStatus = "Peso Ideal"
                                corCardResultado = corFundoPesoIdeal
                            } else if (resultadoIMC >= 25 && resultadoIMC < 30) {
                                resultadoStatus = "Levemente acima do peso"
                                corCardResultado = corFundoPesoAcima
                            } else if (resultadoIMC >= 30 && resultadoIMC < 35) {
                                resultadoStatus = "Obesidade Grau I"
                                corCardResultado = corFundoPesoCritico
                            } else if (resultadoIMC >= 35 && resultadoIMC < 40) {
                                resultadoStatus = "Obesidade Grau II"
                                corCardResultado = corFundoPesoCritico
                            } else {
                                resultadoStatus = "Obesidade Grau III"
                                corCardResultado = corFundoPesoCritico
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(start = 10.dp, end = 10.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = colorResource(R.color.cor_app)
                        )
                    ) {
                        Text(
                            text = "CALCULAR", fontSize = 15.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            alturaInput = ""
                            pesoInput = ""
                            resultadoIMC = 0.0
                            resultadoStatus = ""
                            corCardResultado = corCardResultado
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(start = 10.dp, end = 10.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            containerColor = colorResource(R.color.teal_700)
                        )
                    ) {
                        Text(
                            text = "LIMPAR", fontSize = 15.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 32.dp),
                colors = CardDefaults.cardColors(
                    containerColor = corCardResultado
                ),
                elevation = CardDefaults.cardElevation(4.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(
                        15.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = String.format("%.2f", resultadoIMC),
                        color = Color(0xFFFDF7FE),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${resultadoStatus}",
                        color = Color(0xFFFDF7FE),
                        fontWeight = FontWeight.Bold
                    )

                }
            }
        }
    }
}


