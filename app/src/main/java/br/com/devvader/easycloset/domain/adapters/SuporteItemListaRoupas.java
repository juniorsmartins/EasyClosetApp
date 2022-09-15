package br.com.devvader.easycloset.domain.adapters;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.devvader.easycloset.domain.entities.CompraEntity;
import br.com.devvader.easycloset.domain.entities.RoupaEntity;

public class SuporteItemListaRoupas {

    private Long id;
    private String tipo;
    private String tecido;
    private String corPrincipal;
    private String tamanho;
    private String descricao;
    private Calendar dataCadastro;
    private Long compraId;
    private Double valorPago;
    private String formaPagamento;

    public SuporteItemListaRoupas(RoupaEntity roupa, CompraEntity compra) {
        id = roupa.getId();
        tipo = roupa.getTipo();
        tecido = roupa.getTecido();
        corPrincipal = roupa.getCorPrincipal();
        tamanho = roupa.getTamanho();
        descricao = roupa.getDescricao();
        dataCadastro = roupa.getDataCadastro();
        valorPago = compra.getValorPago();
        formaPagamento = compra.getFormaPagamento();
    }

    public String getDataCadastroFormatada() {
        SimpleDateFormat formatadorDeDatas = new SimpleDateFormat("dd/MM/yyyy");
        return formatadorDeDatas.format(dataCadastro.getTime());
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

    public String getTecido() {
        return tecido;
    }

    public void setTecido(String tecido) {
        this.tecido = tecido;
    }

    public String getCorPrincipal() {
        return corPrincipal;
    }

    public void setCorPrincipal(String corPrincipal) {
        this.corPrincipal = corPrincipal;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
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

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }
}
