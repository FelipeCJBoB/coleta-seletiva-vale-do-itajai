package br.edu.univali.coletaseletiva.funcionalidades.calendario

// ======================================================================
// ARQUIVO-ESQUELETO · Integrante 2 · Cartão PB08 · arquivo 5 de 16
// Regra RN01 a RN03: a próxima coleta.
//
// PASSO A PASSO (troque [ ] por [x] quando terminar; no fim, apague este bloco
// e deixe só comentários úteis):
//   [ ] 1. Crie `proximaColeta(hoje: LocalDate, diasDaSemana: Set<DayOfWeek>): LocalDate`.
//   [ ] 2. Ideia em passos: (1) comece em 'hoje'; (2) ande dia a dia até achar um dia cujo
//          dayOfWeek esteja no conjunto; (3) esse é o dia programado; (4) se o dia
//          programado for feriado nacional, ande até o próximo dia útil (não sábado, não
//          domingo, não feriado); (5) devolva essa data.
//   [ ] 3. Atenção ao caso 'hoje é feriado e é dia de coleta': a coleta passa para o
//          próximo dia útil (receita R11).
//
// Requisitos: RF03, RN01, RN02, RN03, CU02  ·  Receitas: docs/RECEITAS.md  ·  Bíblia:
//        docs/guias/integrante-2.md
// ======================================================================
