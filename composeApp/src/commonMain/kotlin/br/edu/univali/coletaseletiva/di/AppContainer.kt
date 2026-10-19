package br.edu.univali.coletaseletiva.di

import androidx.compose.runtime.staticCompositionLocalOf
import br.edu.univali.coletaseletiva.dados.DriverFactory

/**
 * Onde as peças do app são criadas e entregues às telas (injeção de dependência manual).
 *
 * REGRA: cada Integrante só escreve dentro do SEU bloco (entre os comentários "início" e "fim").
 * Assim, dois PRs nunca editam as mesmas linhas e não dá conflito de merge.
 */
class AppContainer(val driverFactory: DriverFactory) {

    // ===== INTEGRANTE 5 (PB06b): banco de dados =====
    // início

    // fim


    // ===== INTEGRANTE 2 (PB07, PB08): local e calendário =====
    // início

    // fim


    // ===== INTEGRANTE 3 (PB09, PB10, PB17): pontos de descarte e dicas =====
    // início

    // fim


    // ===== INTEGRANTE 4 (PB11): guia de separação =====
    // início

    // fim


    // ===== INTEGRANTE 5 (PB16): pontuação =====
    // início

    // fim


    // ===== INTEGRANTE 2 (PB12, opcional): lembrete =====
    // início

    // fim
}

val LocalContainer = staticCompositionLocalOf<AppContainer> {
    error("AppContainer não foi fornecido. Veja App.kt")
}
