package br.com.devvader.easycloset.domain.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;
import java.util.Date;
import br.com.devvader.easycloset.domain.entities.enuns.EnumFormaPgto;

@Entity(tableName = "compras")
public final class CompraEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_compra")
    private Long id;

    @NonNull
    @ColumnInfo(name = "valor")
    private Double valor;

    @NonNull
    @ColumnInfo(name = "forma_pgto")
    private EnumFormaPgto formaPgto;

    @NonNull
    @ColumnInfo(name = "data_compra")
    private Date dataCompra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(@NonNull Date dataCompra) {
        this.dataCompra = dataCompra;
    }
}
