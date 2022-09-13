package br.com.devvader.easycloset.domain.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

import br.com.devvader.easycloset.domain.entities.enuns.EnumFormaPgto;

@Entity(tableName = "compras",
        foreignKeys = {@ForeignKey(entity = RoupaEntity.class,
                parentColumns = "roupa_id", childColumns = "id_roupa", onDelete = ForeignKey.CASCADE)}
)
public final class CompraEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "compra_id")
    private Long compraId;

    @NonNull
    @ColumnInfo(name = "valor")
    private Double valor;

    @NonNull
    @ColumnInfo(name = "forma_pgto")
    private EnumFormaPgto formaPgto;

    @NonNull
    @ColumnInfo(name = "data_compra")
    private Calendar dataCompra;

    @NonNull
    @ColumnInfo(name = "id_roupa", index = true)
    private Long idRoupa;


    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }

    @NonNull
    public Double getValor() {
        return valor;
    }

    public void setValor(@NonNull Double valor) {
        this.valor = valor;
    }

    @NonNull
    public EnumFormaPgto getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(@NonNull EnumFormaPgto formaPgto) {
        this.formaPgto = formaPgto;
    }

    @NonNull
    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(@NonNull Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

    @NonNull
    public Long getIdRoupa() {
        return idRoupa;
    }

    public void setIdRoupa(@NonNull Long idRoupa) {
        this.idRoupa = idRoupa;
    }
}
