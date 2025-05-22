package com.example.fichasaude;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;




import java.util.ArrayList;

import database.FichaDBHelper;

public class HistoricoActivity extends AppCompatActivity {

    private ListView listView;
    private FichaDBHelper dbHelper;
    private ArrayList<Integer> listaIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        listView = findViewById(R.id.listViewFichas);
        dbHelper = new FichaDBHelper(this);
        carregarFichas();

        listView.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            int fichaId = listaIds.get(position);
            Intent intent = new Intent(this, VisualizacaoActivity.class);
            intent.putExtra("ficha_id", fichaId);
            startActivity(intent);
        });
    }

    private void carregarFichas() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome FROM fichas", null);

        ArrayList<String> listaNomes = new ArrayList<>();
        listaIds = new ArrayList<>();

        while (cursor.moveToNext()) {
            listaNomes.add(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            listaIds.add(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
        }

        cursor.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaNomes);
        listView.setAdapter(adapter);
    }
}
