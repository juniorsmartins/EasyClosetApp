package br.com.devvader.easycloset.domain.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.util.List;
import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.entities.RoupaEntity;
import br.com.devvader.easycloset.domain.utils.Utils;

public class RoupaAdapter extends ArrayAdapter<RoupaEntity> {

    private final Context context;
    private List<RoupaEntity> listaDeRoupas;
    private View rowView;
    private TextView tipo;
    private TextView tecido;
    private TextView corPrincipal;
    private TextView tamanho;

    // Preferências Compartilhadas
    private SharedPreferences preferenciasConfig;

    public RoupaAdapter(Context context, List<RoupaEntity> listaDeRoupas) {
        super(context, R.layout.item_roupa, listaDeRoupas);
        this.context = context;
        this.listaDeRoupas = listaDeRoupas;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.item_roupa, parent, false);

        tipo = rowView.findViewById(R.id.tipo_de_roupa);
        tecido = rowView.findViewById(R.id.tecido_da_roupa);
        corPrincipal = rowView.findViewById(R.id.cor_principal_da_roupa);
        tamanho = rowView.findViewById(R.id.tamanho_de_roupa);

        tipo.setText(listaDeRoupas.get(position).getTipo());
        tecido.setText(listaDeRoupas.get(position).getTecido());
        corPrincipal.setText(listaDeRoupas.get(position).getCorPrincipal());
        tamanho.setText(listaDeRoupas.get(position).getTamanho());

        verificarPreferenciasPreConfiguradas();

//        if(position % 2 == 0) {
//            rowView.setBackgroundColor(Color.LTGRAY);
//        }

        return rowView;
    }

    @Override
    public int getCount() {
        if(listaDeRoupas == null) {
            return 0;
        }
        return listaDeRoupas.size();
    }

    @Nullable
    @Override
    public RoupaEntity getItem(int position) {
        return listaDeRoupas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaDeRoupas.get(position).getId();
    }

    private void verificarPreferenciasPreConfiguradas() {
        preferenciasConfig = PreferenceManager.getDefaultSharedPreferences(getContext());
        if(preferenciasConfig.getString(Utils.TEMA_ESCURO, null).equalsIgnoreCase(Utils.ATIVADO)) {
            ativarTemaEscuro();
        } else if(preferenciasConfig.getString(Utils.TEMA_LATINO, null).equalsIgnoreCase(Utils.ATIVADO)) {
            ativarTemaLatino();
        } else {
            ativarTemaPadrao();
        }
    }

        private void ativarTemaPadrao() {
            modificarCorDeFundoDaLinha(R.color.color_gainsboro);
            modificarCorDoTexto(R.color.color_black);
        }

        private void ativarTemaEscuro() {
            modificarCorDeFundoDaLinha(R.color.color_indigo);
            modificarCorDoTexto(R.color.color_white);
        }

        private void ativarTemaLatino() {
            modificarCorDeFundoDaLinha(R.color.color_teal_700);
            modificarCorDoTexto(R.color.color_yellow_light);
        }

            private void modificarCorDeFundoDaLinha(int cor) {
                rowView.setBackgroundColor(context.getResources().getColor(cor));
            }

            private void modificarCorDoTexto(int cor) {
                tipo.setTextColor(context.getResources().getColor(cor));
                tecido.setTextColor(context.getResources().getColor(cor));
                corPrincipal.setTextColor(context.getResources().getColor(cor));
                tamanho.setTextColor(context.getResources().getColor(cor));
            }
}
