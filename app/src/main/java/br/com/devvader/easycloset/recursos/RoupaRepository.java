package br.com.devvader.easycloset.recursos;

import java.util.ArrayList;
import java.util.List;
import br.com.devvader.easycloset.domain.RoupaEntity;

public final class RoupaRepository implements IRoupaRepository {

    private static List<RoupaEntity> listaDeRoupas = new ArrayList<>();
    private static Long contadorDeIdsDeRoupas = 1L;

    @Override
    public void salvarRoupa(RoupaEntity roupa) {
        roupa.setIdRoupa(contadorDeIdsDeRoupas);
        listaDeRoupas.add(roupa);
        contadorDeIdsDeRoupas++;
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

    @Override
    public List<RoupaEntity> buscarTodasPecasDeRoupa() {
        return listaDeRoupas;
    }

    @Override
    public RoupaEntity consultarPecasDeRoupaPorPosicao(int position) {
        return listaDeRoupas.get(position);
    }
}
