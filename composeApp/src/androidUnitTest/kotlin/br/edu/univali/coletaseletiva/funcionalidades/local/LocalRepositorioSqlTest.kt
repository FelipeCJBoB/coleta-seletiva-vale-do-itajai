package br.edu.univali.coletaseletiva.funcionalidades.local

import br.edu.univali.coletaseletiva.dados.bancoEmMemoria
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LocalRepositorioSqlTest {

    private suspend fun repositorioComDados(): LocalRepositorioSql {
        val repo = LocalRepositorioSql(bancoEmMemoria())
        repo.semearSeVazio()
        return repo
    }

    @Test
    fun escolha_salva_e_lida_de_novo_devolve_o_mesmo_setor() = runTest {
        val repo = repositorioComDados()
        repo.escolherSetor(5)
        assertEquals(SetorEscolhido(5, "Camboriú", "Monte Alegre"), repo.setorEscolhido())
    }

    @Test
    fun sem_escolha_devolve_nulo() = runTest {
        assertNull(repositorioComDados().setorEscolhido())
    }

    @Test
    fun trocar_a_escolha_guarda_so_a_ultima() = runTest {
        val repo = repositorioComDados()
        repo.escolherSetor(1)
        repo.escolherSetor(4)
        assertEquals(SetorEscolhido(4, "Camboriú", "Centro"), repo.setorEscolhido())
    }

    @Test
    fun municipio_novo_cadastrado_aparece_na_lista_sem_mudar_codigo() = runTest {
        val repo = repositorioComDados()
        repo.cadastrarMunicipio("Blumenau", "SC")
        assertEquals(listOf("Blumenau", "Camboriú", "Itajaí"), repo.listarMunicipios().map { it.nome })
    }

    @Test
    fun setores_sao_so_do_municipio_pedido() = runTest {
        val repo = repositorioComDados()
        assertEquals(listOf("Centro", "Fazenda", "São Vicente"), repo.listarSetores(1).map { it.nome })
        assertEquals(listOf("Centro", "Monte Alegre", "Tabuleiro"), repo.listarSetores(2).map { it.nome })
    }

    @Test
    fun semear_so_insere_quando_o_banco_esta_vazio() = runTest {
        val repo = repositorioComDados()
        repo.semearSeVazio()
        assertEquals(2, repo.listarMunicipios().size)
        assertEquals(3, repo.listarSetores(1).size)
    }
}
