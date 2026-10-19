package br.edu.univali.coletaseletiva

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import br.edu.univali.coletaseletiva.di.AppContainer
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.navegacao.AppNavegacao
import br.edu.univali.coletaseletiva.navegacao.Navegador

/** Raiz do app (Android e iOS). Infraestrutura: o Integrante 2 troca o MaterialTheme pelo Tema do app (PB07). */
@Composable
fun App(container: AppContainer) {
    val navegador = remember { Navegador() }
    CompositionLocalProvider(LocalContainer provides container) {
        MaterialTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                AppNavegacao(navegador)
            }
        }
    }
}
