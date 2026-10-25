package br.edu.univali.coletaseletiva.funcionalidades.pontos

import br.edu.univali.coletaseletiva.dados.bancoEmMemoria
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds
import kotlin.time.measureTime

class PontosRepositorioSqlTest {

    @Test
    fun ponto_salvo_volta_com_horario_e_materiais_com_limite() = runTest {
        val repo = PontosRepositorioSql(bancoEmMemoria())
        val ponto = PontoDeDescarte(
            id = 10L,
            municipioId = 1L,
            nome = "Ponto Teste",
            endereco = "Rua Teste, 123",
            horario = "Seg a sex, 8h às 17h",
            latitude = -26.9,
            longitude = -48.6,
            materiais = listOf(
                MaterialAceito(Material.ELETRONICOS, limite = "até 5 kg"),
                MaterialAceito(Material.PILHAS_BATERIAS),
            ),
        )

        repo.cadastrar(ponto)

        val recuperado = repo.ponto(10L)
        assertNotNull(recuperado)
        assertEquals("Seg a sex, 8h às 17h", recuperado.horario)
        assertEquals(2, recuperado.materiais.size)

        val eletronicos = recuperado.materiais.find { it.material == Material.ELETRONICOS }
        assertNotNull(eletronicos)
        assertEquals("até 5 kg", eletronicos.limite)
    }

    @Test
    fun ponto_novo_cadastrado_aparece_sem_mudar_codigo() = runTest {
        val repo = PontosRepositorioSql(bancoEmMemoria())
        repo.semearSeVazio()

        val pontoNovo = PontoDeDescarte(
            id = 999L,
            municipioId = 2L,
            nome = "Ponto Inserido Dinamicamente",
            endereco = "Rua Nova, 50",
            horario = "24 horas",
            latitude = -27.0,
            longitude = -48.6,
            materiais = listOf(MaterialAceito(Material.VIDRO)),
        )

        repo.cadastrar(pontoNovo)

        val pontosMunicipio2 = repo.pontosDoMunicipio(2L)
        assertTrue(pontosMunicipio2.any { it.id == 999L && it.nome == "Ponto Inserido Dinamicamente" })
    }

    @Test
    fun duzentos_pontos_carregam_em_menos_de_3_segundos() = runTest {
        val repo = PontosRepositorioSql(bancoEmMemoria())

        for (i in 1..200) {
            repo.cadastrar(
                PontoDeDescarte(
                    id = i.toLong(),
                    municipioId = 1L,
                    nome = "Ponto $i",
                    endereco = "Endereço $i",
                    horario = "Horário $i",
                    latitude = -26.9 + (i * 0.001),
                    longitude = -48.6 + (i * 0.001),
                    materiais = listOf(
                        MaterialAceito(Material.PAPEL),
                        MaterialAceito(Material.PLASTICO),
                    ),
                ),
            )
        }

        val duracao = measureTime {
            val resultado = repo.pontosDoMunicipio(1L)
            assertEquals(200, resultado.size)
        }

        assertTrue(duracao < 3.seconds, "O carregamento de 200 pontos levou $duracao, esperava-se menos de 3s.")
    }
}
