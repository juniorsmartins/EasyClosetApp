package br.com.devvader.easycloset.recursos;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.devvader.easycloset.domain.UsuarioEntity;

@Database(entities = {UsuarioEntity.class}, version = 1, exportSchema = false)
public abstract class UsuarioDatabaseRoom extends RoomDatabase {

    private static final String DB_NAME = "EasyCloset.db";

    public abstract UsuarioDAORoom usuarioDAORoom();

    private static UsuarioDatabaseRoom instance;

    public static UsuarioDatabaseRoom getUsuarioDatabaseRoom(final Context context) {
        if(instance == null) {
            synchronized (UsuarioDatabaseRoom.class) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context, UsuarioDatabaseRoom.class, DB_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }
}
