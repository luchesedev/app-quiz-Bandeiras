🚩 Quiz de Bandeiras - Android Java
Este é um projeto acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos (POO) e Desenvolvimento Mobile na FATEC Guarulhos. O aplicativo desafia o usuário a identificar bandeiras de diferentes países, mantendo um histórico de pontuação ordenado.

🚀 Funcionalidades
Identificação de Usuário: Sistema de captura de nome com validação em tempo real (TextWatcher).

Mecânica de Quiz: Questões aleatórias (Collections.shuffle) com feedback visual imediato (cores verde/vermelho).

Persistência de Dados Local: Uso de SharedPreferences para manter o nome do usuário entre diferentes telas.

Histórico de Partidas: Ranking persistente em memória (static List) que exibe os resultados em ordem decrescente (maiores pontuações no topo).

Navegação Inteligente: Gerenciamento da pilha de atividades (Back Stack) usando Flags de Intent para evitar consumo excessivo de memória.

🛠️ Tecnologias Utilizadas
Linguagem: Java

IDE: Android Studio

Layout: XML (ConstraintLayout, LinearLayout, FrameLayout)

Componentes:

SharedPreferences para persistência simples.

Intent Flags para controle de fluxo.

Collections Framework para ordenação de objetos.

Handler para controle de tempo nas transições.

🏗️ Estrutura de Classes (POO)
O projeto segue princípios de POO para organização do código:

Questao.java: Modelo que armazena o ID do recurso da imagem e as alternativas.

Resultado.java: Modelo que associa o nome do jogador à sua pontuação, utilizado no ranking.

Telaquiz.java: Gerencia a lógica do jogo, embaralhamento e verificação de respostas.

TelaAcertos.java: Responsável por processar e exibir o ranking ordenado.

📱 Demonstração Técnica
O aplicativo foi otimizado para evitar o empilhamento desnecessário de Activitys:

"Ao clicar em 'Jogar Novamente', o app utiliza a flag FLAG_ACTIVITY_CLEAR_TOP, limpando a memória e garantindo uma navegação fluida."
