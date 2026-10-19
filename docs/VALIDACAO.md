# Roteiro de validação (PB14)

Validação feita **somente pelo grupo**, em emulador e em celular Android (APK do CI). Sem usuários externos. O Integrante 1 completa os passos na Sprint 3 e o grupo executa na Sprint 4.

| ID | Origem | Caso | Resultado esperado | Passos (Integrante 1 completa) | Resultado obtido | Data | Executor | Aprovado |
|---|---|---|---|---|---|---|---|---|
| VAL-01 | CU01 / RF01 | Escolher município e setor | Itajaí e Camboriú aparecem; ao escolher o município aparecem só os setores dele; ao confirmar volta ao Início mostrando 'Município · Setor'. | | | | | |
| VAL-02 | CU01 / RN07 | Fechar e abrir o app | A escolha continua salva. | | | | | |
| VAL-03 | CU02 / RF03 | Calendário | Mostra os dias da coleta seletiva e comum do setor e a próxima data; não mostra horário. | | | | | |
| VAL-04 | CU02 / RN03 | Calendário perto de feriado | Em um setor com coleta numa segunda que cai em feriado (ex.: 07/09), a próxima data vai para o dia útil seguinte (confira com a data do sistema ou o teste automatizado). | | | | | |
| VAL-05 | CU03 / RF04 | Pontos de descarte | Lista só os pontos do município escolhido, com endereço, horário e materiais (com limite quando houver). | | | | | |
| VAL-06 | CU03 / RF07 | Filtro por material | Ao tocar em Pilhas e baterias, só aparecem pontos que aceitam pilhas; aparece o aviso de RN04. | | | | | |
| VAL-07 | CU03 / RF05 | Abrir rota | No detalhe do ponto, 'Abrir rota' abre o app de mapas no local. | | | | | |
| VAL-08 | CU04 / RF08 | Guia: busca | Buscar 'caixa de leite' (e 'CAIXA LEITE') encontra o item; buscar algo inexistente mostra 'Nada encontrado'. | | | | | |
| VAL-09 | CU04 / RNF03 | Guia sem internet | Com o aparelho em modo avião, o guia funciona normalmente. | | | | | |
| VAL-10 | CU06 / RF09 | Registrar descarte | Registrar PAPEL credita 5 pontos; ELETRÔNICOS credita 15. | | | | | |
| VAL-11 | CU06 / RN09 | Limite diário | O 4º registro no mesmo dia mostra o aviso de limite e não credita pontos. | | | | | |
| VAL-12 | CU07 / RF10 | Saldo e histórico | O saldo é a soma dos pontos e o histórico mostra do mais recente para o mais antigo. | | | | | |
| VAL-13 | CU08 / RF11 | Dicas | Mostra a dica do dia e a lista de dicas. | | | | | |
| VAL-14 | RNF01 | Dois toques | A partir do Início, Calendário, Pontos e Guia abrem com 1 toque. | | | | | |
| VAL-15 | RNF04 | Desempenho | A lista de pontos aparece em até 3 s (cronometre no celular). | | | | | |
| VAL-16 | RNF02 | Versões do Android | O app instala e abre em um aparelho/emulador Android 9 (API 28) ou superior. | | | | | |
