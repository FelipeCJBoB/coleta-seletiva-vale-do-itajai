package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import br.edu.univali.coletaseletiva.navegacao.Navegador
import br.edu.univali.coletaseletiva.navegacao.Rota
import kotlinx.datetime.LocalDate

// ===================== Meus pontos (saldo e histórico, RF10) =====================

@Composable
fun MeusPontosRota(navegador: Navegador) {
    val container = LocalContainer.current
    val vm = viewModel { MeusPontosViewModel(container.descarteRepositorio) }
    val estado by vm.estado.collectAsState()

    MeusPontosTela(
        estado = estado,
        onRegistrar = { navegador.ir(Rota.RegistrarDescarte) },
        onVoltar = { navegador.voltar() },
    )
}

@Composable
fun MeusPontosTela(
    estado: MeusPontosEstado,
    onRegistrar: () -> Unit,
    onVoltar: () -> Unit,
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Cabecalho("Meus pontos", onVoltar)

        if (estado.carregando) {
            Carregando()
            return@Column
        }

        Text(
            text = "Saldo: ${estado.saldo} pontos",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Registro autodeclarado: no máximo $LIMITE_DIARIO por dia.",
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = onRegistrar, modifier = Modifier.fillMaxWidth()) {
            Text("Registrar descarte")
        }
        Spacer(Modifier.height(16.dp))

        Text("Histórico", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        if (estado.historico.isEmpty()) {
            Text("Nenhum descarte registrado ainda.", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(estado.historico, key = { it.id }) { descarte ->
                    Card(Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column {
                                Text(descarte.material.nomeExibicao, style = MaterialTheme.typography.titleMedium)
                                Text(formatarData(descarte.data), style = MaterialTheme.typography.bodyMedium)
                            }
                            Text(
                                text = "+${descarte.pontos}",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                }
            }
        }
    }
}

// ===================== Registrar descarte (RF09) =====================

@Composable
fun RegistrarDescarteRota(navegador: Navegador) {
    val container = LocalContainer.current
    val vm = viewModel { RegistrarDescarteViewModel(container.registrarDescarte) }
    val estado by vm.estado.collectAsState()

    RegistrarDescarteTela(
        estado = estado,
        onRegistrar = vm::registrar,
        onVoltar = { navegador.voltar() },
    )
}

@Composable
fun RegistrarDescarteTela(
    estado: RegistrarEstado,
    onRegistrar: (Material) -> Unit,
    onVoltar: () -> Unit,
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Cabecalho("Registrar descarte", onVoltar)

        Text("Qual material você descartou?", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(12.dp))

        estado.mensagem?.let { mensagem ->
            Card(Modifier.fillMaxWidth()) {
                Text(
                    text = mensagem,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp),
                )
            }
            Spacer(Modifier.height(12.dp))
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(estado.materiaisDisponiveis, key = { it.name }) { material ->
                OutlinedButton(
                    onClick = { onRegistrar(material) },
                    enabled = !estado.registrando,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(material.nomeExibicao)
                }
            }
        }
    }
}

// ===================== Peças comuns =====================

/** dd/MM/aaaa (formato brasileiro). */
internal fun formatarData(data: LocalDate): String {
    val dia = data.day.toString().padStart(2, '0')
    val mes = (data.month.ordinal + 1).toString().padStart(2, '0')
    return "$dia/$mes/${data.year}"
}

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
