package br.edu.univali.coletaseletiva.funcionalidades.local

// ======================================================================
// ARQUIVO-ESQUELETO · Integrante 2 · Cartão PB07 · arquivo 6 de 12
// Tela (Compose) de escolha de local.
//
// PASSO A PASSO (troque [ ] por [x] quando terminar; no fim, apague este bloco
// e deixe só comentários úteis):
//   [ ] 1. Crie `EscolherLocalRota(navegador)`: pega o container (LocalContainer.current),
//          cria o ViewModel com viewModel { ... } e coleta o estado (receita R4).
//   [ ] 2. Crie `EscolherLocalTela(estado, ...)` SEM ViewModel dentro (só recebe estado e
//          funções): assim dá para testar.
//   [ ] 3. Conteúdo: título 'Escolha seu local', lista de municípios (clicável), depois
//          lista de setores, botão 'Confirmar' (ativo só com setor escolhido) e o texto
//          'Sua escolha fica só no aparelho.' (RN07). Se semMunicipios: 'Nenhum município
//          cadastrado'.
//   [ ] 4. Quando estado.concluido ficar verdadeiro, volte ao Início:
//          navegador.trocarPara(Rota.Inicio).
//
// Requisitos: RF01, RN06, RN07, RNF05, CU01  ·  Receitas: docs/RECEITAS.md  ·  Bíblia:
//        docs/guias/integrante-2.md
// ======================================================================
