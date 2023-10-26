@file:OptIn(ExperimentalMaterial3Api::class)
package com.numeramalite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun MainView() {
    var random1 by remember { mutableStateOf((0..10).random()) }
    var random2 by remember { mutableStateOf((0..10).random()) }
    var text by remember { mutableStateOf("") }
    var checkResult by remember { mutableStateOf("") }
    var question by remember { mutableStateOf(0) }
    var points by remember { mutableStateOf(0) }
    val sumRandom = random1 + random2
    Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(modifier = Modifier.padding(16.dp), text = "Rozwiąrz zadanie")
        Text(modifier = Modifier.padding(16.dp), text = "$random1 + $random2")

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ), keyboardActions = KeyboardActions(onDone = { val answer = text.toIntOrNull()

                if (answer != null) {

                    if (sumRandom == answer) {
                        checkResult = "Zgadza się, $random1 + $random2 = $sumRandom"
                        points++
                        question++
                    } else {
                        checkResult = "Złe rozwiązanie, $random1 + $random2 = $sumRandom"
                        question++
                    }
                    random1 = (0..10).random()
                    random2 = (0..10).random()
                } else {
                    checkResult = "Niepoprawny format liczby"
                    question++
                }
                text = ""})
        )

        Button(modifier = Modifier.padding(16.dp), onClick = {
            val answer = text.toIntOrNull()

            if (answer != null) {
                if (sumRandom == answer) {
                    checkResult = "Zgadza się, $random1 + $random2 = $sumRandom"
                    points++
                    question++
                } else {
                    checkResult = "Złe rozwiązanie, $random1 + $random2 = $sumRandom"
                    question++
                }
                random1 = (0..10).random()
                random2 = (0..10).random()
            } else {
                checkResult = "Niepoprawny format liczby"
                question++
            }
            text = ""
        }

            // End Button

        ) {
            Text(text = "Sprwadź")
        }
        Text(text = "Zadanie nr: $question")
        Text(text = "Punkty: $points")
        Text(text = checkResult)

    }
}


//fun CountThat(nr1: Int, nr2: Int, answer: Int, pts: Int, ques: Int): Int{
//    val sumRandom = nr1 + nr2
//    if (sumRandom == answer) {
//            return nr1; nr2; sumRandom; pts
//            pts++
//            ques++
//        } else {
//            checkResult = "Złe rozwiązanie, $nr1 + $nr2 = $sumRandom"
//            question++
//        }
//    return nr1+nr2
//}