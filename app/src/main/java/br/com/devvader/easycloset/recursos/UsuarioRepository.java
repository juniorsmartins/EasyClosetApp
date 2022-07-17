package br.com.devvader.easycloset.recursos;

import java.util.ArrayList;
import java.util.List;

import br.com.devvader.easycloset.domain.UsuarioEntity;

public final class UsuarioRepository implements IUsuarioRepository {

    private static List<UsuarioEntity> listaDeUsuarios = new ArrayList<>();
    private static int contadorDeIds = 1;


    @Override
    public void salvarUsuario(UsuarioEntity usuario) {
        usuario.setId(contadorDeIds);
        listaDeUsuarios.add(usuario);
        contadorDeIds++;
    }

    @Override
    public void editarUsuario(UsuarioEntity novoUsuario) {
        for (UsuarioEntity usuario : listaDeUsuarios) {
            if(usuario.getId() == novoUsuario.getId()) {
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
