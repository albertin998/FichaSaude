package com.example.fichasaude;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import database.FichaDBHelper;


public class VisualizacaoActivity extends AppCompatActivity {

    private TextView tvResultado;
    private FichaDBHelper dbHelper;
    private int fichaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao);

        tvResultado = findViewById(R.id.textViewInterpretacao);
        dbHelper = new FichaDBHelper(this);

        fichaId = getIntent().getIntExtra("ficha_id", -1);
        if (fichaId != -1) {
            mostrarDados(fichaId);
        }
    }

    private void mostrarDados(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM fichas WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            String nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
            int idade = cursor.getInt(cursor.getColumnIndexOrThrow("idade"));
            float peso = cursor.getFloat(cursor.getColumnIndexOrThrow("peso"));
            float altura = cursor.getFloat(cursor.getColumnIndexOrThrow("altura"));
            String pressao = cursor.getString(cursor.getColumnIndexOrThrow("pressao"));
            float imc = peso / (altura * altura);
            String interpretacao;

            if (imc < 18.5) interpretacao = "Abaixo do peso";
            else if (imc < 25) interpretacao = "Peso normal";
            else if (imc < 30) interpretacao = "Sobrepeso";
            else interpretacao = "Obesidade";

            tvResultado.setText(
                    "Nome: " + nome + "\n" +
                            "Idade: " + idade + "\n" +
                            "Peso: " + peso + " kg\n" +
                            "Altura: " + altura + " m\n" +
                            "Pressão: " + pressao + "\n\n" +
                            "IMC: " + String.format("%.2f", imc) + " (" + interpretacao + ")"
            );
        }
        cursor.close();
        db.close();
    }
}
