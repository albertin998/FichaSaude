package database;




import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import java.util.*;

import model.FichaSaude;

public class FichaDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "fichas.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "fichas";

    public FichaDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, idade INTEGER, peso REAL, altura REAL, pressao TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long inserirFicha(FichaSaude ficha) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", ficha.getNome());
        values.put("idade", ficha.getIdade());
        values.put("peso", ficha.getPeso());
        values.put("altura", ficha.getAltura());
        values.put("pressao", ficha.getPressao());
        db.insert(TABLE_NAME, null, values);
        return 0;
    }

    public List<FichaSaude> listarFichas() {
        List<FichaSaude> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, "id DESC");

        while (cursor.moveToNext()) {
            FichaSaude ficha = new FichaSaude(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4),
                    cursor.getString(5)
            );
            lista.add(ficha);
        }
        cursor.close();
        return lista;
    }

    public FichaSaude buscarFichaPorId(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            return new FichaSaude(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4),
                    cursor.getString(5)
            );
        }
        cursor.close();
        return null;
    }
}


