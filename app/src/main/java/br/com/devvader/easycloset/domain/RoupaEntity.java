package br.com.devvader.easycloset.domain;

import java.io.Serializable;

public final class RoupaEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    private Long id;
    private String tipo;
    private String tecido;
    private String corPrincipal;
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

    @Override
    public String toString() {
        return "\n\nID = " + id +
                "\nTipo = " + tipo +
                "\nTecido = " + tecido +
                "\nCor Principal = " + corPrincipal +
                "\nTamanho = " + tamanho;
    }
}
