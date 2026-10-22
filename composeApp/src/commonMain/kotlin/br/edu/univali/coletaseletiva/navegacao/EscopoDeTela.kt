package br.edu.univali.coletaseletiva.navegacao

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner

/**
 * Dá a cada TELA o seu próprio "armário" de ViewModels. Quando a tela sai da navegação o armário é
 * esvaziado; ao voltar, o ViewModel nasce de novo e carrega os dados atualizados (sem estado velho).
 * Infraestrutura (Integrante 1): quem faz tela só usa `viewModel { ... }` normalmente.
 */
@Composable
fun EscopoDeTela(chave: Any, conteudo: @Composable () -> Unit) {
    val armario = remember(chave) { ViewModelStore() }
    val dono = remember(armario) {
        object : ViewModelStoreOwner {
            override val viewModelStore: ViewModelStore = armario
        }
    }
    DisposableEffect(armario) {
        onDispose { armario.clear() }
    }
    CompositionLocalProvider(LocalViewModelStoreOwner provides dono) {
        conteudo()
    }
}
