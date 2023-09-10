package com.mungai_codes.hngstageone.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mungai_codes.hngstageone.R
import com.mungai_codes.hngstageone.presentation.theme.HngStageOneTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(navController: NavController, viewModel: MainScreenViewModel = viewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                UiEvent.NavigateToGithubScreen -> {
                    navController.navigate("github_screen")
                }
            }
        }
    }
    MainScreenContent(onEvent = viewModel::onEvent)
}

@Composable
fun MainScreenContent(onEvent: (MainScreenEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfilePicture()
        Spacer(modifier = Modifier.height(12.dp))
        PersonalInfo()
        Spacer(modifier = Modifier.height(12.dp))
        GithubButton(onClick = { onEvent(MainScreenEvent.OnGithubButtonClicked) })
    }
}

@Composable
private fun ProfilePicture(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(150.dp)
            .clip(MaterialTheme.shapes.medium)
            .border(2.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
    ) {
        Image(
            painter = painterResource(id = R.drawable.slack_profile_pic),
            contentDescription = "profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun PersonalInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "name:",
            fontFamily = FontFamily(Font(R.font.raleway_regular)),
            fontSize = 12.sp
        )
        Text(
            text = stringResource(R.string.user_name),
            fontFamily = FontFamily(Font(R.font.raleway_black))
        )
        Text(
            text = "title:",
            fontFamily = FontFamily(Font(R.font.raleway_regular)),
            fontSize = 12.sp
        )
        Text(
            text = stringResource(R.string.user_title),
            fontFamily = FontFamily(Font(R.font.raleway_black))
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    AssistChip(
        onClick = onClick,
        label = {
            Text(
                text = stringResource(R.string.github_button_label),
                fontFamily = FontFamily(Font(R.font.raleway_regular))
            )
        },
        modifier = modifier,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_github),
                contentDescription = "github logo",
                modifier = Modifier.size(18.dp)
            )
        },
        shape = MaterialTheme.shapes.medium
    )
}


@Preview
@Composable
fun PreviewMainScreen() {
    HngStageOneTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            MainScreenContent(onEvent = {})
        }
    }
}


