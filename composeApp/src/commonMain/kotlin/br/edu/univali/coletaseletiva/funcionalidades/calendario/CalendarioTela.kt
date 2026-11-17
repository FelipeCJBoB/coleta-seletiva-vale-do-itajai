package br.edu.univali.coletaseletiva.funcionalidades.calendario

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.navegacao.Navegador
import br.edu.univali.coletaseletiva.navegacao.Rota
import br.edu.univali.coletaseletiva.ui.BarraDeTopo
import br.edu.univali.coletaseletiva.ui.EstadoVazio
import br.edu.univali.coletaseletiva.ui.Selo
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate

@Composable
fun CalendarioRota(navegador: Navegador) {
    val container = LocalContainer.current
    val vm = viewModel {
        CalendarioViewModel(container.localRepositorio, container.calendarioRepositorio, container.relogio)
    }
    val estado by vm.estado.collectAsState()

    CalendarioTela(
        estado = estado,
        onVoltar = { navegador.voltar() },
        onEscolherLocal = { navegador.ir(Rota.EscolherLocal) },
    )
}

/** Calendário de coleta do setor escolhido: dias da semana e próxima data de cada tipo (RF03). */
@Composable
fun CalendarioTela(
    estado: CalendarioEstado,
    onVoltar: () -> Unit,
    onEscolherLocal: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        BarraDeTopo("Calendário", onVoltar)

        when {
            estado.carregando -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

            estado.semLocal -> Column(Modifier.fillMaxSize().padding(16.dp)) {
                EstadoVazio("📍", "Escolha seu local")
                Text(
                    "Para ver os dias de coleta, escolha primeiro o seu município e setor.",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(Modifier.height(16.dp))
                Button(onClick = onEscolherLocal, modifier = Modifier.fillMaxWidth()) {
                    Text("Escolher local")
                }
            }

            else -> Column(
                Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Selo("📍", tamanho = 36.dp)
                    Text(estado.local, style = MaterialTheme.typography.titleMedium)
                }
                estado.itens.forEach { item -> CartaoDeColeta(item) }
                Text(
                    text = "Não há horário fixo de passagem do caminhão.",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = "Feriados nacionais: a coleta passa para o próximo dia útil.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

/** Um tipo de coleta: seletiva em verde, comum em cinza-esverdeado, com a próxima data em destaque. */
@Composable
private fun CartaoDeColeta(item: ItemCalendario) {
    val seletiva = item.tipo == TipoColeta.SELETIVA
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (seletiva) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.secondaryContainer,
        ),
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Selo(if (seletiva) "♻️" else "🗑️", tamanho = 48.dp, fundo = Color.White.copy(alpha = 0.7f))
                Column {
                    Text(
                        text = item.tipo.nomeExibicao,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = item.diasDaSemana.joinToString(", ") { nomeDoDia(it) },
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
            Surface(color = Color.White, shape = MaterialTheme.shapes.medium, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Próxima coleta: ${formatarData(item.proximaData)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                )
            }
        }
    }
}

private fun nomeDoDia(dia: DayOfWeek): String = when (dia) {
    DayOfWeek.MONDAY -> "Segunda-feira"
    DayOfWeek.TUESDAY -> "Terça-feira"
    DayOfWeek.WEDNESDAY -> "Quarta-feira"
    DayOfWeek.THURSDAY -> "Quinta-feira"
    DayOfWeek.FRIDAY -> "Sexta-feira"
    DayOfWeek.SATURDAY -> "Sábado"
    DayOfWeek.SUNDAY -> "Domingo"
}

/** dd/MM/aaaa (formato brasileiro). */
private fun formatarData(data: LocalDate): String {
    val dia = data.day.toString().padStart(2, '0')
    val mes = (data.month.ordinal + 1).toString().padStart(2, '0')
    return "$dia/$mes/${data.year}"
}
