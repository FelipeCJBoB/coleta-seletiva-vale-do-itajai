package br.edu.univali.coletaseletiva.navegacao

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/** Tela provisória. Cada Integrante a substitui pela sua tela real em AppNavegacao.kt. */
@Composable
fun TelaEmConstrucao(titulo: String, navegador: Navegador) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("$titulo: em construção")
        Button(onClick = { navegador.voltar() }) { Text("Voltar") }
    }
}
