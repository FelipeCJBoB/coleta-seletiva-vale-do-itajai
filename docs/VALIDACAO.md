# Roteiro de validação (PB14)

Validação feita **somente pelo grupo**, em emulador e em celular Android (APK do CI). Sem usuários externos. Os passos abaixo já estão escritos; o grupo os executa na Sprint 4 e preenche **Resultado obtido, Data, Executor e Aprovado** com o que realmente viu (não preencher de memória nem por suposição).

## Como executar

1. Baixar o APK do último CI verde de `main` (GitHub > Actions > "APK de teste" > artefato) e instalar no celular, ou abrir no emulador (Android 9 / API 28 ou superior).
2. Na primeira abertura, **aceitar** o pedido de notificações (Android 13+).
3. Usar sempre os dados de demonstração. Para os casos de data (VAL-03, VAL-04, VAL-17) os resultados esperados vêm das regras já testadas; conferir também com um calendário.
4. Registrar cada caso: `Aprovado` = sim/não; se não, descrever o defeito e abrir um cartão ou issue. Executor = Integrante N que executou.
5. O resumo (quantos aprovados, defeitos achados e corrigidos) vai para o relatório 5.1.3.

## Casos

| ID | Origem | Caso | Resultado esperado | Passos | Resultado obtido | Data | Executor | Aprovado |
|---|---|---|---|---|---|---|---|---|
| VAL-01 | CU01 / RF01 | Escolher município e setor | Itajaí e Camboriú aparecem; ao escolher o município aparecem só os setores dele; ao confirmar volta ao Início mostrando 'Município · Setor'. | 1. Abrir o app (primeira vez). 2. Tocar em 'Escolher local'. 3. Ver os municípios. 4. Tocar em Camboriú e ver os setores (Centro, Monte Alegre, Tabuleiro). 5. Escolher 'Monte Alegre' e confirmar. | | | | |
| VAL-02 | CU01 / RN07 | Fechar e abrir o app | A escolha continua salva. | 1. Com o local escolhido, fechar o app por completo (tirar da lista de recentes). 2. Abrir de novo. 3. Conferir 'Camboriú · Monte Alegre' no Início. | | | | |
| VAL-03 | CU02 / RF03 | Calendário | Mostra os dias da coleta seletiva e comum do setor e a próxima data; não mostra horário. | 1. Início > Calendário. 2. Conferir 'Coleta seletiva' e 'Coleta comum' com os dias da semana. 3. Conferir 'Próxima coleta: dd/MM/aaaa' de cada tipo contra um calendário. 4. Ver o texto 'Não há horário fixo de passagem do caminhão.'. | | | | |
| VAL-04 | CU02 / RN03 | Calendário perto de feriado | Em um setor com coleta numa segunda que cai em feriado (ex.: 07/09), a próxima data vai para o dia útil seguinte (confira com a data do sistema ou o teste automatizado). | 1. Escolher 'Camboriú · Centro' (coleta seletiva na segunda). 2. Com a data do aparelho em sexta 04/09/2026 (ou o teste `ProximaColetaTest.feriado_empurra_para_o_proximo_dia_util`), abrir o Calendário. 3. Conferir que a próxima seletiva é terça 08/09/2026. 4. Restaurar a data automática do aparelho. | | | | |
| VAL-05 | CU03 / RF04 | Pontos de descarte | Lista só os pontos do município escolhido, com endereço, horário e materiais (com limite quando houver). | 1. Início > Pontos de descarte. 2. Conferir que só aparecem pontos de Camboriú. 3. Abrir a lista e ver endereço, horário e materiais (e limites, como 'até 5 kg'). 4. Trocar o local para Itajaí e conferir que a lista muda. | | | | |
| VAL-06 | CU03 / RF07 | Filtro por material | Ao tocar em Pilhas e baterias, só aparecem pontos que aceitam pilhas; aparece o aviso de RN04. | 1. Na lista de pontos, tocar no filtro 'Pilhas e baterias'. 2. Conferir que só ficam pontos que aceitam pilhas. 3. Ver o aviso de RN04 (pilhas e óleo só em ponto de descarte). 4. Tocar de novo no filtro para limpar. | | | | |
| VAL-07 | CU03 / RF05 | Abrir rota | No detalhe do ponto, 'Abrir rota' abre o app de mapas no local. | 1. Tocar num ponto da lista. 2. Conferir endereço, horário, materiais e coordenadas. 3. Tocar em 'Abrir rota'. 4. Conferir que o app de mapas abre apontando para o local. | | | | |
| VAL-08 | CU04 / RF08 | Guia: busca | Buscar 'caixa de leite' (e 'CAIXA LEITE') encontra o item; buscar algo inexistente mostra 'Nada encontrado'. | 1. Início > Guia de separação. 2. Digitar 'caixa de leite' e ver 'Caixa de leite (longa vida)'. 3. Apagar e digitar 'CAIXA LEITE' (maiúsculas, fora de ordem) e ver o mesmo item. 4. Digitar 'xyzabc' e ver 'Nada encontrado'. 5. Digitar 'refrigerante' e ver 'Garrafa PET' (busca por sinônimo). | | | | |
| VAL-09 | CU04 / RNF03 | Guia sem internet | Com o aparelho em modo avião, o guia funciona normalmente. | 1. Ligar o modo avião (Wi-Fi e dados desligados). 2. Abrir o app e o Guia. 3. Repetir a busca por 'caixa de leite' e abrir um item (ex.: 'Pilhas'). 4. Conferir que o destino e a orientação aparecem. | | | | |
| VAL-10 | CU06 / RF09 | Registrar descarte | Registrar PAPEL credita 5 pontos; ELETRÔNICOS credita 15. | 1. Início > Meus pontos > Registrar descarte. 2. Tocar em 'Papel' e ver 'Você ganhou 5 pontos!'. 3. Voltar a 'Meus pontos': saldo 5. 4. Registrar 'Eletrônicos' e ver 'Você ganhou 15 pontos!'. 5. Conferir que 'Roupas e tecidos' não aparece na lista (não pontua). | | | | |
| VAL-11 | CU06 / RN09 | Limite diário | O 4º registro no mesmo dia mostra o aviso de limite e não credita pontos. | 1. Com 2 descartes já registrados no dia (VAL-10), registrar mais 1 (3º): credita. 2. Registrar o 4º: ver 'Limite de 3 descartes por hoje atingido'. 3. Voltar a 'Meus pontos' e conferir que o saldo não mudou. | | | | |
| VAL-12 | CU07 / RF10 | Saldo e histórico | O saldo é a soma dos pontos e o histórico mostra do mais recente para o mais antigo. | 1. Em 'Meus pontos', conferir o saldo contra a soma dos pontos do histórico. 2. Conferir que o histórico mostra material, pontos e data (dd/MM/aaaa), com o mais recente no topo. 3. Fechar e abrir o app: saldo e histórico continuam iguais. | | | | |
| VAL-13 | CU08 / RF11 | Dicas | Mostra a dica do dia e a lista de dicas. | 1. Início > Dicas. 2. Conferir a dica do dia em destaque. 3. Conferir a lista de dicas. | | | | |
| VAL-14 | RNF01 | Dois toques | A partir do Início, Calendário, Pontos e Guia abrem com 1 toque. | 1. No Início, tocar em 'Calendário' e conferir que a tela abriu (1 toque). 2. Voltar e repetir com 'Pontos de descarte', 'Guia de separação', 'Meus pontos', 'Dicas' e 'Lembrete da coleta'. | | | | |
| VAL-15 | RNF04 | Desempenho | A lista de pontos aparece em até 3 s (cronometre no celular). | 1. Fechar o app. 2. Abrir, tocar em 'Pontos de descarte' e cronometrar do toque até a lista aparecer. 3. Repetir 3 vezes e anotar o maior tempo. | | | | |
| VAL-16 | RNF02 | Versões do Android | O app instala e abre em um aparelho/emulador Android 9 (API 28) ou superior. | 1. Instalar o APK em um emulador API 28 (ou no aparelho mais antigo do grupo). 2. Abrir e navegar pelo Início. 3. Anotar a versão do Android usada. | | | | |
| VAL-17 | CU05 / RF02 | Lembrete da coleta | Ao ligar o lembrete aparece 'Próximo aviso' com a véspera da próxima coleta no horário escolhido; ao desligar, o aviso some; a notificação chega na véspera. | 1. Início > Lembrete da coleta (com local escolhido). 2. Escolher um horário e ligar 'Lembrar na véspera'. 3. Conferir 'Próximo aviso: dd/MM/aaaa às HH:mm' = véspera da próxima coleta (conferir no Calendário). 4. Desligar e conferir que o aviso some. 5. Para ver a notificação: mudar a data do aparelho para a véspera e o horário para poucos minutos antes do aviso, ligar o lembrete e esperar; depois restaurar a data automática. | | | | |

## Resumo da execução (preencher no fim)

| Total de casos | Aprovados | Reprovados | Defeitos encontrados | Defeitos corrigidos | Quem executou | Data |
|---|---|---|---|---|---|---|
| 17 | | | | | | |
