# Ambiente de desenvolvimento


**1. Instalar os programas**
- Git — git-scm.com (instalação padrão).
- JDK 17 (Temurin) — adoptium.net, versão 17 (LTS). Depois de instalar, abra um terminal novo e rode: `java -version` (tem que aparecer 17).
- Android Studio (versão estável) — developer.android.com/studio. Na primeira abertura aceite baixar o Android SDK que ele sugerir.
- VS Code (opcional) — code.visualstudio.com. Extensões úteis: 'GitHub Pull Requests' e 'Kotlin' (a do Kotlin ainda está em alpha: se falhar, use o Android Studio para editar; os comandos são os mesmos).

**2. Conta e identidade no GitHub**
- Aceite o convite para o repositório (o Integrante 1 envia por e-mail/GitHub).
- No GitHub: Settings > Emails > marque 'Keep my email addresses private' e use o e-mail 'noreply' que aparece lá.
- No terminal: `git config --global user.name "seu-usuario-do-github"` e `git config --global user.email "SEU-ID+usuario@users.noreply.github.com"` (o e-mail noreply do passo anterior).

**3. Baixar o projeto**
- No terminal, numa pasta sua: `git clone https://github.com/FelipeCJBoB/coleta-seletiva-vale-do-itajai.git`
- Entre na pasta: `cd coleta-seletiva-vale-do-itajai`

**4. Abrir e testar**
- Android Studio: File > Open > escolha a pasta clonada. Aguarde 'Gradle sync' (1ª vez demora ~10 min; aceite instalar o que ele pedir, como a plataforma do Android SDK).
- No terminal (Windows PowerShell): `.\gradlew.bat testDebugUnitTest` · (Git Bash/Mac/Linux): `./gradlew testDebugUnitTest`. Esperado: 'BUILD SUCCESSFUL'.
- Gerar o APK: `.\gradlew.bat assembleDebug` (o arquivo fica em composeApp/build/outputs/apk/debug/).

**5. Ver o app rodando**
- Android Studio: Tools > Device Manager > Create Device > Pixel 7 > imagem Android 14 (API 34, baixe se pedir) > Finish.
- Clique no ▶ (Run 'composeApp'). Vai aparecer uma tela escrita 'Início: em construção' com um botão Voltar. É isso mesmo: o app será construído nas próximas semanas.
- Alternativa com celular Android: ative 'Opções do desenvolvedor' + 'Depuração USB' e escolha o aparelho no lugar do emulador. Ou baixe o APK gerado pelo CI (aba Actions > a última execução > 'apk-debug').

**6. Seu primeiro Pull Request**
- `git checkout -b docs/ambiente-integrante-N` (troque N pelo seu número).
- Abra docs/CHECKLIST-AMBIENTE.md e troque `[ ]` por `[x]` SÓ na linha do seu Integrante.
- `git add .` · `git commit -m "docs: ambiente do Integrante N pronto"` · `git push -u origin docs/ambiente-integrante-N`
- No GitHub abra o Pull Request, marque o Integrante 1 como revisor. Quando ele fizer o merge, você está pronto.
- Avise no grupo: 'Integrante N: ambiente ok'.

## Comandos do dia a dia

| O que fazer | Windows (PowerShell) | Git Bash / Mac / Linux |
|---|---|---|
| Rodar todos os testes (JVM, sem emulador) | `.\gradlew.bat testDebugUnitTest` | `./gradlew testDebugUnitTest` |
| Rodar só uma classe de teste | `.\gradlew.bat testDebugUnitTest --tests "*ProximaColetaTest"` | `./gradlew testDebugUnitTest --tests "*ProximaColetaTest"` |
| Gerar o APK de teste | `.\gradlew.bat assembleDebug` | `./gradlew assembleDebug` |
| Compilar os testes de interface | `.\gradlew.bat compileDebugAndroidTestKotlin` | `./gradlew compileDebugAndroidTestKotlin` |
| Rodar os testes de interface (emulador ligado) | `.\gradlew.bat connectedDebugAndroidTest` | `./gradlew connectedDebugAndroidTest` |

## Problemas comuns

- `java -version` não mostra 17: feche e reabra o terminal; confira o JDK 17 do Temurin; no Android Studio, File > Settings > Build Tools > Gradle > Gradle JDK = 17.
- 'SDK location not found': abra o projeto pelo Android Studio uma vez (ele cria o local.properties).
- Gradle demorando na primeira vez: normal (baixa dependências).
- iOS: ninguém tem Mac; os testes de iOS rodam no CI.
- Teste falhando que você acabou de escrever é o esperado no TDD (vermelho). Só é problema se continuar vermelho depois do código.
- Conflito de merge: quase sempre é por mexer fora do seu bloco. Avise o Integrante 1 antes de resolver.
