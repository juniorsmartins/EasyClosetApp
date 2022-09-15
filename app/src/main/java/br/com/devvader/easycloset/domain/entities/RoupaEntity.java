package br.com.devvader.easycloset.domain.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity(tableName = "roupas")
public final class RoupaEntity implements Serializable {

    // -------------------- ATRIBUTOS DE CLASSE -------------------- //
    private static final Long serialVersionUID = 1L;

    // -------------------- ATRIBUTOS DE INSTÂNCIA -------------------- //
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
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

    @ColumnInfo(name = "descricao")
    private String descricao;

    @ColumnInfo(name = "data_cadastro")
    private Calendar dataCadastro;


    // -------------------- CONSTRUTORES -------------------- //
    public RoupaEntity() {
        this.dataCadastro = Calendar.getInstance();
    }


    // -------------------- COMPORTAMENTO -------------------- //
    @Override
    public String toString() {
        return "\n\nID = " + id +
                "\nTipo = " + tipo +
                "\nTecido = " + tecido +
                "\nCor Principal = " + corPrincipal +
                "\nTamanho = " + tamanho +
                "\nDescricao = " + descricao +
                "\nData de Cadastro: " + getDataCadastroFormatada();
    }

    public String getDataCadastroFormatada() {
        SimpleDateFormat formatadorDeDatas = new SimpleDateFormat("dd/MM/yyyy");
        return formatadorDeDatas.format(dataCadastro.getTime());
    }


    // -------------------- GETTERS E SETTERS -------------------- //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getTipo() {
        return tipo;
    }

    public void setTipo(@NonNull String tipo) {
        this.tipo = tipo;
    }

    @NonNull
    public String getTecido() {
        return tecido;
    }

    public void setTecido(@NonNull String tecido) {
        this.tecido = tecido;
    }

    @NonNull
    public String getCorPrincipal() {
        return corPrincipal;
    }

    public void setCorPrincipal(@NonNull String corPrincipal) {
        this.corPrincipal = corPrincipal;
    }

    @NonNull
    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(@NonNull String tamanho) {
        this.tamanho = tamanho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
