package br.edu.univali.coletaseletiva.funcionalidades.local

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.univali.coletaseletiva.di.LocalContainer
import br.edu.univali.coletaseletiva.navegacao.Navegador
import br.edu.univali.coletaseletiva.navegacao.Rota

/** Liga a tela ao ViewModel e à navegação. Ao confirmar, volta para o Início. */
@Composable
fun EscolherLocalRota(navegador: Navegador) {
    val container = LocalContainer.current
    val vm = viewModel { EscolherLocalViewModel(container.localRepositorio) }
    val estado by vm.estado.collectAsState()
    LaunchedEffect(estado.concluido) {
        if (estado.concluido) navegador.trocarPara(Rota.Inicio)
    }
    EscolherLocalTela(
        estado = estado,
        onEscolherMunicipio = vm::escolherMunicipio,
        onEscolherSetor = vm::escolherSetor,
        onConfirmar = vm::confirmar,
        onVoltar = { navegador.voltar() },
    )
}

/** Tela sem ViewModel dentro: só recebe o estado e as ações (fácil de testar). */
@Composable
fun EscolherLocalTela(
    estado: EscolherLocalEstado,
    onEscolherMunicipio: (Long) -> Unit,
    onEscolherSetor: (Long) -> Unit,
    onConfirmar: () -> Unit,
    onVoltar: () -> Unit,
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Escolha seu local", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Sua escolha fica só no aparelho.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp, bottom = 12.dp),
        )

        if (estado.semMunicipios) {
            Text("Nenhum município cadastrado", style = MaterialTheme.typography.bodyLarge)
        }

        LazyColumn(Modifier.weight(1f)) {
            if (estado.municipios.isNotEmpty()) {
                item { Text("Município", style = MaterialTheme.typography.titleMedium) }
                items(estado.municipios, key = { "m${it.id}" }) { municipio ->
                    LinhaDeEscolha(
                        texto = "${municipio.nome} · ${municipio.uf}",
                        selecionado = municipio.id == estado.municipioSelecionado?.id,
                        onClick = { onEscolherMunicipio(municipio.id) },
                    )
                }
            }
            if (estado.municipioSelecionado != null) {
                item {
                    Text(
                        "Setor (bairro)",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 16.dp),
                    )
                }
                items(estado.setores, key = { "s${it.id}" }) { setor ->
                    LinhaDeEscolha(
                        texto = setor.nome,
                        selecionado = setor.id == estado.setorSelecionado?.id,
                        onClick = { onEscolherSetor(setor.id) },
                    )
                }
            }
        }

        Row(Modifier.fillMaxWidth().padding(top = 8.dp), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = onVoltar) { Text("Voltar") }
            Button(onClick = onConfirmar, enabled = estado.setorSelecionado != null) { Text("Confirmar") }
        }
    }
}

@Composable
private fun LinhaDeEscolha(texto: String, selecionado: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(selected = selecionado, onClick = null)
        Text(texto, modifier = Modifier.padding(start = 12.dp))
    }
}
