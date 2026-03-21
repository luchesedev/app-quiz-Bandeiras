package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaAcertos extends AppCompatActivity {
    Intent it;
    Button btnprincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_acertos);

        btnprincipal = findViewById(R.id.btninicio);

        // 1. Referencie o TextView onde você vai mostrar o resultado
        TextView tvResultado = findViewById(R.id.textView);

        // 2. A LÓGICA: Pegue a Intent e extraia o valor usando a mesma chave "acertos"
        // O 0 é o valor padrão caso ele não encontre nada
        int totalAcertos = getIntent().getIntExtra("acertos", 0);

        // 3. Exiba o valor (não esqueça de concatenar com String para não dar erro)
        tvResultado.setText("" + totalAcertos);

    }
    public void principal(View view){
        it = new Intent(this, TelaNome.class);
        startActivity(it);
        finish(); // Adicione o finish para o usuário não voltar para a tela de acertos ao apertar o botão 'voltar'
    }

}