package br.com.devvader.easycloset.recursos.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.devvader.easycloset.domain.entities.RoupaEntity;
import br.com.devvader.easycloset.domain.entities.UsuarioEntity;
import br.com.devvader.easycloset.recursos.daos.RoupaDAORoom;
import br.com.devvader.easycloset.recursos.daos.UsuarioDAORoom;

@Database(entities = {UsuarioEntity.class, RoupaEntity.class}, version = 1, exportSchema = false)
public abstract class EasyClosetDatabaseRoom extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "easyclosetdatabaseroom.db";

    private static EasyClosetDatabaseRoom conexaoDatabaseRoom;

    public static EasyClosetDatabaseRoom getConexaoDatabaseRoom(Context context) {
        conexaoDatabaseRoom = Room
                .databaseBuilder(context, EasyClosetDatabaseRoom.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries() // permite a execução do room na thread principal - não é recomendado fazer
                .fallbackToDestructiveMigration() // destroi versão anterior do database - perde dados - não é recomendado - uso apenas em desenvolvimento
                .build();
        return conexaoDatabaseRoom;
    }

    public abstract UsuarioDAORoom getUsuarioDAORoom();
    public abstract RoupaDAORoom getRoupaDAORoom();
}
