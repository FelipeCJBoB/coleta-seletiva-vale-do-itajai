package br.edu.univali.coletaseletiva.funcionalidades.local

/** Município cadastrado (dados de demonstração). */
data class Municipio(val id: Long, val nome: String, val uf: String)

/** Setor (bairro) de coleta de um município. */
data class Setor(val id: Long, val municipioId: Long, val nome: String)

/** O local que o usuário escolheu: já com os nomes, para mostrar na tela inicial ("Município · Setor"). */
data class SetorEscolhido(val setorId: Long, val municipio: String, val setor: String)
