package br.edu.univali.coletaseletiva.funcionalidades.guia

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.navegacao.Navegador
import br.edu.univali.coletaseletiva.navegacao.Rota
import br.edu.univali.coletaseletiva.ui.BarraDeTopo
import br.edu.univali.coletaseletiva.ui.EstadoVazio
import br.edu.univali.coletaseletiva.ui.Selo

/** Tag do campo de busca, usada no teste de interface. */
const val TAG_CAMPO_BUSCA = "guia_campo_busca"

// ===================== Lista com busca =====================

@Composable
fun GuiaRota(navegador: Navegador) {
    val container = LocalContainer.current
    val vm = viewModel { GuiaViewModel(container.guiaRepositorio) }
    val estado by vm.estado.collectAsState()

    GuiaTela(
        estado = estado,
        onDigitar = vm::digitar,
        onAbrir = { id -> navegador.ir(Rota.GuiaDetalhe(id)) },
        onVoltar = { navegador.voltar() },
    )
}

@Composable
fun GuiaTela(
    estado: GuiaEstado,
    onDigitar: (String) -> Unit,
    onAbrir: (Long) -> Unit,
    onVoltar: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        BarraDeTopo("Guia de separação", onVoltar)

        Column(Modifier.fillMaxSize().padding(16.dp)) {
            OutlinedTextField(
                value = estado.termo,
                onValueChange = onDigitar,
                label = { Text("Buscar no guia") },
                placeholder = { Text("Ex.: caixa de leite") },
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth().testTag(TAG_CAMPO_BUSCA),
            )
            Spacer(Modifier.height(12.dp))

            when {
                estado.carregando -> Carregando()
                estado.nadaEncontrado -> EstadoVazio("🔎", "Nada encontrado")
                else -> LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(estado.itens, key = { it.id }) { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth().clickable { onAbrir(item.id) },
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                            ) {
                                Selo(emoji = emojiDoDestino(item.destino), fundo = corDoDestino(item.destino))
                                Column {
                                    Text(item.nome, style = MaterialTheme.typography.titleMedium)
                                    Text(
                                        text = item.destino.nomeExibicao,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.primary,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// ===================== Detalhe do item =====================

@Composable
fun GuiaDetalheRota(itemId: Long, navegador: Navegador) {
    val container = LocalContainer.current
    val vm = viewModel { GuiaDetalheViewModel(itemId, container.guiaRepositorio) }
    val estado by vm.estado.collectAsState()

    GuiaDetalheTela(
        estado = estado,
        onVerPontos = { navegador.ir(Rota.Pontos) },
        onVoltar = { navegador.voltar() },
    )
}

@Composable
fun GuiaDetalheTela(
    estado: GuiaDetalheEstado,
    onVerPontos: () -> Unit,
    onVoltar: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        BarraDeTopo("Guia de separação", onVoltar)

        Column(Modifier.fillMaxSize().padding(16.dp)) {
            val item = estado.item
            when {
                estado.carregando -> Carregando()
                item == null -> EstadoVazio("🔎", "Item não encontrado")
                else -> {
                    Text(
                        text = item.nome,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(Modifier.height(12.dp))

                    // Destino em destaque, com a cor do tipo de destino.
                    Card(
                        colors = CardDefaults.cardColors(containerColor = corDoDestino(item.destino)),
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(14.dp),
                        ) {
                            Selo(emoji = emojiDoDestino(item.destino), tamanho = 52.dp, fundo = Color.White.copy(alpha = 0.7f))
                            Column {
                                Text(
                                    text = "Destino",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                                Text(
                                    text = item.destino.nomeExibicao,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))

                    Text(item.orientacao, style = MaterialTheme.typography.bodyLarge)

                    if (item.destino == Destino.PONTO_DE_DESCARTE) {
                        Spacer(Modifier.height(24.dp))
                        Button(onClick = onVerPontos, modifier = Modifier.fillMaxWidth()) {
                            Text("Ver pontos de descarte")
                        }
                    }
                }
            }
        }
    }
}

// ===================== Peças comuns =====================

/** Emoji de cada destino (só decoração). */
private fun emojiDoDestino(destino: Destino): String = when (destino) {
    Destino.COLETA_SELETIVA -> "♻️"
    Destino.COLETA_COMUM -> "🗑️"
    Destino.PONTO_DE_DESCARTE -> "📍"
    Destino.ORIENTACAO_GERAL -> "ℹ️"
}

/** Cor de fundo de cada destino: verde, cinza, âmbar e azul-acinzentado. */
@Composable
private fun corDoDestino(destino: Destino): Color = when (destino) {
    Destino.COLETA_SELETIVA -> MaterialTheme.colorScheme.primaryContainer
    Destino.COLETA_COMUM -> MaterialTheme.colorScheme.surfaceVariant
    Destino.PONTO_DE_DESCARTE -> MaterialTheme.colorScheme.tertiaryContainer
    Destino.ORIENTACAO_GERAL -> Color(0xFFDDE8F2)
}

@Composable
private fun Carregando() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
