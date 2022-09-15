package br.com.devvader.easycloset.domain.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

@Entity(tableName = "compras", foreignKeys = {@ForeignKey(entity = RoupaEntity.class,
        parentColumns = "id", childColumns = "roupa_id", onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE)})
public final class CompraEntity implements Serializable {

    // -------------------- ATRIBUTOS DE CLASSE -------------------- //
    private static final long serialVersionUID = 1L;

    // -------------------- ATRIBUTOS DE INSTÂNCIA -------------------- //
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;

    @NonNull
    @ColumnInfo(name = "valor_pago")
    private Double valorPago;

    @NonNull
    @ColumnInfo(name = "forma_pagamento")
    private String formaPagamento;

    //    @NonNull
    @ColumnInfo(name = "data_compra")
    private Calendar dataCompra;

    @ColumnInfo(name = "roupa_id", index = true)
    private Long roupaId;


    // -------------------- CONSTRUTORES -------------------- //
    public CompraEntity() {
    }


    // -------------------- GETTERS E SETTERS -------------------- //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(@NonNull Double valorPago) {
        this.valorPago = valorPago;
    }

    @NonNull
    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(@NonNull String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Long getRoupaId() {
        return roupaId;
    }

    public void setRoupaId(Long roupaId) {
        this.roupaId = roupaId;
    }
}
