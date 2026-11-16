package br.edu.univali.coletaseletiva.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material

// Peças visuais comuns às telas (só aparência, sem regra de negócio).

/** Faixa verde no topo de cada tela: título e botão "Voltar". Respeita a barra de status do aparelho. */
@Composable
fun BarraDeTopo(titulo: String, onVoltar: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(start = 8.dp, end = 16.dp, top = 6.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextButton(
                onClick = onVoltar,
                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary),
            ) {
                Text("← Voltar")
            }
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 4.dp),
            )
        }
    }
}

/** Círculo colorido com um emoji dentro. Decorativo: o leitor de tela ignora. */
@Composable
fun Selo(
    emoji: String,
    modifier: Modifier = Modifier,
    tamanho: Dp = 44.dp,
    fundo: Color = MaterialTheme.colorScheme.primaryContainer,
) {
    Box(
        modifier = modifier
            .size(tamanho)
            .clip(CircleShape)
            .background(fundo)
            .clearAndSetSemantics {},
        contentAlignment = Alignment.Center,
    ) {
        Text(text = emoji, fontSize = (tamanho.value * 0.5f).sp)
    }
}

/** Tela ou lista sem nada para mostrar: emoji grande e uma frase. */
@Composable
fun EstadoVazio(emoji: String, texto: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth().padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Selo(emoji = emoji, tamanho = 64.dp, fundo = MaterialTheme.colorScheme.secondaryContainer)
        Spacer(Modifier.height(4.dp))
        Text(
            text = texto,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

/** Título de seção dentro de uma tela. */
@Composable
fun TituloDeSecao(texto: String, modifier: Modifier = Modifier) {
    Text(
        text = texto,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier,
    )
}

/** Emoji de cada material, usado nas listas e botões (só decoração). */
fun iconeDoMaterial(material: Material): String = when (material) {
    Material.PAPEL -> "📄"
    Material.PLASTICO -> "🧴"
    Material.METAL -> "🥫"
    Material.VIDRO -> "🫙"
    Material.PILHAS_BATERIAS -> "🔋"
    Material.OLEO -> "🛢️"
    Material.ELETRONICOS -> "💻"
    Material.ROUPAS -> "👕"
}
