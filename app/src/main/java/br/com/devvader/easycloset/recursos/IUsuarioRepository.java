package br.com.devvader.easycloset.recursos;

import java.util.List;

import br.com.devvader.easycloset.domain.UsuarioEntity;

public interface IUsuarioRepository {

    void salvarUsuario(UsuarioEntity usuario);
    List<UsuarioEntity> buscarTodosUsuarios();
    UsuarioEntity consultarUsuarioPorPosicao(int position);
}
