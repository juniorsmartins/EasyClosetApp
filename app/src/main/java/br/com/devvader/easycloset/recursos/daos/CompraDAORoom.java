package br.com.devvader.easycloset.recursos.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.devvader.easycloset.domain.entities.CompraEntity;

@Dao
public interface CompraDAORoom {

    @Insert
    void insert(CompraEntity compraEntity);

    @Delete
    void delete(CompraEntity compraEntity);

    @Update
    void update(CompraEntity compraEntity);

    @Query("SELECT * FROM compras WHERE compra_id = :id")
    CompraEntity queryForId(Long id);

    @Query("SELECT * FROM compras ORDER BY compra_id DESC")
    List<CompraEntity> queryAll();
}
