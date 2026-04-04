package com.example.quizbandeiras;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    RadioButton btn1, btn2, btn3, btn4;
    RadioGroup grupoAlternativas;
    Button btnproxima;
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

        grupoAlternativas = findViewById(R.id.grupoAlternativas);
        imgplaca = findViewById(R.id.imgplaca);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btnproxima = findViewById(R.id.btnproxima);
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
        int idSelecionado = grupoAlternativas.getCheckedRadioButtonId();
        if (idSelecionado == -1) return;

        final RadioButton btnVerificar = findViewById(idSelecionado);
        String respostaEscolhida = btnVerificar.getText().toString();
        String respostaCorreta = questoes.get(cont).getRespostaCorreta();

        // 1. Feedback Visual usando Cores do Sistema para acerto/erro
        if (respostaEscolhida.equals(respostaCorreta)) {
            acertos++;
            btnVerificar.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        } else {
            btnVerificar.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }

        bloquearBotoes(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 1. LIMPA A SELEÇÃO DO GRUPO (Isso aciona o 'state_checked="false"' do seu XML)
                grupoAlternativas.clearCheck();

                // 2. REMOVE A TINTA (VERDE/VERMELHA) DE TODOS
                // Ao definir como null, o Android volta a olhar para o seu arquivo <selector>
                btn1.setBackgroundTintList(null);
                btn2.setBackgroundTintList(null);
                btn3.setBackgroundTintList(null);
                btn4.setBackgroundTintList(null);

                // 3. REAPLICA O SEU SELECTOR (Caso tenha sido sobrescrito)
                // Substitua 'seu_arquivo_selector' pelo nome do seu arquivo XML de fundo
                btn1.setBackgroundResource(R.drawable.botao_selecionado);
                btn2.setBackgroundResource(R.drawable.botao_selecionado);
                btn3.setBackgroundResource(R.drawable.botao_selecionado);
                btn4.setBackgroundResource(R.drawable.botao_selecionado);

                // 4. LIBERA OS BOTÕES
                bloquearBotoes(true);

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
        }, 600);
    }

    // Método para habilitar/desabilitar a interação
    private void bloquearBotoes(boolean estado) {
        btn1.setEnabled(estado);
        btn2.setEnabled(estado);
        btn3.setEnabled(estado);
        btn4.setEnabled(estado);
        btnproxima.setEnabled(estado);
    }
}
