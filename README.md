# 🚩 Quiz de Bandeiras - Android Java

---

### 📖 Sobre o Projeto
Este é um projeto acadêmico desenvolvido para a disciplina de **Programação Orientada a Objetos (POO)** e **Desenvolvimento Mobile** na **FATEC Guarulhos**. O aplicativo desafia o usuário a identificar bandeiras de diferentes países, mantendo um histórico de pontuação ordenado.

---

## 🚀 Funcionalidades

* **👤 Identificação de Usuário:** Sistema de captura de nome com validação em tempo real (`TextWatcher`).
* **🎮 Mecânica de Quiz:** Questões aleatórias (`Collections.shuffle`) com feedback visual imediato (cores verde/vermelho).
* **💾 Persistência de Dados:** Uso de `SharedPreferences` para manter o nome do usuário entre diferentes telas.
* **🏆 Histórico de Partidas:** Ranking persistente em memória (`static List`) que exibe os resultados em ordem decrescente.
* **🔄 Navegação Inteligente:** Gerenciamento da pilha de atividades (*Back Stack*) usando `Flags de Intent` para otimização de memória.

---

## 🛠️ Tecnologias e Ferramentas

<div style="display: inline_block">
  <img align="center" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg">
  <img align="center" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/androidstudio/androidstudio-original.svg">
  <img align="center" height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/xml/xml-original.svg">
</div>

<br>

* **Linguagem:** Java
* **IDE:** Android Studio
* **Layout:** XML (`ConstraintLayout`, `LinearLayout`, `FrameLayout`)
* **Componentes:** `SharedPreferences`, `Intent Flags`, `Collections Framework`, `Handler`.

---

## 🏗️ Estrutura de Classes (POO)

O projeto aplica conceitos fundamentais de POO para organização da lógica:

* `Questao.java`: Modelo para armazenamento do ID do recurso da imagem e alternativas.
* `Resultado.java`: Modelo que associa o nome do jogador à pontuação para o ranking.
* `Telaquiz.java`: Controller da lógica do jogo e verificação de respostas.
* `TelaAcertos.java`: View responsável por processar e exibir o ranking ordenado.

---

## 📱 Demonstração Técnica

O aplicativo foi otimizado para evitar o empilhamento desnecessário de *Activities*:

> "Ao clicar em 'Jogar Novamente', o app utiliza a flag **FLAG_ACTIVITY_CLEAR_TOP**, limpando a memória e garantindo uma navegação fluida."

---
