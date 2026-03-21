package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class TelaNome extends AppCompatActivity {
    EditText edtNome;
    Intent it;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_nome);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNome = findViewById(R.id.edtNome);
        btn1 = findViewById(R.id.btnJogar);
        btn2 = findViewById(R.id.btnVoltar);

        // Estado inicial: Invisível e desativado
        btn1.setVisibility(View.INVISIBLE);
        btn1.setEnabled(false);

        // O "Vigia" do texto
        edtNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Pega o que foi digitado e remove espaços vazios
                String nome = s.toString().trim();

                // Se não estiver vazio, mostra o botão. Se apagar tudo, esconde de novo.
                if (!nome.isEmpty()) {
                    btn1.setVisibility(View.VISIBLE);
                    btn1.setEnabled(true);
                } else {
                    btn1.setVisibility(View.INVISIBLE);
                    btn1.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

    }

    public void telaquestao(View view) {
        // Opcional: passar o nome para a próxima tela
        String nomeUsuario = edtNome.getText().toString().trim();

        // 2. Salva no "Armário Global" (SharedPreferences)
        // "DADOS_QUIZ" é o nome do seu arquivo de preferências
        getSharedPreferences("DADOS_QUIZ", MODE_PRIVATE)
                .edit()
                .putString("nome_jogador", nomeUsuario)
                .apply(); // O apply() salva em segundo plano, sem travar o app

        // 3. Inicia a Telaquiz normalmente
        // Note que aqui não precisamos mais do putExtra para o nome!
        Intent it = new Intent(this, Telaquiz.class);
        startActivity(it);
    }

    public void voltar(View view) {

        Intent it = new Intent(this, MainActivity.class);

        // Limpa a pilha para garantir que a MainActivity seja a única aberta
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        startActivity(it);
        finish(); // Mata a TelaNome
    }
}