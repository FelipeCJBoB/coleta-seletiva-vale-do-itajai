package br.edu.univali.coletaseletiva.di

import androidx.compose.runtime.staticCompositionLocalOf
import br.edu.univali.coletaseletiva.dados.Banco
import br.edu.univali.coletaseletiva.dados.DriverFactory
import br.edu.univali.coletaseletiva.funcionalidades.local.LocalRepositorioSql
import br.edu.univali.coletaseletiva.util.Relogio
import br.edu.univali.coletaseletiva.util.RelogioDoSistema

/**
 * Onde as peças do app são criadas e entregues às telas (injeção de dependência manual).
 *
 * REGRA: cada Integrante só escreve dentro do SEU bloco (entre os comentários "início" e "fim").
 * Assim, dois PRs nunca editam as mesmas linhas e não dá conflito de merge.
 */
class AppContainer(
    val driverFactory: DriverFactory,
    val relogio: Relogio = RelogioDoSistema,
) {

    /**
     * Roda uma vez quando o app abre, ANTES da primeira tela (veja App.kt).
     * Cada Integrante chama aqui, no seu bloco, o `semearSeVazio()` do seu repositório
     * (insere os dados de demonstração só se o banco estiver vazio).
     */
    suspend fun iniciar() {
        // ===== INTEGRANTE 2 (PB07, PB08): dados de demonstração =====
        // início
        localRepositorio.semearSeVazio()
        // fim


        // ===== INTEGRANTE 3 (PB09): dados de demonstração =====
        // início

        // fim
    }

    // ===== INTEGRANTE 5 (PB06b): banco de dados =====
    // início
    val banco: Banco by lazy { Banco(driverFactory.criar(Banco.Schema)) }
    // fim


    // ===== INTEGRANTE 2 (PB07, PB08): local e calendário =====
    // início
    val localRepositorio: LocalRepositorioSql by lazy { LocalRepositorioSql(banco) }
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
