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
import br.edu.univali.coletaseletiva.ui.BarraDeTopo
import br.edu.univali.coletaseletiva.ui.Selo
import br.edu.univali.coletaseletiva.ui.iconeDoMaterial

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
    Column(Modifier.fillMaxSize()) {
    BarraDeTopo("Detalhes do ponto", onVoltar)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
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
            colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Selo(emoji = "📍", tamanho = 52.dp)
                Spacer(modifier = Modifier.height(8.dp))
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
            Row(
                modifier = Modifier.padding(vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Selo(emoji = iconeDoMaterial(aceito.material), tamanho = 32.dp)
                Text(
                    text = texto,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }

        val aceitaEspeciais = ponto.materiais.any {
            it.material == Material.PILHAS_BATERIAS || it.material == Material.OLEO
        }

        if (aceitaEspeciais) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                ),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Pilhas, baterias e óleo de cozinha: leve apenas a pontos de descarte, nunca na coleta comum.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
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
}
