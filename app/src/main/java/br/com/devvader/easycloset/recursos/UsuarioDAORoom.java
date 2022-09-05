package br.com.devvader.easycloset.recursos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

    @Query("SELECT * FROM UsuarioEntity WHERE idUsuario = :id")
    UsuarioEntity queryForId(long id);

    @Query("SELECT * FROM UsuarioEntity ORDER BY idUsuario ASC")
    List<UsuarioEntity> queryAll();
}
