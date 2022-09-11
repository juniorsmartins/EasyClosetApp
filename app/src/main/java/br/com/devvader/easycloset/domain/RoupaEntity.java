package br.com.devvader.easycloset.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "roupas")
public final class RoupaEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_roupa")
    private Long id;

    @NonNull
    @ColumnInfo(name = "tipo")
    private String tipo;

    @NonNull
    @ColumnInfo(name = "tecido")
    private String tecido;

    @NonNull
    @ColumnInfo(name = "cor_principal")
    private String corPrincipal;

    @NonNull
    @ColumnInfo(name = "tamanho")
    private String tamanho;

    public RoupaEntity() {}
    public RoupaEntity(String tipo,
                       String tecido,
                       String corPrincipal,
                       String tamanho) {
        this.tipo = tipo;
        this.tecido = tecido;
        this.corPrincipal = corPrincipal;
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "\n\nID = " + id +
                "\nTipo = " + tipo +
                "\nTecido = " + tecido +
                "\nCor Principal = " + corPrincipal +
                "\nTamanho = " + tamanho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCorPrincipal() {
        return corPrincipal;
    }

    public void setCorPrincipal(String corPrincipal) {
        this.corPrincipal = corPrincipal;
    }

    public String getTecido() {
        return tecido;
    }

    public void setTecido(String tecido) {
        this.tecido = tecido;
    }
}
