package br.edu.univali.coletaseletiva.funcionalidades.lembrete

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.navegacao.Navegador
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

/** Horários oferecidos para o aviso (uma lista simples, sem relógio de seleção). */
private val HORARIOS = listOf(
    LocalTime(7, 0), LocalTime(12, 0), LocalTime(18, 0),
    LocalTime(19, 0), LocalTime(20, 0), LocalTime(21, 0),
)

@Composable
fun LembreteRota(navegador: Navegador) {
    val container = LocalContainer.current
    val vm = viewModel {
        LembreteViewModel(
            container.agendador,
            container.calendarioRepositorio,
            container.localRepositorio,
            container.relogio,
        )
    }
    val estado by vm.estado.collectAsState()

    LembreteTela(
        estado = estado,
        onAtivar = vm::ativar,
        onDesativar = vm::desativar,
        onVoltar = { navegador.voltar() },
    )
}

/** Lembrete na véspera da coleta (RF02): interruptor e horário do aviso. */
@Composable
fun LembreteTela(
    estado: LembreteEstado,
    onAtivar: (LocalTime) -> Unit,
    onDesativar: () -> Unit,
    onVoltar: () -> Unit,
) {
    var horario by remember(estado.horario) { mutableStateOf(estado.horario) }

    Column(Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState())) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Lembrete da coleta", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = onVoltar) { Text("Voltar") }
        }
        Spacer(Modifier.height(16.dp))

        Text(
            "Receba um aviso na véspera da próxima coleta do seu setor, no horário que você escolher.",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .toggleable(
                    value = estado.ativo,
                    role = Role.Switch,
                    onValueChange = { ligar -> if (ligar) onAtivar(horario) else onDesativar() },
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Lembrar na véspera", style = MaterialTheme.typography.titleMedium)
            Switch(checked = estado.ativo, onCheckedChange = null)
        }
        Spacer(Modifier.height(16.dp))

        Text("Horário do aviso", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            HORARIOS.take(3).forEach { h -> ChipDeHorario(h, h == horario) { escolher(h, estado, onAtivar) { horario = it } } }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            HORARIOS.drop(3).forEach { h -> ChipDeHorario(h, h == horario) { escolher(h, estado, onAtivar) { horario = it } } }
        }
        Spacer(Modifier.height(16.dp))

        when {
            estado.semLocal -> Text(
                "Escolha seu local para receber o lembrete.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
            )
            estado.ativo && estado.proximoAviso != null -> Text(
                "Próximo aviso: ${formatarAviso(estado.proximoAviso)}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            "O lembrete é local: funciona sem internet e só avisa uma vez por vez ligado. Ligue de novo depois da coleta para receber o próximo.",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun ChipDeHorario(horario: LocalTime, selecionado: Boolean, onClick: () -> Unit) {
    FilterChip(selected = selecionado, onClick = onClick, label = { Text(formatarHora(horario)) })
}

/** Tocar num horário o seleciona; se o lembrete já está ligado, reagenda no novo horário. */
private fun escolher(
    horario: LocalTime,
    estado: LembreteEstado,
    onAtivar: (LocalTime) -> Unit,
    selecionar: (LocalTime) -> Unit,
) {
    selecionar(horario)
    if (estado.ativo) onAtivar(horario)
}

private fun formatarHora(hora: LocalTime): String =
    "${hora.hour.toString().padStart(2, '0')}:${hora.minute.toString().padStart(2, '0')}"

/** dd/MM/aaaa às HH:mm */
private fun formatarAviso(quando: LocalDateTime): String {
    val dia = quando.day.toString().padStart(2, '0')
    val mes = (quando.month.ordinal + 1).toString().padStart(2, '0')
    return "$dia/$mes/${quando.year} às ${formatarHora(quando.time)}"
}
