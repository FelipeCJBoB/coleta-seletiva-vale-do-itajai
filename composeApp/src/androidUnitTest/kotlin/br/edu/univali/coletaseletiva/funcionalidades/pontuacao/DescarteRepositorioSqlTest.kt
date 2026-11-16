package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import br.edu.univali.coletaseletiva.dados.bancoEmMemoria
import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

// PB16 · critério 9: o que foi gravado no banco volta igual.
class DescarteRepositorioSqlTest {

    private val d14 = LocalDate(2026, 11, 14)
    private val d15 = LocalDate(2026, 11, 15)
    private val d16 = LocalDate(2026, 11, 16)

    @Test
    fun saldo_e_historico_persistem() = runTest {
        val repo = DescarteRepositorioSql(bancoEmMemoria())
        assertEquals(0, repo.somarPontos(), "Sem descartes o saldo é 0.")

        repo.inserir(Descarte(material = Material.PAPEL, pontos = 5, data = d14))
        repo.inserir(Descarte(material = Material.OLEO, pontos = 10, data = d16))
        repo.inserir(Descarte(material = Material.ELETRONICOS, pontos = 15, data = d15))

        assertEquals(30, repo.somarPontos())
        val historico = repo.listarMaisRecentesPrimeiro()
        assertEquals(listOf(d16, d15, d14), historico.map { it.data })
        assertEquals(listOf(Material.OLEO, Material.ELETRONICOS, Material.PAPEL), historico.map { it.material })
        assertEquals(listOf(10, 15, 5), historico.map { it.pontos })
    }

    @Test
    fun contarNaData_so_conta_a_data_pedida() = runTest {
        val repo = DescarteRepositorioSql(bancoEmMemoria())
        repo.inserir(Descarte(material = Material.PAPEL, pontos = 5, data = d15))
        repo.inserir(Descarte(material = Material.VIDRO, pontos = 5, data = d16))
        repo.inserir(Descarte(material = Material.METAL, pontos = 5, data = d16))

        assertEquals(2, repo.contarNaData(d16))
        assertEquals(1, repo.contarNaData(d15))
        assertEquals(0, repo.contarNaData(d14))
    }
}
