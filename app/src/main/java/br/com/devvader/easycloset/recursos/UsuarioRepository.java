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
        listaDeUsuarios.add(new UsuarioEntity(
                "Pedro",
                "Marino",
                "999999999",
                "999988888",
                "fulano@gmail.com"));

        listaDeUsuarios.add(new UsuarioEntity(
                "Maria",
                "Aparecida",
                "7777777777",
                "6666666666",
                "sicrana@gmail.com"));

        return listaDeUsuarios;
    }
}
