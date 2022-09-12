package br.com.devvader.easycloset.recursos.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import br.com.devvader.easycloset.domain.entities.UsuarioEntity;

@Dao
public interface UsuarioDAORoom {

    @Insert
    void insert(UsuarioEntity usuarioEntity);

    @Delete
    void delete(UsuarioEntity usuarioEntity);

    @Update
    void update(UsuarioEntity usuarioEntity);

    @Query("SELECT * FROM usuarios WHERE id_usuario = :id")
    UsuarioEntity queryForId(long id);

    @Query("SELECT * FROM usuarios ORDER BY id_usuario DESC")
    List<UsuarioEntity> queryAll();
}
