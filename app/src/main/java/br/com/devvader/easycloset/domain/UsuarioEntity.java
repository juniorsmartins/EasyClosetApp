package br.com.devvader.easycloset.domain;

import java.io.Serializable;

public final class UsuarioEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    private int id = 0;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String fone;
    private String email;
    private String sexo;
    private String escolaridade;
    private Boolean autorizo;

    public UsuarioEntity(){}
    public UsuarioEntity(String nomeUsuario, String sobrenomeUsuario, String cpfUsuario,
                         String foneUsuario, String emailUsuario, String sexo,
                         String escolaridade, Boolean autorizoPublicidade) {
        this.nome = nomeUsuario;
        this.sobrenome = sobrenomeUsuario;
        this.cpf = cpfUsuario;
        this.fone = foneUsuario;
        this.email = emailUsuario;
        this.sexo = sexo;
        this.escolaridade = escolaridade;
        this.autorizo = autorizoPublicidade;
    }

    @Override
    public String toString() {
        return
            "Nome Completo: " + nome + " " + sobrenome
            + "\nCPF: " + cpf
            + "\nFone: " + fone
            + "\nEmail: " + email
            + "\nSexo: " + sexo
            + "\nEscolaridade: " + escolaridade
            + "\nAutorização de publicidade: " + autorizo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Boolean getAutorizo() {
        return autorizo;
    }

    public void setAutorizo(Boolean autorizo) {
        this.autorizo = autorizo;
    }
}

