package br.edu.univali.coletaseletiva.funcionalidades.inicio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.navegacao.Navegador
import br.edu.univali.coletaseletiva.navegacao.Rota
import br.edu.univali.coletaseletiva.ui.Selo

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
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Topo(estado, onIr)

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Atalho("📅", "Calendário", Modifier.weight(1f)) { onIr(Rota.Calendario) }
                Atalho("📍", "Pontos de descarte", Modifier.weight(1f)) { onIr(Rota.Pontos) }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Atalho("📖", "Guia de separação", Modifier.weight(1f)) { onIr(Rota.Guia) }
                Atalho("⭐", "Meus pontos", Modifier.weight(1f)) { onIr(Rota.MeusPontos) }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Atalho("💡", "Dicas", Modifier.weight(1f)) { onIr(Rota.Dicas) }
                Atalho("🔔", "Lembrete da coleta", Modifier.weight(1f)) { onIr(Rota.Lembrete) }
            }

            Text(
                text = "Dados de demonstração",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            )
        }
    }
}

/** Faixa verde com gradiente: nome do app e o local escolhido (ou o convite para escolher). */
@Composable
private fun Topo(estado: InicioEstado, onIr: (Rota) -> Unit) {
    val escuro = MaterialTheme.colorScheme.primary
    val claro = Color(0xFF39A866)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.linearGradient(listOf(escuro, claro)))
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(start = 20.dp, end = 12.dp, top = 20.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Selo(emoji = "♻️", tamanho = 48.dp, fundo = Color.White.copy(alpha = 0.22f))
            Text(
                text = "Coleta Seletiva Vale",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }

        val local = estado.local
        if (local != null) {
            Text(
                text = local,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            TextButton(
                onClick = { onIr(Rota.EscolherLocal) },
                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary),
            ) { Text("Trocar local") }
        } else if (estado.precisaEscolherLocal) {
            Text(
                text = "Escolha onde você mora para ver as informações do seu bairro.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(end = 8.dp),
            )
            Button(
                onClick = { onIr(Rota.EscolherLocal) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
            ) { Text("Escolher local") }
        }
    }
}

/** Cartão de atalho: selo com emoji e o nome da funcionalidade. */
@Composable
private fun Atalho(emoji: String, texto: String, modifier: Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.height(116.dp).clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Selo(emoji = emoji, tamanho = 48.dp)
            Text(
                text = texto,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp),
            )
        }
    }
}
