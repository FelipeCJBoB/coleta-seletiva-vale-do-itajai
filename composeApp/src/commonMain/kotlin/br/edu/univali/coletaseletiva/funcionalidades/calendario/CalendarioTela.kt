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
import androidx.compose.material3.CircularProgressIndicator
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
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Calendário", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = onVoltar) { Text("Voltar") }
        }
        Spacer(Modifier.height(16.dp))

        when {
            estado.carregando -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

            estado.semLocal -> {
                Text("Escolha seu local", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Para ver os dias de coleta, escolha primeiro o seu município e setor.",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(Modifier.height(16.dp))
                Button(onClick = onEscolherLocal, modifier = Modifier.fillMaxWidth()) {
                    Text("Escolher local")
                }
            }

            else -> Column(Modifier.verticalScroll(rememberScrollState())) {
                Text(estado.local, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(12.dp))
                estado.itens.forEach { item ->
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp)) {
                            Text(
                                text = item.tipo.nomeExibicao,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = item.diasDaSemana.joinToString(", ") { nomeDoDia(it) },
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "Próxima coleta: ${formatarData(item.proximaData)}",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                }
                Text(
                    text = "Não há horário fixo de passagem do caminhão.",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Feriados nacionais: a coleta passa para o próximo dia útil.",
                    style = MaterialTheme.typography.bodyMedium,
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
