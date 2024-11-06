package com.example.UCP1PRAKTIKUM

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class FormState(
    var nama: String = "",
    var alamat: String = "",
)

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Gray)
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Daerah Istimewa Yogyakarta",
                    color = Color.White
                )
                Text(
                    text = "FAX : 021-121212, TLP : 08745678",
                    color = Color.White
                )
            }
            Box(
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.king),
                    contentDescription = "",
                    Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Image(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UCP1PRAKTIKUM(modifier: Modifier = Modifier) {
    var formState by remember { mutableStateOf(FormState()) }
    val listJK = listOf("Bus", "Ship", "Train", "Plane")
    var savedForm by remember { mutableStateOf(FormState()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Plan Your Adventures",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        TextField(
            value = formState.nama,
            onValueChange = { formState = formState.copy(nama = it) },
            label = { Text(text = "Nama") },
            placeholder = { Text(text = "Isi nama anda") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )

        TextField(
            value = formState.alamat,
            onValueChange = { formState = formState.copy(alamat = it) },
            label = { Text(text = "Alamat") },
            placeholder = { Text(text = "Isi alamat anda") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )


        Button(onClick = { savedForm = formState }) {
            Text(text = "submit")
        }

        DetailMessage(param = "Origin", argum = savedForm.nama)
        DetailMessage(param = "Depanture", argum = savedForm.alamat)
    }
}

@Composable
fun DetailMessage(param: String, argum: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = param, modifier = Modifier.weight(0.8f))
            Text(text = ":", modifier = Modifier.weight(0.2f))
            Text(text = argum, modifier = Modifier.weight(2f))
        }
    }
}
