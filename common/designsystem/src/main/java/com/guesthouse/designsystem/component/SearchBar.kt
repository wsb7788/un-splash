package com.guesthouse.designsystem.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guesthouse.designsystem.R
import com.guesthouse.designsystem.component.ComponentConstants.SEARCH_DELAY
import com.guesthouse.designsystem.theme.UnSplashTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UnSplashSearchBar(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardController: SoftwareKeyboardController?,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: @Composable (() -> Unit)? = null,
    hint: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {

    val searchText = remember { mutableStateOf("")}


    val keyboardAction = if(keyboardActions.onSearch != null){
        keyboardActions
    } else{
        KeyboardActions(onSearch = {
            keyboardController?.hide()
        })
    }

    val trailing =
        trailingIcon ?: {
            Icon(
                modifier = Modifier.clickable {
                  keyboardController?.hide()
                },
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(
                    R.string.search_icon
                )
            )
        }

    LaunchedEffect(key1 = searchText.value){
        delay(SEARCH_DELAY)
        if(searchText.value.isNotEmpty()){
            onValueChange(searchText.value)
        }
    }

    UnSplashTextField(
        modifier = modifier
            .height(46.dp)
            .fillMaxWidth(),
        value = searchText.value,
        hint = hint,
        onValueChange = {searchText.value = it},
        enabled = enabled,
        readOnly = readOnly,
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailing,
        prefix = prefix,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardAction,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource
    )
}

@Composable
@Preview
fun UnSplashSearchBarPreview(){
    UnSplashTheme {
    }
}