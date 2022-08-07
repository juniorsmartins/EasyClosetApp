package br.com.devvader.easycloset.recursos;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.devvader.easycloset.domain.RoupaEntity;

public final class RoupaRepository implements IRoupaRepository {

    private static List<RoupaEntity> listaDeRoupas = new ArrayList<>();
    private static Long contadorDeIdsDeRoupas = 1L;

    @Override
    public void salvarRoupa(RoupaEntity roupa) {
        if(!listaDeRoupas.contains(roupa)) {
            roupa.setId(contadorDeIdsDeRoupas);
            listaDeRoupas.add(roupa);
            contadorDeIdsDeRoupas++;
        }
    }

    @Override
    public void atualizarRoupa(RoupaEntity novaRoupa) {
        System.out.println(novaRoupa + "\n\n");
        int posicaoNaLista = -1;
        for(RoupaEntity roupa : listaDeRoupas) {
            if(roupa.getId() == novaRoupa.getId()) {
                int contador = 0;
                System.out.println("\n\n----- testes -----" + contador++);
                posicaoNaLista = listaDeRoupas.indexOf(roupa);
                break;
            }
        }
        if(posicaoNaLista >= 0)
            listaDeRoupas.remove(posicaoNaLista);
        listaDeRoupas.add(novaRoupa);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<RoupaEntity> listar() {
        return listaDeRoupas
                .stream()
                .sorted(Comparator.comparing(RoupaEntity::getId).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public RoupaEntity consultarPecasDeRoupaPorPosicao(int position) {
        return listaDeRoupas.get(position);
    }
}
