package br.com.devvader.easycloset.recursos;

import java.util.ArrayList;
import java.util.List;

import br.com.devvader.easycloset.domain.UsuarioEntity;

public final class UsuarioRepository implements IUsuarioRepository {

    private static List<UsuarioEntity> listaDeUsuarios = new ArrayList<>();
    private static Long contadorDeIdsDeUsuarios = 1L;

    @Override
    public void salvarUsuario(UsuarioEntity usuario) {
        usuario.setIdUsuario(contadorDeIdsDeUsuarios);
        listaDeUsuarios.add(usuario);
        contadorDeIdsDeUsuarios++;
    }

    @Override
    public void editarUsuario(UsuarioEntity novoUsuario) {
        for(UsuarioEntity usuario : listaDeUsuarios) {
            if(usuario.getIdUsuario() == novoUsuario.getIdUsuario()) {
                int posicaoDoUsuario = listaDeUsuarios.indexOf(usuario);
                listaDeUsuarios.set(posicaoDoUsuario, novoUsuario);
            }
        }
    }

    @Override
    public List<UsuarioEntity> buscarTodosUsuarios() {
        return listaDeUsuarios;
    }

    @Override
    public UsuarioEntity consultarUsuarioPorPosicao(int position) {
        return listaDeUsuarios.get(position);
    }
}
