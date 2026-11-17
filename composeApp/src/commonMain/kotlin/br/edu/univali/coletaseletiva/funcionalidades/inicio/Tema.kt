package br.edu.univali.coletaseletiva.funcionalidades.inicio

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/** Cores do app: verde de coleta seletiva, com âmbar de destaque (avisos e pontos de descarte). */
private val CoresDoApp = lightColorScheme(
    primary = Color(0xFF1B7F4C),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD6F0E0),
    onPrimaryContainer = Color(0xFF0A3D24),
    secondary = Color(0xFF3F6F57),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE3EEE7),
    onSecondaryContainer = Color(0xFF1B3327),
    tertiary = Color(0xFFB26A00),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFE9C2),
    onTertiaryContainer = Color(0xFF3D2400),
    background = Color(0xFFF6FAF7),
    onBackground = Color(0xFF14211C),
    surface = Color(0xFFF6FAF7),
    onSurface = Color(0xFF14211C),
    surfaceVariant = Color(0xFFE6EDE8),
    onSurfaceVariant = Color(0xFF3E4A43),
    outline = Color(0xFF8A968F),
    outlineVariant = Color(0xFFC9D3CC),
)

/** Cantos mais arredondados em todo o app. */
private val FormasDoApp = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
)

/** Títulos mais fortes que o padrão do Material, para dar hierarquia às telas. */
private val TextosDoApp = Typography().let { base ->
    base.copy(
        headlineMedium = base.headlineMedium.copy(fontWeight = FontWeight.Bold),
        headlineSmall = base.headlineSmall.copy(fontWeight = FontWeight.Bold),
        titleLarge = base.titleLarge.copy(fontWeight = FontWeight.SemiBold),
        titleMedium = base.titleMedium.copy(fontWeight = FontWeight.SemiBold),
    )
}

@Composable
fun Tema(conteudo: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CoresDoApp,
        shapes = FormasDoApp,
        typography = TextosDoApp,
        content = conteudo,
    )
}
