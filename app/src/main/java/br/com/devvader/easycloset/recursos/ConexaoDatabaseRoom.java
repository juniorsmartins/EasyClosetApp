package br.com.devvader.easycloset.recursos;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.devvader.easycloset.domain.RoupaEntity;
import br.com.devvader.easycloset.domain.UsuarioEntity;

@Database(entities = {UsuarioEntity.class, RoupaEntity.class}, version = 2, exportSchema = false)
public abstract class ConexaoDatabaseRoom extends RoomDatabase {

    private static final String DB_NAME = "easycloset.db";
    private static ConexaoDatabaseRoom conexaoDatabaseRoom;

    public static ConexaoDatabaseRoom getConexaoDatabaseRoom(final Context context) {
        if(conexaoDatabaseRoom == null) {
            synchronized (ConexaoDatabaseRoom.class) {
                if(conexaoDatabaseRoom == null) {
                    conexaoDatabaseRoom = Room.databaseBuilder(context, ConexaoDatabaseRoom.class, DB_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return conexaoDatabaseRoom;
    }

    public abstract UsuarioDAORoom usuarioDAORoom();
    public abstract RoupaDAORoom roupaDAORoom();
}
