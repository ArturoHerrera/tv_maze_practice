package com.arthur.tv_maze.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.arthur.tv_maze.ui.theme.MazeGreen
import com.arthur.tv_maze.utils.DateUtils

@Composable
fun TopBarComponent(
    onSearchClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = DateUtils.getCurrentDateLarge(),
            color = Color.White
        )
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Botón para buscar",
            tint = Color.White,
            modifier = Modifier.clickable { onSearchClicked() }
        )
    }
}

@Composable
fun SearchBar(
    onWriteQuery: (String) -> Unit,
    onFocusClear: () -> Unit,
    onBack: () -> Unit,
    hideKeyboard: Boolean = false
) {
    var query by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester = focusRequester)
                .onFocusChanged { isHintDisplayed = it.hasFocus != true },
            value = query,
            onValueChange = { query = it },
            label = {
                Text(
                    text = "Buscar",
                    color = Color.Black
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = MazeGreen,
                textColor = MazeGreen,
                disabledLabelColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = { Text("") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                onWriteQuery(query)
            }),
            maxLines = 1,
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = {
                    if (query.isNotEmpty()) {
                        query = ""
                    } else {
                        focusManager.clearFocus()
                        onBack()
                    }
                }) {
                    if (query.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Botón para regresar",
                            tint = Color.Black
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Botón para regresar",
                            tint = Color.Black
                        )
                    }
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    onWriteQuery(query)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Botón para buscar",
                        tint = Color.Black
                    )
                }
            }
        )
    }

    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}