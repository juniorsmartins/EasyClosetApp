package br.com.devvader.easycloset.recursos;

import java.util.List;

import br.com.devvader.easycloset.domain.RoupaEntity;
import br.com.devvader.easycloset.domain.UsuarioEntity;

public interface IRoupaRepository {

    void salvarRoupa(RoupaEntity roupa);
    void editarRoupa(RoupaEntity novaRoupa);
    List<RoupaEntity> buscarTodasPecasDeRoupa();
    RoupaEntity consultarPecasDeRoupaPorPosicao(int position);
}
