package com.example.fichasaude;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import database.FichaDBHelper;
import model.FichaSaude;


public class CadastroActivity extends AppCompatActivity {

    EditText edtNome, edtIdade, edtPeso, edtAltura, edtPressao;
    Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.editNome);
        edtIdade = findViewById(R.id.editIdade);
        edtPeso = findViewById(R.id.editPeso);
        edtAltura = findViewById(R.id.editAltura);
        edtPressao = findViewById(R.id.editPressao);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(v -> {
            try {
                String nome = edtNome.getText().toString();
                int idade = Integer.parseInt(edtIdade.getText().toString());
                float peso = Float.parseFloat(edtPeso.getText().toString());
                float altura = Float.parseFloat(edtAltura.getText().toString());
                String pressao = edtPressao.getText().toString();

                FichaSaude ficha = new FichaSaude(nome, idade, peso, altura, pressao);
                FichaDBHelper db = new FichaDBHelper(this);
                long id = db.inserirFicha(ficha);

                Toast.makeText(this, "Ficha salva!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, VisualizacaoActivity.class);
                intent.putExtra("fichaId", (int) id);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                Toast.makeText(this, "Erro ao salvar a ficha: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
