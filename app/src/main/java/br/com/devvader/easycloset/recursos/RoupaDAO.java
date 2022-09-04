package br.com.devvader.easycloset.recursos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.com.devvader.easycloset.domain.RoupaEntity;

public final class RoupaDAO {

    public static final String TABELA = "ROUPAS";
    public static final String ID = "ID";
    public static final String TIPO = "TIPO";
    public static final String TECIDO = "TECIDO";
    public static final String COR_PRINCIPAL = "COR_PRINCIPAL";
    public static final String TAMANHO = "TAMANHO";

    private RoupaDatabase conexaoDatabase;
    public List<RoupaEntity> listaDeRoupas;

    public RoupaDAO(RoupaDatabase roupaDatabase) {
        conexaoDatabase = roupaDatabase;
        listaDeRoupas = new ArrayList<>();
    }

    public void criarTabela(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TIPO + " TEXT NOT NULL, " +
                TECIDO + " TEXT NOT NULL, " +
                COR_PRINCIPAL + " TEXT NOT NULL, " +
                TAMANHO + " TEXT NOT NULL)";

        sqLiteDatabase.execSQL(sql);
    }

    public void apagarTabela(SQLiteDatabase sqLiteDatabase) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        sqLiteDatabase.execSQL(sql);
    }

    public boolean inserir(RoupaEntity roupaEntity) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(TIPO, roupaEntity.getTipo());
        contentValues.put(TECIDO, roupaEntity.getTecido());
        contentValues.put(COR_PRINCIPAL, roupaEntity.getCorPrincipal());
        contentValues.put(TAMANHO, roupaEntity.getTamanho());

        Long id = conexaoDatabase.getWritableDatabase().insert(TABELA, null, contentValues);
        roupaEntity.setId(id);

        listaDeRoupas.add(roupaEntity);
        ordenarListaDeRoupas();
        return true;
    }

    public boolean atualizar(RoupaEntity roupaEntity) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(TIPO, roupaEntity.getTipo());
        contentValues.put(TECIDO, roupaEntity.getTecido());
        contentValues.put(COR_PRINCIPAL, roupaEntity.getCorPrincipal());
        contentValues.put(TAMANHO, roupaEntity.getTamanho());

        String[] args = {String.valueOf(roupaEntity.getId())};
        conexaoDatabase.getWritableDatabase().update(TABELA, contentValues, ID + " = ?", args);
        ordenarListaDeRoupas();
        return true;
    }

    public boolean excluir(RoupaEntity roupaEntity) {
        String[] args = {String.valueOf(roupaEntity.getId())};
        conexaoDatabase.getWritableDatabase().delete(TABELA, ID + " = ?", args);
        listaDeRoupas.remove(roupaEntity);
        ordenarListaDeRoupas();
        return true;
    }

    public void carregarTudo() {
        listaDeRoupas.clear();

        String sql = "SELECT * FROM " + TABELA;

        Cursor cursor = conexaoDatabase.getReadableDatabase().rawQuery(sql, null);

        int colunaId = cursor.getColumnIndex(ID);
        int colunaTipo = cursor.getColumnIndex(TIPO);
        int colunaTecido = cursor.getColumnIndex(TECIDO);
        int colunaCorPrincipal = cursor.getColumnIndex(COR_PRINCIPAL);
        int colunaTamanho = cursor.getColumnIndex(TAMANHO);

        while(cursor.moveToNext()) {

            RoupaEntity roupaEntity = new RoupaEntity();
            roupaEntity.setId(cursor.getLong(colunaId));
            roupaEntity.setTipo(cursor.getString(colunaTipo));
            roupaEntity.setTecido(cursor.getString(colunaTecido));
            roupaEntity.setCorPrincipal(cursor.getString(colunaCorPrincipal));
            roupaEntity.setTamanho(cursor.getString(colunaTamanho));

            listaDeRoupas.add(roupaEntity);
        }
        ordenarListaDeRoupas();
        cursor.close();
    }

    public void ordenarListaDeRoupas() {
        Collections.sort(listaDeRoupas, (RoupaEntity o1, RoupaEntity o2) -> {
            if(o1.getId() > o2.getId()) {
                return -1;
            }else if(o1.getId() < o2.getId()){
                return 1;
            }else{
                return 0;
            }
        });
    }
}
