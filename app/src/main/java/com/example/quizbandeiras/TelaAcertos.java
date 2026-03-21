package com.example.quizbandeiras;



import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.TextView;



import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;

import androidx.core.view.ViewCompat;

import androidx.core.view.WindowInsetsCompat;



import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class TelaAcertos extends AppCompatActivity {
        Intent it;
        EditText edtResultado;
        Button btnprincipal;

        // Tornamos a lista STATIC para que ela não seja apagada ao sair da tela
        // Assim ela acumula os resultados de várias partidas
        private static List<Resultado> listaHistorico = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tela_acertos);

            edtResultado = findViewById(R.id.edtResultado);

            // 1. Pega os acertos que vieram da Intent (Chave: "acertos")
            int acertos = getIntent().getIntExtra("acertos", -1);

            // 2. SÓ entra aqui se o usuário veio da TelaQuiz (acertos != -1)
            if (acertos != -1) {
                // BUSCA O NOME QUE FOI SALVO NA TELANOME
                String nomeDoUsuario = getSharedPreferences("DADOS_QUIZ", MODE_PRIVATE)
                        .getString("nome_jogador", "Jogador Desconhecido");

                // Cria o objeto com os dados REAIS
                Resultado totalAcertos = new Resultado(acertos, nomeDoUsuario);

                // Adiciona na lista estática
                listaHistorico.add(totalAcertos);

                // Ordena Decrescente (Maior acerto primeiro)
                Collections.sort(listaHistorico, (r1, r2) -> Integer.compare(r2.getAcertos(), r1.getAcertos()));
            }

            // 3. Atualiza o campo de texto com a lista completa
            exibirNoEditText();
        }

        private void exibirNoEditText() {
            StringBuilder sb = new StringBuilder();

            if (listaHistorico.isEmpty()) {
                sb.append("Nenhum histórico registrado.");
            } else {
                for (Resultado r : listaHistorico) {
                    sb.append("🏆 JOGADOR: ").append(r.getNome().toUpperCase()) // Nome em caixa alta
                            .append("\n🎯 PONTOS: ").append(r.getAcertos())
                            .append("\n--------------------------\n");
                }
            }

            edtResultado.setText(sb.toString());
        }

        public void principal(View view){
            Intent it = new Intent(this, TelaNome.class);

            // CLEAR_TOP: Se a TelaNome já estiver na pilha, ele volta pra ela e fecha as outras
            // SINGLE_TOP: Evita criar uma nova instância se ela já for a principal
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(it);
            finish(); // Fecha a TelaAcertos atual
        }

        public void limpar(View view){
            // Como a lista é static, para limpar o histórico real, temos que limpar a lista
            listaHistorico.clear();
            edtResultado.setText("");
        }
}
