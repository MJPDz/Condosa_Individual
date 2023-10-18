package com.example.mvvm_condosa.ui.caja_chica.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvm_condosa.R
import com.example.mvvm_condosa.data.GastosCasaSource.gastosCasa
import com.example.mvvm_condosa.data.GastosMesAnteriorSource
import com.example.mvvm_condosa.model.GastosCasa

@Preview
@Composable
fun GastosScreen() {
    val gradient = Brush.linearGradient(
        0.0f to Color(0xFF0052D6),
        1000.0f to Color(0xFF00183F),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Box(
        modifier = Modifier
            .background(gradient)
            .fillMaxSize()
    ) {
        Gastos()
    }
}

@Composable
fun Gastos() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderTitle_GastosCasa()
        Spacer(modifier = Modifier.padding(12.dp))
        InfoGastoTotal()
        Spacer(modifier = Modifier.padding(12.dp))
        ListaGastos_Casa()
    }
}

@Composable
fun InfoGastoTotal() {
    var consumido = 0
    gastosCasa.forEach { gastosCasa ->
        consumido += gastosCasa.monto
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Gasto Total:",
            fontSize = 16.sp,
            color = Color.White
        )
        Text(
            text = "S/ ${consumido}.00",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD1C484),
            modifier = Modifier.align(Alignment.Bottom)
        )
    }
}

@Composable
fun HeaderTitle_GastosCasa() {
    Text(
        text = "Gastos de caja chica",
        color = Color.White,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        lineHeight = 36.sp
    )
}

@Composable
fun ListaGastos_Casa() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(gastosCasa) {
                item -> ListGastosRow(item)
        }
    }
}

@Composable
fun ListGastosRow(item: GastosCasa) {
    val gradient = Brush.linearGradient(
        0.0f to Color(0xFF4B7DCE),
        1000.0f to Color(0xFF001941),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(R.dimen.padding_medium))
    ){
        Box(modifier = Modifier
            .background(gradient)
            .padding(15.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
            ) {
                Column {
                    Text(
                        text = item.fecha,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = item.nombre,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Text(
                        text = item.producto,
                        fontSize = 16.sp,
                        color = Color(0xFFD1C484)
                    )
                }
                Text(
                    text = "S/ ${item.monto}.00",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD1C484)
                )
            }
        }
    }
}
