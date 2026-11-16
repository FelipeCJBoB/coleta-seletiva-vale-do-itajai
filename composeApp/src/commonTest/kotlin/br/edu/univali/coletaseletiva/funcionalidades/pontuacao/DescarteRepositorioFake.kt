package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import kotlinx.datetime.LocalDate

/** Repositório de mentira para os testes: guarda os descartes numa lista em memória. */
class DescarteRepositorioFake(inicial: List<Descarte> = emptyList()) : DescarteRepositorio {

    val guardados = inicial.toMutableList()

    override suspend fun inserir(descarte: Descarte) {
        guardados += descarte.copy(id = guardados.size + 1L)
    }

    // Do mais recente para o mais antigo (empate na data: o inserido por último vem primeiro).
    override suspend fun listarMaisRecentesPrimeiro(): List<Descarte> =
        guardados.sortedWith(compareByDescending<Descarte> { it.data }.thenByDescending { it.id })

    override suspend fun contarNaData(data: LocalDate): Int = guardados.count { it.data == data }

    override suspend fun somarPontos(): Int = guardados.sumOf { it.pontos }
}
