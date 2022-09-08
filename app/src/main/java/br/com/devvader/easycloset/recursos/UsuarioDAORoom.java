package br.com.devvader.easycloset.recursos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import br.com.devvader.easycloset.domain.UsuarioEntity;

@Dao
public interface UsuarioDAORoom {

    @Insert
    long insert(UsuarioEntity usuarioEntity);

    @Delete
    void delete(UsuarioEntity usuarioEntity);

    @Update
    void update(UsuarioEntity usuarioEntity);

    @Query("SELECT * FROM usuarios WHERE id_usuario = :id")
    UsuarioEntity queryForId(long id);

    @Query("SELECT * FROM usuarios ORDER BY id_usuario ASC")
    List<UsuarioEntity> queryAll();
}
