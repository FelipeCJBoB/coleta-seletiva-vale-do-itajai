package br.edu.univali.coletaseletiva.funcionalidades.local

// ======================================================================
// ARQUIVO-ESQUELETO · Integrante 2 · Cartão PB07 · arquivo 5 de 12
// Estado e ações da tela de escolha.
//
// PASSO A PASSO (troque [ ] por [x] quando terminar; no fim, apague este bloco
// e deixe só comentários úteis):
//   [ ] 1. Crie `EscolherLocalEstado` com: carregando, municipios, municipioSelecionado,
//          setores, setorSelecionado, concluido, semMunicipios.
//   [ ] 2. Crie `EscolherLocalViewModel(repositorio: LocalRepositorio)` com StateFlow
//          `estado` (receita R3).
//   [ ] 3. Ações: escolherMunicipio(id) (carrega os setores dele), escolherSetor(id),
//          confirmar() (chama repositorio.escolherSetor e marca concluido).
//   [ ] 4. Ao iniciar, carregue os municípios; se vierem vazios, marque semMunicipios.
//
// Requisitos: RF01, RN06, RN07, RNF05, CU01  ·  Receitas: docs/RECEITAS.md  ·  Bíblia:
//        docs/guias/integrante-2.md
// ======================================================================
