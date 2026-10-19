package br.edu.univali.coletaseletiva.funcionalidades.pontuacao

// ======================================================================
// ARQUIVO-ESQUELETO · Integrante 5 · Cartão PB16 · arquivo 8 de 17
// Caso de uso: regras RN08 e RN09 juntas.
//
// PASSO A PASSO (troque [ ] por [x] quando terminar; no fim, apague este bloco
// e deixe só comentários úteis):
//   [ ] 1. Crie o resultado `ResultadoRegistro` (sealed interface): Sucesso(pontos: Int),
//          LimiteAtingido, MaterialNaoPontua.
//   [ ] 2. Crie `RegistrarDescarte(repositorio: DescarteRepositorio, relogio: Relogio)`
//          com `suspend operator fun invoke(material: Material): ResultadoRegistro`.
//   [ ] 3. Passos: (1) pontos = pontosPorMaterial(material) — se nulo, MaterialNaoPontua;
//          (2) hoje = relogio.hoje(); se repositorio.contarNaData(hoje) já for 3 ou mais,
//          LimiteAtingido; (3) senão insere Descarte(material, pontos, hoje) e devolve
//          Sucesso(pontos).
//   [ ] 4. Constante LIMITE_DIARIO = 3 (RN09) no topo do arquivo.
//
// Requisitos: RF09, RF10, RN08, RN09, CU06, CU07  ·  Receitas: docs/RECEITAS.md  ·
//        Bíblia: docs/guias/integrante-5.md
// ======================================================================
