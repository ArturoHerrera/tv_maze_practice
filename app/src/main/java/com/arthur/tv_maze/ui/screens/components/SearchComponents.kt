package com.arthur.tv_maze.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchBar(
    onWriteQuery: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onFocusClear: () -> Unit,
    hideKeyboard: Boolean = false
) {

    var query by remember { mutableStateOf("") }

    var isHintDisplayed by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isHintDisplayed = it.hasFocus != true
                },
            value = query,
            onValueChange = {
                query = it
                //onWriteQuery(query)
            },
            label = { Text(text = "Buscar...", color = Color.White) },
            placeholder = { Text("") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                onWriteQuery(query)
            }),
            maxLines = 1,
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    focusManager.clearFocus()
                    onWriteQuery(query)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Botón para buscar",
                        tint = Color.White
                    )
                }
            }
        )
    }

    if (hideKeyboard) {
        focusManager.clearFocus()
        // Call onFocusClear to reset hideKeyboard state to false
        onFocusClear()
    }
}