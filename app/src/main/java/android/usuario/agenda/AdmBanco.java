package android.usuario.agenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdmBanco extends SQLiteOpenHelper{

    public AdmBanco(Context context) {
        super(context, "agenda.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table agenda (" +
                "_id integer primary key autoincrement not null," +
                "descricao text," +
                "tipo text," +
                "hora text," +
                "data text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int e, int i) {

    }
}
