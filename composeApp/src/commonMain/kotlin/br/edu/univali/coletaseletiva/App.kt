package br.edu.univali.coletaseletiva

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.edu.univali.coletaseletiva.di.AppContainer
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.funcionalidades.inicio.Tema
import br.edu.univali.coletaseletiva.navegacao.AppNavegacao
import br.edu.univali.coletaseletiva.navegacao.Navegador

/** Raiz do app (Android e iOS). Infraestrutura: usa o Tema do app (PB07b). */
@Composable
fun App(container: AppContainer) {
    val navegador = remember { Navegador() }
    var pronto by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        container.iniciar()
        pronto = true
    }
    CompositionLocalProvider(LocalContainer provides container) {
        Tema {
            Surface(modifier = Modifier.fillMaxSize()) {
                if (pronto) AppNavegacao(navegador)
            }
        }
    }
}
