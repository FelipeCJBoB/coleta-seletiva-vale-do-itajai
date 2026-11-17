package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import br.edu.univali.coletaseletiva.navegacao.Navegador
import br.edu.univali.coletaseletiva.navegacao.Rota
import br.edu.univali.coletaseletiva.ui.BarraDeTopo
import br.edu.univali.coletaseletiva.ui.EstadoVazio
import br.edu.univali.coletaseletiva.ui.Selo
import br.edu.univali.coletaseletiva.ui.TituloDeSecao
import br.edu.univali.coletaseletiva.ui.iconeDoMaterial
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
    Column(Modifier.fillMaxSize()) {
        BarraDeTopo("Meus pontos", onVoltar)

        if (estado.carregando) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            return@Column
        }

        Column(Modifier.fillMaxSize().padding(16.dp)) {
            // Cartão de saldo em destaque.
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(listOf(MaterialTheme.colorScheme.primary, Color(0xFF39A866))),
                        RoundedCornerShape(22.dp),
                    )
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Selo("⭐", tamanho = 48.dp, fundo = Color.White.copy(alpha = 0.25f))
                    Text(
                        text = "Saldo: ${estado.saldo} pontos",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
                Text(
                    text = "Registro autodeclarado: no máximo $LIMITE_DIARIO por dia.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Spacer(Modifier.height(6.dp))
                Button(
                    onClick = onRegistrar,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text("Registrar descarte")
                }
            }
            Spacer(Modifier.height(20.dp))

            TituloDeSecao("Histórico")
            Spacer(Modifier.height(8.dp))
            if (estado.historico.isEmpty()) {
                EstadoVazio("🌱", "Nenhum descarte registrado ainda.")
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(estado.historico, key = { it.id }) { descarte ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(12.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Selo(iconeDoMaterial(descarte.material))
                                Column(Modifier.weight(1f)) {
                                    Text(descarte.material.nomeExibicao, style = MaterialTheme.typography.titleMedium)
                                    Text(formatarData(descarte.data), style = MaterialTheme.typography.bodyMedium)
                                }
                                Text(
                                    text = "+${descarte.pontos}",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
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
    Column(Modifier.fillMaxSize()) {
        BarraDeTopo("Registrar descarte", onVoltar)

        Column(Modifier.fillMaxSize().padding(16.dp)) {
            Text("Qual material você descartou?", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(12.dp))

            estado.mensagem?.let { mensagem ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Selo("✅", tamanho = 36.dp, fundo = Color.White.copy(alpha = 0.7f))
                        Text(
                            text = mensagem,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(estado.materiaisDisponiveis, key = { it.name }) { material ->
                    OutlinedButton(
                        onClick = { onRegistrar(material) },
                        enabled = !estado.registrando,
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            Selo(iconeDoMaterial(material), tamanho = 36.dp)
                            Text(material.nomeExibicao, style = MaterialTheme.typography.titleMedium)
                        }
                    }
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
