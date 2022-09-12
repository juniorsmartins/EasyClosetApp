package br.com.devvader.easycloset.recursos.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.devvader.easycloset.domain.entities.RoupaEntity;

@Dao
public interface RoupaDAORoom {

    @Insert
    void insert(RoupaEntity roupaEntity);

    @Delete
    void delete(RoupaEntity roupaEntity);

    @Update
    void update(RoupaEntity roupaEntity);

    @Query("SELECT * FROM roupas WHERE id_roupa = :id")
    RoupaEntity queryForId(long id);

    @Query("SELECT * FROM roupas ORDER BY id_roupa DESC")
    List<RoupaEntity> queryAll();
}
