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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.navegacao.Navegador
import br.edu.univali.coletaseletiva.navegacao.Rota

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
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Cabecalho("Guia de separação", onVoltar)

        OutlinedTextField(
            value = estado.termo,
            onValueChange = onDigitar,
            label = { Text("Buscar no guia") },
            placeholder = { Text("Ex.: caixa de leite") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().testTag(TAG_CAMPO_BUSCA),
        )
        Spacer(Modifier.height(12.dp))

        when {
            estado.carregando -> Carregando()
            estado.nadaEncontrado -> Text(
                text = "Nada encontrado",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp),
            )
            else -> LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(estado.itens, key = { it.id }) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable { onAbrir(item.id) },
                    ) {
                        Column(Modifier.padding(12.dp)) {
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
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Cabecalho("Guia de separação", onVoltar)

        val item = estado.item
        when {
            estado.carregando -> Carregando()
            item == null -> Text("Item não encontrado", style = MaterialTheme.typography.bodyLarge)
            else -> {
                Text(
                    text = item.nome,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(12.dp))

                // Destino em destaque.
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(
                            text = "Destino",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                        Text(
                            text = item.destino.nomeExibicao,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
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

// ===================== Peças comuns =====================

@Composable
private fun Cabecalho(titulo: String, onVoltar: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(titulo, style = MaterialTheme.typography.headlineMedium)
        TextButton(onClick = onVoltar) { Text("Voltar") }
    }
    Spacer(Modifier.height(16.dp))
}

@Composable
private fun Carregando() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
