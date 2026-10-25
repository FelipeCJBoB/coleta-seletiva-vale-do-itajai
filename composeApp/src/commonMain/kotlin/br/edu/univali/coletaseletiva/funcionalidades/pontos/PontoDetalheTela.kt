package br.edu.univali.coletaseletiva.funcionalidades.pontos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.navegacao.Navegador

@Composable
fun PontoDetalheRota(pontoId: Long, navegador: Navegador) {
    val container = LocalContainer.current
    val uriHandler = LocalUriHandler.current
    val vm = viewModel { PontoDetalheViewModel(pontoId, container.pontosRepositorio) }
    val estado by vm.estado.collectAsState()

    PontoDetalheTela(
        estado = estado,
        onAbrirRota = { url ->
            if (url.isNotEmpty()) {
                uriHandler.openUri(url)
            }
        },
        onVoltar = { navegador.voltar() },
    )
}

@Composable
fun PontoDetalheTela(
    estado: PontoDetalheEstado,
    onAbrirRota: (String) -> Unit,
    onVoltar: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Detalhes do ponto",
                style = MaterialTheme.typography.headlineMedium,
            )
            TextButton(onClick = onVoltar) {
                Text("Voltar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (estado.carregando) {
            Column(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
            return
        }

        if (estado.naoEncontrado || estado.ponto == null) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Ponto não encontrado",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.error,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onVoltar) {
                    Text("Voltar para a lista")
                }
            }
            return
        }

        val ponto = estado.ponto

        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = ponto.nome,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Endereço:",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = ponto.endereco,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Horário de funcionamento:",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = ponto.horario,
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Coordenadas:",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = "${ponto.latitude}, ${ponto.longitude}",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Materiais aceitos
        Text(
            text = "Materiais aceitos:",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(8.dp))

        ponto.materiais.forEach { aceito ->
            val texto = if (aceito.limite != null) {
                "• ${aceito.material.nomeExibicao} (${aceito.limite})"
            } else {
                "• ${aceito.material.nomeExibicao}"
            }
            Text(
                text = texto,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 2.dp),
            )
        }

        val aceitaEspeciais = ponto.materiais.any {
            it.material == Material.PILHAS_BATERIAS || it.material == Material.OLEO
        }

        if (aceitaEspeciais) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                ),
                modifier = Modifier.fillMaxWidth(),
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

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onAbrirRota(estado.urlDaRota) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Abrir rota")
        }
    }
}
