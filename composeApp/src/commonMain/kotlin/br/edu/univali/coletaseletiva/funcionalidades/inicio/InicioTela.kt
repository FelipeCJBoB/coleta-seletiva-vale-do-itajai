package br.edu.univali.coletaseletiva.funcionalidades.inicio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.navegacao.Navegador
import br.edu.univali.coletaseletiva.navegacao.Rota

/** Liga a tela ao ViewModel e à navegação. */
@Composable
fun InicioRota(navegador: Navegador) {
    val container = LocalContainer.current
    val vm = viewModel { InicioViewModel(container.localRepositorio) }
    val estado by vm.estado.collectAsState()
    InicioTela(estado = estado, onIr = { navegador.ir(it) })
}

/** Tela Início: o local escolhido e os atalhos (RNF01: cada funcionalidade com 1 toque). */
@Composable
fun InicioTela(estado: InicioEstado, onIr: (Rota) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Coleta Seletiva Vale", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)

        val local = estado.local
        if (local != null) {
            Text(local, style = MaterialTheme.typography.titleLarge)
            TextButton(onClick = { onIr(Rota.EscolherLocal) }) { Text("Trocar local") }
        } else if (estado.precisaEscolherLocal) {
            Text("Escolha onde você mora para ver as informações do seu bairro.", style = MaterialTheme.typography.bodyLarge)
            Button(onClick = { onIr(Rota.EscolherLocal) }, modifier = Modifier.fillMaxWidth()) { Text("Escolher local") }
        }

        Spacer(Modifier.height(4.dp))
        Atalho("Calendário") { onIr(Rota.Calendario) }
        Atalho("Pontos de descarte") { onIr(Rota.Pontos) }
        Atalho("Guia de separação") { onIr(Rota.Guia) }
        Atalho("Meus pontos") { onIr(Rota.MeusPontos) }
        Atalho("Dicas") { onIr(Rota.Dicas) }

        Spacer(Modifier.height(8.dp))
        Text(
            "Dados de demonstração",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
        )
    }
}

@Composable
private fun Atalho(texto: String, onClick: () -> Unit) {
    OutlinedButton(onClick = onClick, modifier = Modifier.fillMaxWidth()) { Text(texto) }
}
