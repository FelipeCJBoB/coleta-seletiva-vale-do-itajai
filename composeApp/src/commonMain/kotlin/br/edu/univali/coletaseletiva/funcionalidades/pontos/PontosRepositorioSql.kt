package br.edu.univali.coletaseletiva.funcionalidades.pontos

import br.edu.univali.coletaseletiva.dados.Banco
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repositório SQL real de pontos de descarte usando SQDelight.
 */
class PontosRepositorioSql(
    private val banco: Banco,
) : PontosRepositorio {

    private val queries get() = banco.pontosQueries

    override suspend fun pontosDoMunicipio(municipioId: Long): List<PontoDeDescarte> = withContext(Dispatchers.Default) {
        val pontosDb = queries.listarPontosDoMunicipio(municipioId).executeAsList()
        pontosDb.map { p ->
            val materiaisDb = queries.listarMateriaisDoPonto(p.id).executeAsList()
            val materiais = materiaisDb.map { m ->
                MaterialAceito(
                    material = Material.valueOf(m.material),
                    limite = m.limite,
                )
            }
            PontoDeDescarte(
                id = p.id,
                municipioId = p.municipio_id,
                nome = p.nome,
                endereco = p.endereco,
                horario = p.horario,
                latitude = p.latitude,
                longitude = p.longitude,
                materiais = materiais,
            )
        }
    }

    override suspend fun ponto(id: Long): PontoDeDescarte? = withContext(Dispatchers.Default) {
        val p = queries.buscarPonto(id).executeAsOneOrNull() ?: return@withContext null
        val materiaisDb = queries.listarMateriaisDoPonto(p.id).executeAsList()
        val materiais = materiaisDb.map { m ->
            MaterialAceito(
                material = Material.valueOf(m.material),
                limite = m.limite,
            )
        }
        PontoDeDescarte(
            id = p.id,
            municipioId = p.municipio_id,
            nome = p.nome,
            endereco = p.endereco,
            horario = p.horario,
            latitude = p.latitude,
            longitude = p.longitude,
            materiais = materiais,
        )
    }

    override suspend fun cadastrar(ponto: PontoDeDescarte) = withContext(Dispatchers.Default) {
        banco.transaction {
            queries.inserirPonto(
                id = ponto.id,
                municipio_id = ponto.municipioId,
                nome = ponto.nome,
                endereco = ponto.endereco,
                horario = ponto.horario,
                latitude = ponto.latitude,
                longitude = ponto.longitude,
            )
            ponto.materiais.forEach { aceito ->
                queries.inserirMaterialDoPonto(
                    ponto_id = ponto.id,
                    material = aceito.material.name,
                    limite = aceito.limite,
                )
            }
        }
    }

    suspend fun semearSeVazio() = withContext(Dispatchers.Default) {
        if (queries.contarPontos().executeAsOne() == 0L) {
            DadosPontosDemo.lista.forEach { ponto ->
                cadastrar(ponto)
            }
        }
    }
}
