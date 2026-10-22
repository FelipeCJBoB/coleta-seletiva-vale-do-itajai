package br.edu.univali.coletaseletiva.funcionalidades.inicio

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/** Cores do app: verde de coleta seletiva. */
private val CoresDoApp = lightColorScheme(
    primary = Color(0xFF1B7F4C),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD6F0E0),
    onPrimaryContainer = Color(0xFF0A3D24),
    secondary = Color(0xFF3F6F57),
    onSecondary = Color.White,
    background = Color(0xFFF6FAF7),
    onBackground = Color(0xFF14211C),
    surface = Color(0xFFF6FAF7),
    onSurface = Color(0xFF14211C),
)

@Composable
fun Tema(conteudo: @Composable () -> Unit) {
    MaterialTheme(colorScheme = CoresDoApp, content = conteudo)
}
