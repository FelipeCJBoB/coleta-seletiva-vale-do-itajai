package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

import br.edu.univali.coletaseletiva.funcionalidades.pontos.Material
import br.edu.univali.coletaseletiva.util.Relogio

/** RN09: no máximo 3 descartes por dia; acima disso avisa e não credita. */
const val LIMITE_DIARIO = 3

sealed interface ResultadoRegistro {
    data class Sucesso(val pontos: Int) : ResultadoRegistro
    data object LimiteAtingido : ResultadoRegistro
    data object MaterialNaoPontua : ResultadoRegistro
}

/** Caso de uso do PB16: aplica RN08 (pontos) e RN09 (limite por dia) e só então salva. */
class RegistrarDescarte(
    private val repositorio: DescarteRepositorio,
    private val relogio: Relogio,
) {
    suspend operator fun invoke(material: Material): ResultadoRegistro {
        val pontos = pontosPorMaterial(material) ?: return ResultadoRegistro.MaterialNaoPontua
        val hoje = relogio.hoje()
        if (repositorio.contarNaData(hoje) >= LIMITE_DIARIO) return ResultadoRegistro.LimiteAtingido
        repositorio.inserir(Descarte(material = material, pontos = pontos, data = hoje))
        return ResultadoRegistro.Sucesso(pontos)
    }
}
