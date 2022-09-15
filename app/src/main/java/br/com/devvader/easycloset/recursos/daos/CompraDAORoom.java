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

    @Query("SELECT * FROM compras " +
            "WHERE id = :id")
    CompraEntity queryForId(Long id);

    @Query("SELECT * FROM compras " +
            "ORDER BY id DESC")
    List<CompraEntity> queryAll();

    @Query("SELECT * FROM compras " +
            "ORDER BY roupa_id DESC")
    List<CompraEntity> buscarComprasOrdenadoDecrescentePorFkDeRoupas();

    @Query("SELECT * FROM compras " +
            "WHERE roupa_id = :id")
    CompraEntity queryForIdRoupa(Long id);

    @Query("SELECT c.* FROM compras AS c " +
            "JOIN roupas AS r " +
            "ON c.roupa_id = r.id " +
            "WHERE c.roupa_id = :roupaId")
    CompraEntity buscarComprasComRoupasPorIdDeRoupa(Long roupaId);
}
