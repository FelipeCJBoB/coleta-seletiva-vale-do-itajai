package br.edu.univali.coletaseletiva.funcionalidades.local

// ======================================================================
// ARQUIVO-ESQUELETO · Integrante 2 · Cartão PB07 · arquivo 9 de 12
// Implementação do repositório com SQLite.
//
// PASSO A PASSO (troque [ ] por [x] quando terminar; no fim, apague este bloco
// e deixe só comentários úteis):
//   [ ] 1. Crie `LocalRepositorioSql(banco: Banco) : LocalRepositorio`. Use
//          withContext(Dispatchers.Default) em cada função (receita R7).
//   [ ] 2. Converta as linhas do banco para Municipio/Setor com a lambda do SQLDelight (os
//          campos viram parâmetros).
//   [ ] 3. Crie também `semearSeVazio()`: se contarMunicipios = 0, insere os dados de
//          DadosLocalDemo.
//
// Requisitos: RF01, RN06, RN07, RNF05, CU01  ·  Receitas: docs/RECEITAS.md  ·  Bíblia:
//        docs/guias/integrante-2.md
// ======================================================================
