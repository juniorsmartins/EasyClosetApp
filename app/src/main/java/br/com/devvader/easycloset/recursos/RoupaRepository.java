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
            roupa.setIdRoupa(contadorDeIdsDeRoupas);
            listaDeRoupas.add(roupa);
            contadorDeIdsDeRoupas++;
        }
    }

    @Override
    public void editarRoupa(RoupaEntity novaRoupa) {
        for(RoupaEntity roupa : listaDeRoupas) {
            if(roupa.getIdRoupa() == novaRoupa.getIdRoupa()) {
                int posicaoDaRoupa = listaDeRoupas.indexOf(roupa);
                listaDeRoupas.set(posicaoDaRoupa, novaRoupa);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<RoupaEntity> buscarTodasPecasDeRoupa() {
        return listaDeRoupas
                .stream()
                .sorted(Comparator.comparing(RoupaEntity::getIdRoupa).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public RoupaEntity consultarPecasDeRoupaPorPosicao(int position) {
        return listaDeRoupas.get(position);
    }
}
