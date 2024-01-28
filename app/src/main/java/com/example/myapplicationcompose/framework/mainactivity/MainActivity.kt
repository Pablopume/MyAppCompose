package com.example.myapplicationcompose.framework.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationcompose.domain.modelo.Actor
import com.example.myapplicationcompose.ui.theme.MyApplicationComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApplicationComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScreen()

                }
            }
        }

    }


    @Composable
    fun MyScreen() {
        val textState = remember { mutableStateOf(TextFieldValue()) }
        val radioOptions = listOf("Masculino", "Femenino", "Otro")
        val selectedOption = remember { mutableStateOf(radioOptions[0]) }
        val checkBoxState = remember { mutableStateOf(false) }
        val sliderPosition = remember { mutableFloatStateOf(0f) }
        val famousMovie = remember { mutableStateOf(TextFieldValue()) }
        val buttonAnterior = remember { mutableStateOf(false) }
        val buttonSiguiente = remember { mutableStateOf(true) }
        val editmode = remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )

            {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Switch(
                        checked = editmode.value,
                        onCheckedChange = { isChecked ->
                            editmode.value = isChecked
                        },
                        thumbContent = {
                            if (editmode.value) {
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = null,
                                    modifier = Modifier.size(SwitchDefaults.IconSize),


                                    )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.Lock,
                                    contentDescription = null,
                                    modifier = Modifier.size(SwitchDefaults.IconSize),
                                )
                            }
                        }
                    )
                }
                if (editmode.value) {
                    OutlinedTextField(
                        value = textState.value,
                        onValueChange = { textState.value = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.width(228.dp)
                    )
                } else {
                    OutlinedTextField(
                        value = textState.value,
                        onValueChange = { textState.value = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.width(228.dp),
                        enabled = false
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 18.dp)
                        .height(50.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {

                    if (editmode.value) {
                        radioOptions.forEach { text ->
                            RadioButton(
                                selected = (text == selectedOption.value),
                                onClick = { selectedOption.value = text }
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.clickable { selectedOption.value = text }
                            )
                        }
                    } else {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()


                        ) {
                            Text(
                                text = selectedOption.value,
                                style = MaterialTheme.typography.bodyLarge

                            )
                        }
                    }


                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Premios Óscar",
                    fontSize = 24.sp,
                    modifier = Modifier.width(228.dp)


                )
                Spacer(modifier = Modifier.height(16.dp))
                if (editmode.value) {
                    Slider(
                        value = sliderPosition.floatValue,
                        onValueChange = { sliderPosition.floatValue = it },
                        valueRange = 0f..10f,
                        steps = 10,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                    )
                } else {
                    Slider(
                        enabled = false,
                        value = sliderPosition.floatValue,
                        onValueChange = { sliderPosition.floatValue = it },
                        valueRange = 0f..10f,
                        steps = 10,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    if (editmode.value) {
                        Checkbox(
                            checked = checkBoxState.value,
                            onCheckedChange = { checkBoxState.value = it }

                        )
                    } else {
                        Checkbox(
                            enabled = false,
                            checked = checkBoxState.value,
                            onCheckedChange = { checkBoxState.value = it }

                        )
                    }

                    Text(
                        text = "Está vivo",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.clickable {
                            checkBoxState.value = !checkBoxState.value
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                if (editmode.value) {
                    OutlinedTextField(
                        value = famousMovie.value,
                        onValueChange = { famousMovie.value = it },
                        label = { Text("Película famosa") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                    )
                } else {
                    OutlinedTextField(
                        enabled = false,
                        value = famousMovie.value,
                        onValueChange = { famousMovie.value = it },
                        label = { Text("Película famosa") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (editmode.value) {
                    Button(
                        onClick = {
                            viewModel.handleEvent(
                                MainEvent.UpdateActor(
                                    Actor(
                                        textState.value.text,
                                        checkBoxState.value,
                                        famousMovie.value.text,
                                        sliderPosition.floatValue.toInt(),
                                        selectedOption.value, viewModel.uiState.value.actor.id
                                    )
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(43.dp)
                    ) {
                        Text("Actualizar actor")
                    }
                } else {
                    Button(
                        onClick = {
                            viewModel.handleEvent(
                                MainEvent.UpdateActor(
                                    Actor(
                                        textState.value.text,
                                        checkBoxState.value,
                                        famousMovie.value.text,
                                        sliderPosition.floatValue.toInt(),
                                        selectedOption.value, viewModel.uiState.value.actor.id
                                    )
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(43.dp),
                        enabled = false
                    ) {
                        Text("Actualizar actor")
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(
                        onClick = { viewModel.handleEvent(MainEvent.GetAnterior) },
                        enabled = buttonAnterior.value
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Anterior")
                    }
                    if (editmode.value) {
                        IconButton(onClick = {
                            viewModel.handleEvent(
                                MainEvent.AddActor(
                                    Actor(
                                        textState.value.text,
                                        checkBoxState.value,
                                        famousMovie.value.text,
                                        sliderPosition.floatValue.toInt(),
                                        selectedOption.value
                                    )
                                )
                            )
                        }) {
                            Icon(Icons.Default.Add, contentDescription = "Añadir")
                        }
                    } else {
                        IconButton(onClick = {
                            viewModel.handleEvent(
                                MainEvent.AddActor(
                                    Actor(
                                        textState.value.text,
                                        checkBoxState.value,
                                        famousMovie.value.text,
                                        sliderPosition.floatValue.toInt(),
                                        selectedOption.value
                                    )
                                )
                            )
                        }, enabled = false) {
                            Icon(Icons.Default.Add, contentDescription = "Añadir")
                        }
                    }

                    if (editmode.value) {
                        IconButton(onClick = { viewModel.handleEvent(MainEvent.DeleteActor) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Basura")
                        }
                    } else {
                        IconButton(
                            onClick = { viewModel.handleEvent(MainEvent.DeleteActor) },
                            enabled = false
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Basura")
                        }
                    }

                    IconButton(
                        onClick = { viewModel.handleEvent(MainEvent.GetSiguiente) },
                        enabled = buttonSiguiente.value
                    ) {

                        Icon(Icons.Default.ArrowForward, contentDescription = "Siguiente")
                    }
                }

            }

        }
        ObservarViewModel(
            textState,
            checkBoxState,
            sliderPosition,
            famousMovie,
            selectedOption,
            buttonSiguiente,
            buttonAnterior
        )
    }

    @Composable
    private fun ObservarViewModel(
        textState: MutableState<TextFieldValue>,
        checkBoxState: MutableState<Boolean>,
        sliderPosition: MutableFloatState,
        famousMovie: MutableState<TextFieldValue>,
        selectedOption: MutableState<String>,
        buttonSiguiente: MutableState<Boolean>,
        buttonAnterior: MutableState<Boolean>
    ) {
        LaunchedEffect(viewModel) {
            viewModel.uiState.collect { uiState ->
                uiState.actor.let { actor ->
                    textState.value = TextFieldValue(actor.nombre)
                    checkBoxState.value = actor.vivo
                    sliderPosition.floatValue = actor.premiosOscar.toFloat()
                    famousMovie.value = TextFieldValue(actor.peliculaFamosa)
                    selectedOption.value = actor.genero

                }
                uiState.botonDerecha.let {
                    buttonSiguiente.value = it
                }

                uiState.botonIzquierda.let {
                    buttonAnterior.value = it
                }

            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MyApplicationComposeTheme {
            MyScreen()
        }
    }
}
