package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import br.edu.univali.coletaseletiva.dados.Banco
import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate

/** Guarda os descartes no banco local (RN07). A data vai como texto AAAA-MM-DD e o material como o nome do enum. */
class DescarteRepositorioSql(private val banco: Banco) : DescarteRepositorio {

    private val queries get() = banco.descarteQueries

    override suspend fun inserir(descarte: Descarte): Unit = withContext(Dispatchers.Default) {
        queries.inserirDescarte(descarte.material.name, descarte.pontos.toLong(), descarte.data.toString())
        Unit
    }

    override suspend fun listarMaisRecentesPrimeiro(): List<Descarte> = withContext(Dispatchers.Default) {
        queries.listarDescartesRecentesPrimeiro().executeAsList().map {
            Descarte(
                id = it.id,
                material = Material.valueOf(it.material),
                pontos = it.pontos.toInt(),
                data = LocalDate.parse(it.data_),
            )
        }
    }

    override suspend fun contarNaData(data: LocalDate): Int = withContext(Dispatchers.Default) {
        queries.contarDescartesNaData(data.toString()).executeAsOne().toInt()
    }

    override suspend fun somarPontos(): Int = withContext(Dispatchers.Default) {
        queries.somarPontos().executeAsOne().toInt()
    }
}
