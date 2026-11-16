package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import kotlinx.datetime.LocalDate

/** Contrato de onde ficam os descartes. A versão real usa o banco (DescarteRepositorioSql). */
interface DescarteRepositorio {
    suspend fun inserir(descarte: Descarte)

    /** Do mais recente para o mais antigo (RF10). */
    suspend fun listarMaisRecentesPrimeiro(): List<Descarte>

    /** Quantos descartes foram registrados naquela data (base do limite diário, RN09). */
    suspend fun contarNaData(data: LocalDate): Int

    /** Saldo: soma dos pontos de todos os descartes (0 quando não há nenhum). */
    suspend fun somarPontos(): Int
}
