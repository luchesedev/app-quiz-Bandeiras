package com.example.quizbandeiras;

public class Questao {

        private int Idimagem;
        private String respostaCorreta;
        private String respostaErrada1;
        private String respostaErrada2;
        private String respostaErrada3;

    public Questao(int Idimagem, String respostaCorreta, String respostaErrada1, String respostaErrada2, String respostaErrada3) {
        this.Idimagem = Idimagem;
        this.respostaCorreta = respostaCorreta;
        this.respostaErrada1 = respostaErrada1;
        this.respostaErrada2 = respostaErrada2;
        this.respostaErrada3 = respostaErrada3;
    }

        public int getIdimagem() {
        return Idimagem;
    }

        public String getRespostaCorreta() {
        return respostaCorreta;
    }

        public String getRespostaErrada1() {
        return respostaErrada1;
    }

        public String getRespostaErrada2() {
        return respostaErrada2;
    }

        public String getRespostaErrada3() {
        return respostaErrada3;
    }


    }
