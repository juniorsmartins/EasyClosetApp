package br.com.devvader.easycloset.domain.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.util.List;
import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.RoupaEntity;

public class RoupaAdapter extends ArrayAdapter<RoupaEntity> {

    private final Context context;
    private List<RoupaEntity> listaDeRoupas;

    public RoupaAdapter(Context context, List<RoupaEntity> listaDeRoupas) {
        super(context, R.layout.item_roupa, listaDeRoupas);
        this.context = context;
        this.listaDeRoupas = listaDeRoupas;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_roupa, parent, false);

        TextView tipo = rowView.findViewById(R.id.tipo_de_roupa);
        TextView tamanho = rowView.findViewById(R.id.tamanho_de_roupa);
        TextView corPrincipal = rowView.findViewById(R.id.cor_principal_da_roupa);
        TextView tecido = rowView.findViewById(R.id.tecido_da_roupa);

        tipo.setText(listaDeRoupas.get(position).getTipo());
        tamanho.setText(listaDeRoupas.get(position).getTamanho());
        corPrincipal.setText(listaDeRoupas.get(position).getCorPrincipal());
        tecido.setText(listaDeRoupas.get(position).getTecido());

        if(position % 2 == 0) {
            rowView.setBackgroundColor(Color.GRAY);
        }

        return rowView;
    }

    @Override
    public int getCount() {
        return listaDeRoupas.size();
    }

    @Nullable
    @Override
    public RoupaEntity getItem(int position) {
        return listaDeRoupas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaDeRoupas.get(position).getIdRoupa();
    }

    public int getPosicaoPorId(long id) {
        for(RoupaEntity roupa : listaDeRoupas) {
            if(roupa.getIdRoupa() == id) {
                return listaDeRoupas.indexOf(roupa);
            }
        }
        return -1;
    }
}
