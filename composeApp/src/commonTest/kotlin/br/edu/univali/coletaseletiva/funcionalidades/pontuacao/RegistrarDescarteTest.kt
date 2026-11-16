package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import br.edu.univali.coletaseletiva.util.RelogioFixo
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

// PB16 · critérios 2 a 5: registrar descarte (RN08 e RN09).
class RegistrarDescarteTest {

    private val hoje = LocalDate(2026, 11, 16)
    private val ontem = LocalDate(2026, 11, 15)
    private val relogio = RelogioFixo(hoje)

    private fun descarte(data: LocalDate) = Descarte(material = Material.PAPEL, pontos = 5, data = data)

    @Test
    fun registrar_credita_os_pontos_do_material() = runTest {
        val repo = DescarteRepositorioFake()

        val resultado = RegistrarDescarte(repo, relogio)(Material.PAPEL)

        assertEquals(ResultadoRegistro.Sucesso(5), resultado)
        assertEquals(1, repo.guardados.size)
        assertEquals(hoje, repo.guardados.single().data)
        assertEquals(Material.PAPEL, repo.guardados.single().material)
    }

    @Test
    fun quarto_registro_no_mesmo_dia_atinge_o_limite() = runTest {
        val repo = DescarteRepositorioFake(List(3) { descarte(hoje) })

        val resultado = RegistrarDescarte(repo, relogio)(Material.VIDRO)

        assertIs<ResultadoRegistro.LimiteAtingido>(resultado)
        assertEquals(3, repo.guardados.size)
    }

    @Test
    fun novo_dia_zera_o_limite() = runTest {
        val repo = DescarteRepositorioFake(List(3) { descarte(ontem) })

        val resultado = RegistrarDescarte(repo, relogio)(Material.METAL)

        assertEquals(ResultadoRegistro.Sucesso(5), resultado)
        assertEquals(4, repo.guardados.size)
    }

    @Test
    fun roupas_nao_pontuam_e_nada_e_salvo() = runTest {
        val repo = DescarteRepositorioFake()

        val resultado = RegistrarDescarte(repo, relogio)(Material.ROUPAS)

        assertIs<ResultadoRegistro.MaterialNaoPontua>(resultado)
        assertEquals(0, repo.guardados.size)
    }
}
