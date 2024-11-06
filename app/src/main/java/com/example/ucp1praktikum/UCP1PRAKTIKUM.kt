package com.example.ucp1praktikum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp1praktikum.ui.theme.UCP1PRAKTIKUMTheme
import com.example.ucp1praktikum.R

class UCP1PRAKTIKUM : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UCP1PRAKTIKUMTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AdventurePlannerApp()
                }
            }
        }
    }
}

@Composable
fun AdventurePlannerApp() {
    var formState by remember { mutableStateOf(FormState()) }
    var savedForm by remember { mutableStateOf(FormState()) }
    val transportationOptions = listOf("Bus", "Ship", "Train", "Plane")
    var selectedTransport by remember { mutableStateOf(transportationOptions[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HeaderSection()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Plan Your Adventures",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Let's plan an exquisite adventure",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Origin Text Field
        TextField(
            value = formState.nama,
            onValueChange = { formState = formState.copy(nama = it) },
            label = { Text(text = "Origin") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Departure and Arrival Date Text Fields
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = formState.departure,
                onValueChange = { formState = formState.copy(departure = it) },
                label = { Text("Departure") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )
            TextField(
                value = formState.arrival,
                onValueChange = { formState = formState.copy(arrival = it) },
                label = { Text("Arrival") },
                singleLine = true,
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Choose Transportation",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        // Transportation Options
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            transportationOptions.forEach { option ->
                RadioButton(
                    selected = selectedTransport == option,
                    onClick = { selectedTransport = option },
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Blue)
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 4.dp),
                    color = if (selectedTransport == option) Color.Blue else Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Submit Button
        Button(
            onClick = {
                savedForm = formState.copy(transport = selectedTransport)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF004A8D))
        ) {
            Text(text = "Submit", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Travel Details
        if (savedForm.nama.isNotEmpty()) {
            TravelDetailsSection(savedForm)
        }
    }
}

@Composable
fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF004A8D))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.kingno),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Text(
            text = "Elwins Pranata",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Daerah Istimewa Yogyakarta",
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Composable
fun TravelDetailsSection(form: FormState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF004A8D))
            .padding(16.dp)
    ) {
        DetailMessage(param = "Origin", argum = form.nama)
        DetailMessage(param = "Departure", argum = form.departure)
        DetailMessage(param = "Arrival", argum = form.arrival)
        DetailMessage(param = "Transport", argum = form.transport)
    }
}

@Composable
fun DetailMessage(param: String, argum: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$param :", color = Color.White, fontWeight = FontWeight.Bold)
        Text(text = argum, color = Color.White)
    }
}

// Define the FormState data class
data class FormState(
    var nama: String = "",
    var departure: String = "",
    var arrival: String = "",
    var transport: String = ""
)

@Preview(showBackground = true)
@Composable
fun PreviewAdventurePlannerApp() {
    UCP1PRAKTIKUMTheme {
        AdventurePlannerApp()
    }
}
