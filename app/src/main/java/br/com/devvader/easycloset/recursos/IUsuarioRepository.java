package br.com.devvader.easycloset.recursos;

import java.util.List;

import br.com.devvader.easycloset.domain.UsuarioEntity;

public interface IUsuarioRepository {

    void salvarUsuario(UsuarioEntity usuario);
    void atualizarUsuario(UsuarioEntity novoUsuario);
    List<UsuarioEntity> listar();
    UsuarioEntity consultarUsuarioPorPosicao(int position);
}
