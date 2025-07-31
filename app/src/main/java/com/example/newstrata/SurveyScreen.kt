package com.example.newstrata

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SurveyScreen(navController: NavController) {
    var currentQuestionIndex by remember { mutableStateOf(0) }

    // List of questions and their options
    val questions = listOf(
        Question(
            question = "How often do you travel by airplane?",
            options = listOf(
                "Never",
                "Once a year or less",
                "2–5 times a year",
                "6–10 times a year",
                "More than 10 times a year"
            )
        ),
        Question(
            question = "What is your primary mode of daily transportation?",
            options = listOf(
                "Walking",
                "Biking",
                "Public transport (bus, train, metro)",
                "Car (petrol/diesel)",
                "Car (electric/hybrid)",
                "Carpooling or ride-sharing"
            )
        ),
        Question(
            question = "How often do you use ride-hailing services (e.g., Uber, Lyft)?",
            options = listOf(
                "Never",
                "Occasionally (1–3 times a month)",
                "Regularly (once a week or more)"
            )
        ),
        Question(
            question = "What type of diet do you follow?",
            options = listOf(
                "Vegan",
                "Vegetarian",
                "Pescatarian",
                "Omnivore (regular meat consumption)"
            )
        ),
        Question(
            question = "Do you participate in recycling programs?",
            options = listOf(
                "Yes",
                "No"
            )
        ),
        Question(
            question = "What type of house do you live in?",
            options = listOf(
                "Apartment/Flat",
                "Independent house/bungalow",
                "Shared accommodation (e.g., hostel, PG)"
            )
        ),
        Question(
            question = "How many rooms in your house have air conditioners?",
            options = listOf(
                "None",
                "1",
                "2–3",
                "More than 3"
            )
        )
    )

    val selectedOptions = remember { mutableStateListOf<String?>() }
    while (selectedOptions.size < questions.size) {
        selectedOptions.add(null)
    }

    // Current question
    val currentQuestion = questions[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Card(
            // Make sure this is imported correctly
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = currentQuestion.question,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Radio buttons for options
                currentQuestion.options.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = selectedOptions[currentQuestionIndex] == option,
                            onClick = { selectedOptions[currentQuestionIndex] = option }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = option, fontSize = 16.sp)
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp), // Add padding to lift the buttons
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (currentQuestionIndex > 0) {
                Button(onClick = { currentQuestionIndex-- }) {
                    Text(text = "Previous")
                }
            }
            Button(
                onClick = {
                    if (currentQuestionIndex < questions.size - 1) {
                        currentQuestionIndex++
                    } else {
                        navController.navigate("DashboardScreen") // Navigate when "Finish" is clicked
                    }
                },
                modifier = Modifier.width(120.dp) // Set a uniform width for the button
            ) {
                Text(
                    text = if (currentQuestionIndex < questions.size - 1) "Next" else "Finish"
                )
            }





        }
    }
}

data class Question(
    val question: String,
    val options: List<String>
)

/*fun handleSurveySubmission(selectedOptions: List<String?>) {
    val selectedData = selectedOptions.mapIndexed { index, answer ->
        "Question ${index + 1}: $answer"
    }
    println(selectedData)

    // Send selected options to the backend for processing
    val jsonBody = Gson().toJson(selectedOptions)

    // Example: Send a POST request to the backend
    val client = OkHttpClient()
    val requestBody = jsonBody.toRequestBody("application/json; charset=utf-8".toMediaType())
    val request = Request.Builder()
        .url("https://your-backend-api-endpoint/fetch-carbon-footprint")
        .post(requestBody)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val responseData = response.body?.string()
                println("Carbon Footprint Data: $responseData")
                // Handle response from RAG-based backend
            } else {
                println("Failed to fetch data: ${response.code}")
            }
        }
    })
}*/