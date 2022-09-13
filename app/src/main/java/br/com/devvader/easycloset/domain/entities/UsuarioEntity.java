package br.com.devvader.easycloset.domain.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "usuarios", indices = @Index(value = {"cpf"}, unique = true))
public final class UsuarioEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    private Long idUsuario;

    @NonNull
    @ColumnInfo(name = "nome")
    private String nome;

    @NonNull
    @ColumnInfo(name = "sobrenome")
    private String sobrenome;

    @NonNull
    @ColumnInfo(name = "cpf")
    private String cpf;

    @NonNull
    @ColumnInfo(name = "fone")
    private String fone;

    @NonNull
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "escolaridade")
    private String escolaridade;

    @NonNull
    @ColumnInfo(name = "autorizo")
    private Boolean autorizo;

    public UsuarioEntity(){}

    @Ignore
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

