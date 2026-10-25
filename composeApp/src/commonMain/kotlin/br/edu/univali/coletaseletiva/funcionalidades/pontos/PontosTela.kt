package br.edu.univali.coletaseletiva.funcionalidades.pontos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.navegacao.Navegador
import br.edu.univali.coletaseletiva.navegacao.Rota

@Composable
fun PontosRota(navegador: Navegador) {
    val container = LocalContainer.current
    val vm = viewModel { PontosViewModel(container.localRepositorio, container.pontosRepositorio) }
    val estado by vm.estado.collectAsState()

    PontosTela(
        estado = estado,
        onFiltrar = vm::filtrarPor,
        onAbrirPonto = { id -> navegador.ir(Rota.PontoDetalhe(id)) },
        onVoltar = { navegador.voltar() },
    )
}

@Composable
fun PontosTela(
    estado: PontosEstado,
    onFiltrar: (Material?) -> Unit,
    onAbrirPonto: (Long) -> Unit,
    onVoltar: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Pontos de descarte",
                style = MaterialTheme.typography.headlineMedium,
            )
            TextButton(onClick = onVoltar) {
                Text("Voltar")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (estado.carregando) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
            return
        }

        if (estado.semLocal) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Nenhum município escolhido.",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onVoltar) {
                    Text("Escolher município na tela inicial")
                }
            }
            return
        }

        // Linha de chips com filtro por material
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            FilterChip(
                selected = estado.filtro == null,
                onClick = { onFiltrar(null) },
                label = { Text("Todos") },
            )
            Material.entries.forEach { material ->
                FilterChip(
                    selected = estado.filtro == material,
                    onClick = { onFiltrar(material) },
                    label = { Text(material.nomeExibicao) },
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Banner de aviso RN04 para Pilhas/Baterias ou Óleo
        if (estado.avisoRN04) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
            ) {
                Text(
                    text = "Pilhas, baterias e óleo de cozinha: leve apenas a pontos de descarte, nunca na coleta comum.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp),
                )
            }
        }

        if (estado.pontosFiltrados.isEmpty()) {
            Text(
                text = "Nenhum ponto de descarte encontrado para este filtro.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp),
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(estado.pontosFiltrados) { ponto ->
                    ItemPontoDeDescarte(ponto = ponto, onClick = { onAbrirPonto(ponto.id) })
                }
            }
        }
    }
}

@Composable
private fun ItemPontoDeDescarte(
    ponto: PontoDeDescarte,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = ponto.nome,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = ponto.endereco,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Horário: ${ponto.horario}",
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(4.dp))

            val materiasTexto = ponto.materiais.joinToString(", ") { aceito ->
                if (aceito.limite != null) {
                    "${aceito.material.nomeExibicao} (${aceito.limite})"
                } else {
                    aceito.material.nomeExibicao
                }
            }

            Text(
                text = "Aceita: $materiasTexto",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}
