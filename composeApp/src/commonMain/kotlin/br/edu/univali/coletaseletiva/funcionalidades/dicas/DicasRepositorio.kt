package br.edu.univali.coletaseletiva.funcionalidades.dicas

/**
 * Contrato para o repositório de dicas de reciclagem.
 */
interface DicasRepositorio {
    suspend fun listar(): List<Dica>
}

/**
 * Repositório em memória utilizando o conteúdo estático de demonstração.
 */
class DicasRepositorioEmMemoria : DicasRepositorio {
    override suspend fun listar(): List<Dica> = DadosDicasDemo.lista
}
