package com.example.quizbandeiras;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Telaquiz extends AppCompatActivity {


    // Variáveis globais
    List<Questao> questoes = new ArrayList<>();
    ImageView imgplaca;
    Button btn1, btn2, btn3, btn4;
    int cont = 0, acertos = 0;
    TextView textview3, textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telaquiz);

        // Ajuste de preenchimento para as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        imgplaca = findViewById(R.id.imgplaca);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        textview3 = findViewById(R.id.textview3);
        textView4 = findViewById(R.id.textView4);

        // 2. ADICIONAR AS QUESTÕES (Usando os IDs do seu drawable)
        questoes.add(new Questao(R.drawable.africadosul, "África do Sul", "Jamaica", "Vanuatu", "Guiana"));
        questoes.add(new Questao(R.drawable.albania, "Albânia", "Montenegro", "Vietnã", "Marrocos"));
        questoes.add(new Questao(R.drawable.armenia, "Armênia", "Colômbia", "Lituânia", "Maurício"));
        questoes.add(new Questao(R.drawable.belize, "Belize", "Guatemala", "Laos", "Camboja"));
        questoes.add(new Questao(R.drawable.brunei, "Brunei", "Tailândia", "Butão", "Mongólia"));
        questoes.add(new Questao(R.drawable.camaroes, "Camarões", "Senegal", "Guiné", "Gana"));
        questoes.add(new Questao(R.drawable.canada, "Canadá", "Peru", "Áustria", "Indonésia"));
        questoes.add(new Questao(R.drawable.catar, "Catar", "Bahrein", "Jordânia", "Kuwait"));
        questoes.add(new Questao(R.drawable.mocambique, "Moçambique", "Eritreia", "Zimbábue", "Angola"));
        questoes.add(new Questao(R.drawable.barbados, "Barbados", "Ucrânia", "Bahamas", "Santa Lúcia"));

        // 3. EMBARALHAR AS PERGUNTAS
        Collections.shuffle(questoes);
        btn1.setTextColor(Color.WHITE);
        btn2.setTextColor(Color.WHITE);
        btn3.setTextColor(Color.WHITE);
        btn4.setTextColor(Color.WHITE);
        // 4. CHAMAR A PRIMEIRA QUESTÃO
        atualizarTela();
    }

    public void atualizarTela() {
        if (cont < questoes.size()) {
            Questao q = questoes.get(cont);

            // Define a imagem da placa
            imgplaca.setImageResource(q.getIdimagem());

            // Cria e embaralha as alternativas (Botões)
            List<String> alternativas = new ArrayList<>();
            alternativas.add(q.getRespostaCorreta());
            alternativas.add(q.getRespostaErrada1());
            alternativas.add(q.getRespostaErrada2());
            alternativas.add(q.getRespostaErrada3());

            Collections.shuffle(alternativas);

            // Define o texto nos botões
            btn1.setText(alternativas.get(0));
            btn2.setText(alternativas.get(1));
            btn3.setText(alternativas.get(2));
            btn4.setText(alternativas.get(3));

            // Atualiza os textos de progresso e acertos
            textview3.setText("Questão " + (cont + 1) + " de " + questoes.size());
            textView4.setText("Acertos: " + acertos);
        }
    }


    public void verificarResposta(View view) {
        final Button btnClicado = (Button) view;
        String respostaEscolhida = btnClicado.getText().toString();
        String respostaCorreta = questoes.get(cont).getRespostaCorreta();
        final ColorStateList corOriginal = ColorStateList.valueOf(Color.parseColor("#005792"));

        // 1. Define a cor do feedback (Verde ou Vermelho)
        if (respostaEscolhida.equals(respostaCorreta)) {
            acertos++;
            btnClicado.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        } else {
            btnClicado.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }

        // 2. BLOQUEIA os botões para o usuário não clicar em outro enquanto espera
        btn1.setEnabled(false); btn2.setEnabled(false);
        btn3.setEnabled(false); btn4.setEnabled(false);

        // 3. O "HANDLER" é o seu sleep que funciona
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Tudo aqui dentro roda DEPOIS de 700ms

                // Volta a cor original
                btn1.setBackgroundTintList(corOriginal);
                btn2.setBackgroundTintList(corOriginal);
                btn3.setBackgroundTintList(corOriginal);
                btn4.setBackgroundTintList(corOriginal);

                // Reativa os botões
                btn1.setEnabled(true); btn2.setEnabled(true);
                btn3.setEnabled(true); btn4.setEnabled(true);

                cont++;

                if (cont < questoes.size()) {
                    atualizarTela();
                } else {
                    Intent intent = new Intent(Telaquiz.this, TelaAcertos.class);
                    intent.putExtra("acertos", acertos);
                    startActivity(intent);
                    finish();
                }
            }
        }, 400); // Esse é o tempo que a cor fica na tela
    }
}