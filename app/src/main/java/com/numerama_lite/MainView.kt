@file:OptIn(ExperimentalMaterial3Api::class)

package com.lite

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
import kotlin.random.Random


@Composable
fun MainView() {
    val randomStart = 2
    val randomEnd = 20

    var random1 by remember { mutableStateOf((randomStart..randomEnd).random()) }
    var random2 by remember { mutableStateOf((randomStart..randomEnd).random()) }

    var addSub by remember {
        mutableStateOf(Random.nextBoolean())
    }
    var text by remember { mutableStateOf("") }
    var checkResult by remember { mutableStateOf("") }
    var question by remember { mutableStateOf(0) }
    var points by remember { mutableStateOf(0) }
    var printAddSub: String = ""
    var sumRandom = 0

    if (addSub){
        printAddSub = "+"
    }
    else{
        printAddSub = "-"}

    fun resetButton(){
        points = 0
        question = 0
        random1 = (randomStart..randomEnd).random()
        random2 = (randomStart..randomEnd).random()
        addSub = Random.nextBoolean()
        text = ""
        checkResult = ""

    }

    fun verifyAnswer() {
        if (addSub){
            sumRandom = random1 + random2
            printAddSub = "+"
        }
        else{
            sumRandom = random1 - random2
            printAddSub = "-"}

        val answer = text.toIntOrNull()

        if (answer != null) {
            if (sumRandom == answer) {
                checkResult = "Zgadza się, $random1 $printAddSub $random2 = $sumRandom"
                points++
                question++
            } else {
                checkResult = "Złe rozwiązanie, $random1 $printAddSub $random2 = $sumRandom"
                question++
            }

        } else {
            checkResult = "Niepoprawny format liczby"
            question++
        }
        text = ""
        random1 = (randomStart..randomEnd).random()
        random2 = (randomStart..randomEnd).random()
        addSub = Random.nextBoolean()
    }


    Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(modifier = Modifier.padding(16.dp), text = "Rozwiąż zadanie")
        Text(modifier = Modifier.padding(16.dp), text = "$random1 $printAddSub $random2")

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ), keyboardActions = KeyboardActions(onDone = {
                verifyAnswer()
            })
        )

        Button(modifier = Modifier.padding(16.dp), onClick = {
            verifyAnswer() }
        ) {
            Text(text = "Sprwadź")
        }
        // End Button
        
        Text(text = "Zadanie nr: $question")
        Text(text = "Punkty: $points")
        Text(text = checkResult)

        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = { resetButton() }) {
            Text(text = "Od nowa")
        }

    }


}
