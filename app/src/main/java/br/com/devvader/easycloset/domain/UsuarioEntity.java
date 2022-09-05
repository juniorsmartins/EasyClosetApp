package br.com.devvader.easycloset.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public final class UsuarioEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    private Long idUsuario;

    @NonNull
    private String nome;

    @NonNull
    private String sobrenome;

    @NonNull
    private String cpf;

    @NonNull
    private String fone;

    @NonNull
    private String email;

    @NonNull
    private String escolaridade;

    @NonNull
    private Boolean autorizo;

    public UsuarioEntity(){}
    public UsuarioEntity(String nomeUsuario, String sobrenomeUsuario, String cpfUsuario,
                         String foneUsuario, String emailUsuario,
                         String escolaridade, Boolean autorizoPublicidade) {
        this.nome = nomeUsuario;
        this.sobrenome = sobrenomeUsuario;
        this.cpf = cpfUsuario;
        this.fone = foneUsuario;
        this.email = emailUsuario;
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
            + "\nEscolaridade: " + escolaridade
            + "\nAutorização de publicidade: " + autorizo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

