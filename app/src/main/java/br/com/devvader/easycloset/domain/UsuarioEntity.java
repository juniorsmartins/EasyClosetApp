package br.com.devvader.easycloset.domain;

import java.io.Serializable;

public final class UsuarioEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    private String nomeUsuario;
    private String sobrenomeUsuario;
    private String cpfUsuario;
    private String foneUsuario;
    private String emailUsuario;
    private Boolean autorizoPublicidade;

    public UsuarioEntity(){}
    public UsuarioEntity(String nomeUsuario, String sobrenomeUsuario, String cpfUsuario,
                         String foneUsuario, String emailUsuario, Boolean autorizoPublicidade) {
        this.nomeUsuario = nomeUsuario;
        this.sobrenomeUsuario = sobrenomeUsuario;
        this.cpfUsuario = cpfUsuario;
        this.foneUsuario = foneUsuario;
        this.emailUsuario = emailUsuario;
        this.autorizoPublicidade = autorizoPublicidade;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSobrenomeUsuario() {
        return sobrenomeUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public String getFoneUsuario() {
        return foneUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public Boolean getAutorizoPublicidade() {
        return autorizoPublicidade;
    }

    @Override
    public String toString() {
        return
            "Nome Completo: " + nomeUsuario + " " + sobrenomeUsuario
            + "\nCPF: " + cpfUsuario
            + "\nFone: " + foneUsuario
            + "\nEmail: " + emailUsuario
            + "\nAutorização de publicidade: " + autorizoPublicidade;
    }
}

