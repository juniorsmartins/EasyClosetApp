package br.com.devvader.easycloset.recursos;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import br.com.devvader.easycloset.domain.UsuarioEntity;

@Database(entities = {UsuarioEntity.class}, version = 1, exportSchema = false)
public abstract class UsuarioDatabaseRoom extends RoomDatabase {

    private static final String DB_NAME = "room1.db";

    public abstract UsuarioDAORoom usuarioDAORoom();

    private static UsuarioDatabaseRoom usuarioDatabaseRoom;

    public static UsuarioDatabaseRoom getUsuarioDatabaseRoom(final Context context) {
        if(usuarioDatabaseRoom == null) {
            synchronized (UsuarioDatabaseRoom.class) {
                if(usuarioDatabaseRoom == null) {
                    usuarioDatabaseRoom = Room.databaseBuilder(context, UsuarioDatabaseRoom.class, DB_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return usuarioDatabaseRoom;
    }
}
