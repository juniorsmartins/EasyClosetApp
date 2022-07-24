package br.com.devvader.easycloset.domain.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.RoupaEntity;

public class RoupaAdapter extends ArrayAdapter<RoupaEntity> {

    public RoupaAdapter(Context context, List<RoupaEntity> listaDeRoupas) {
        super(context, 0, listaDeRoupas);
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {

        if(itemView == null)
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_roupa, parent, false);

        TextView tipo = itemView.findViewById(R.id.tipo_de_roupa);
        TextView tamanho = itemView.findViewById(R.id.tamanho_de_roupa);
        TextView corPrincipal = itemView.findViewById(R.id.cor_principal_da_roupa);
        TextView tecido = itemView.findViewById(R.id.tecido_da_roupa);

        RoupaEntity roupaEntity = getItem(position);
        tipo.setText(roupaEntity.getTipo());
        tamanho.setText(roupaEntity.getTamanho());
        corPrincipal.setText(roupaEntity.getCorPrincipal());
        tecido.setText(roupaEntity.getTecido());

        return itemView;
    }

}
