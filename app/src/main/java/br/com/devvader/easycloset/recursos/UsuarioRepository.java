package br.com.devvader.easycloset.recursos;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.devvader.easycloset.domain.UsuarioEntity;

public final class UsuarioRepository implements IUsuarioRepository {

    private static List<UsuarioEntity> listaDeUsuarios = new ArrayList<>();
    private static Long contadorDeIdsDeUsuarios = 1L;

    @Override
    public void salvarUsuario(UsuarioEntity usuario) {
        if(!listaDeUsuarios.contains(usuario)) {
            usuario.setIdUsuario(contadorDeIdsDeUsuarios);
            listaDeUsuarios.add(usuario);
            contadorDeIdsDeUsuarios++;
        }
    }

    @Override
    public void atualizarUsuario(UsuarioEntity novoUsuario) {
        for(UsuarioEntity usuario : listaDeUsuarios) {
            if(usuario.getIdUsuario() == novoUsuario.getIdUsuario()) {
                int posicaoDoUsuario = listaDeUsuarios.indexOf(usuario);
                listaDeUsuarios.set(posicaoDoUsuario, novoUsuario);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<UsuarioEntity> listar() {
        return listaDeUsuarios
                .stream()
                .sorted(Comparator.comparing(UsuarioEntity::getIdUsuario).reversed())
                .collect(Collectors.toList());
    }
}
