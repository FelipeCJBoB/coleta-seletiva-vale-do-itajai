package br.edu.univali.coletaseletiva.navegacao

import androidx.compose.runtime.Composable
import br.edu.univali.coletaseletiva.funcionalidades.inicio.InicioRota
import br.edu.univali.coletaseletiva.funcionalidades.local.EscolherLocalRota

/**
 * Liga cada Rota à sua tela.
 * REGRA: o Integrante dono troca SÓ o bloco da sua rota (a linha TelaEmConstrucao) pela tela real.
 */
@Composable
fun AppNavegacao(navegador: Navegador) {
    val rota = navegador.atual
    // Cada tela tem o seu próprio armário de ViewModels (veja EscopoDeTela.kt).
    EscopoDeTela(rota) {
        when (rota) {

            // ===== INTEGRANTE 2 (PB07): Início =====
            Rota.Inicio -> {
                InicioRota(navegador)
            }


            // ===== INTEGRANTE 2 (PB07): Escolher local =====
            Rota.EscolherLocal -> {
                EscolherLocalRota(navegador)
            }


            // ===== INTEGRANTE 2 (PB08): Calendário =====
            Rota.Calendario -> {
                TelaEmConstrucao("Calendário", navegador)
            }


            // ===== INTEGRANTE 3 (PB09): Pontos de descarte =====
            Rota.Pontos -> {
                TelaEmConstrucao("Pontos de descarte", navegador)
            }


            // ===== INTEGRANTE 3 (PB10): Detalhe do ponto =====
            is Rota.PontoDetalhe -> {
                TelaEmConstrucao("Detalhe do ponto", navegador)
            }


            // ===== INTEGRANTE 4 (PB11): Guia de separação =====
            Rota.Guia -> {
                TelaEmConstrucao("Guia de separação", navegador)
            }


            // ===== INTEGRANTE 4 (PB11): Detalhe do item do guia =====
            is Rota.GuiaDetalhe -> {
                TelaEmConstrucao("Detalhe do item do guia", navegador)
            }


            // ===== INTEGRANTE 5 (PB16): Meus pontos =====
            Rota.MeusPontos -> {
                TelaEmConstrucao("Meus pontos", navegador)
            }


            // ===== INTEGRANTE 5 (PB16): Registrar descarte =====
            Rota.RegistrarDescarte -> {
                TelaEmConstrucao("Registrar descarte", navegador)
            }


            // ===== INTEGRANTE 3 (PB17): Dicas de reciclagem =====
            Rota.Dicas -> {
                TelaEmConstrucao("Dicas de reciclagem", navegador)
            }


            // ===== INTEGRANTE 2 (PB12): Lembrete =====
            Rota.Lembrete -> {
                TelaEmConstrucao("Lembrete", navegador)
            }

        }
    }
}
