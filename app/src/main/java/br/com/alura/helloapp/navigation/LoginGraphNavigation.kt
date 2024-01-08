package br.com.alura.helloapp.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.alura.helloapp.DestinosHelloApp
import br.com.alura.helloapp.preferences.dataStore
import br.com.alura.helloapp.ui.login.FormularioLoginTela
import br.com.alura.helloapp.ui.login.FormularioLoginViewModel
import br.com.alura.helloapp.ui.login.LoginTela
import br.com.alura.helloapp.ui.login.LoginViewModel
import br.com.alura.helloapp.ui.navegaLimpo
import kotlinx.coroutines.launch

fun NavGraphBuilder.loginGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = DestinosHelloApp.Login.rota,
        route = DestinosHelloApp.LoginGraph.rota
    ) {
        composable(
            route = DestinosHelloApp.Login.rota,
        ) {
            val viewModel = hiltViewModel<LoginViewModel>()
            val state by viewModel.uiState.collectAsState()

            if (state.logado) {

                LaunchedEffect(Unit) {
//                    navController.navegaLimpo(DestinosHelloApp.HomeGraph.rota)
                }
            }

            val dataStore = LocalContext.current.dataStore
            val rememberCoroutineScope = rememberCoroutineScope()

            LoginTela(
                state = state,
                onClickLogar = {
                    rememberCoroutineScope.launch {
                        dataStore.edit { preferences ->
                            preferences[booleanPreferencesKey("isLogged")] = true
                        }
                        viewModel.tentaLogar()
                    }
                },
                onClickCriarLogin = {
                    navController.navigate(DestinosHelloApp.FormularioLogin.rota)
                }
            )

            LaunchedEffect(Unit) {
                rememberCoroutineScope.launch {
                    dataStore.data.collect {
                        val isLogged = it[booleanPreferencesKey("isLogged")]

                        if (isLogged == true) {
                            navController.navegaLimpo(DestinosHelloApp.HomeGraph.rota);
                        }

                    }
                }
            }
        }

        composable(
            route = DestinosHelloApp.FormularioLogin.rota,
        ) {
            val viewModel = hiltViewModel<FormularioLoginViewModel>()
            val state by viewModel.uiState.collectAsState()

            FormularioLoginTela(
                state = state,
                onSalvar = {
                    navController.navegaLimpo(DestinosHelloApp.Login.rota)
                }
            )
        }
    }
}