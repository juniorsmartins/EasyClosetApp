package br.com.devvader.easycloset.domain;

import java.io.Serializable;
import java.time.LocalDate;

public final class RoupaEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    private Long idRoupa;
    private String tipo;
    private String tamanho;
    private String corPrincipal;
    private String marca;
    private LocalDate dataCompra;

    public RoupaEntity() {}
    public RoupaEntity(Long idRoupa, String tipo, String tamanho,
                       String corPrincipal, String marca, LocalDate dataCompra) {
        this.idRoupa = idRoupa;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.corPrincipal = corPrincipal;
        this.marca = marca;
        this.dataCompra = dataCompra;
    }

    public Long getIdRoupa() {
        return idRoupa;
    }

    public void setIdRoupa(Long idRoupa) {
        this.idRoupa = idRoupa;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }
}
