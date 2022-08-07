package br.com.devvader.easycloset.recursos;

import java.util.List;
import br.com.devvader.easycloset.domain.RoupaEntity;

public interface IRoupaRepository {

    void salvarRoupa(RoupaEntity roupa);
    void atualizarRoupa(RoupaEntity novaRoupa);
    List<RoupaEntity> listarRoupas();
    RoupaEntity consultarPecasDeRoupaPorPosicao(int position);
}
