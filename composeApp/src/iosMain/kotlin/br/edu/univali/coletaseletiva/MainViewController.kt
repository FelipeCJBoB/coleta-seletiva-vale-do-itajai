package br.edu.univali.coletaseletiva

import androidx.compose.ui.window.ComposeUIViewController
import br.edu.univali.coletaseletiva.dados.DriverFactory
import br.edu.univali.coletaseletiva.di.AppContainer

@Suppress("FunctionName")
fun MainViewController() = ComposeUIViewController { App(AppContainer(DriverFactory())) }
