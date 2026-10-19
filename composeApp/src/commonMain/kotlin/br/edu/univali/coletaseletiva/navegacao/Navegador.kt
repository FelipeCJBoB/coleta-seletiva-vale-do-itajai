package br.edu.univali.coletaseletiva.navegacao

import androidx.compose.runtime.mutableStateListOf

/** Pilha simples de telas. `ir` abre uma tela, `voltar` fecha a atual, `trocarPara` reinicia a pilha. */
class Navegador(inicial: Rota = Rota.Inicio) {
    private val pilha = mutableStateListOf(inicial)

    val atual: Rota get() = pilha.last()

    fun ir(rota: Rota) {
        pilha.add(rota)
    }

    /** Devolve false quando já está na primeira tela. */
    fun voltar(): Boolean {
        if (pilha.size <= 1) return false
        pilha.removeAt(pilha.lastIndex)
        return true
    }

    fun trocarPara(rota: Rota) {
        pilha.clear()
        pilha.add(rota)
    }
}
