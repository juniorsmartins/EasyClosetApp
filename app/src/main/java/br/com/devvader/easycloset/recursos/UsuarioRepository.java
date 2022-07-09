package br.com.devvader.easycloset.recursos;

import java.util.ArrayList;
import java.util.List;

import br.com.devvader.easycloset.domain.UsuarioEntity;

public final class UsuarioRepository implements IUsuarioRepository {

    private static List<UsuarioEntity> listaDeUsuarios = new ArrayList<>();

    @Override
    public void salvarUsuario(UsuarioEntity usuario) {
        listaDeUsuarios.add(usuario);
    }

    @Override
    public List<UsuarioEntity> buscarTodosUsuarios() {
        return listaDeUsuarios;
    }
}
