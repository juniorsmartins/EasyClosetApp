package br.com.devvader.easycloset.recursos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class RoupaDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "EasyCloset.db";
    private static final int DB_VERSION = 1;
    private static RoupaDatabase instance;

    private Context context;
    private RoupaDAO roupaDAO;

    public static RoupaDatabase getInstance(Context context) {
        if(instance == null) {
            instance = new RoupaDatabase(context);
        }
        return instance;
    }

    private RoupaDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        roupaDAO = new RoupaDAO(this);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        roupaDAO.criarTabela(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            roupaDAO.apagarTabela(db);
            onCreate(db);
        }
    }

    public RoupaDAO getRoupaDAO() {
        return roupaDAO;
    }
}
