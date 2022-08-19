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
import br.com.devvader.easycloset.domain.UsuarioEntity;

public class UsuarioAdapter extends ArrayAdapter<UsuarioEntity> {

    private final Context context;
    private List<UsuarioEntity> listaDeUsuarios;
    private View rowView;
    private TextView nome;
    private TextView sobrenome;
    private TextView cpf;
    private TextView fone;
    private TextView email;
    private TextView autorizoPublicidade;
    private TextView escolaridade;

    public UsuarioAdapter(Context context, List<UsuarioEntity> listaDeUsuarios) {
        super(context, R.layout.item_usuario, listaDeUsuarios);
        this.context = context;
        this.listaDeUsuarios = listaDeUsuarios;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rowView = inflater.inflate(R.layout.item_usuario, parent, false);

        nome = rowView.findViewById(R.id.editText_nomeUsuario);
        sobrenome = rowView.findViewById(R.id.editText_sobrenomeUsuario);
        cpf = rowView.findViewById(R.id.editText_cpfUsuario);
        fone = rowView.findViewById(R.id.editText_foneUsuario);
        email = rowView.findViewById(R.id.editText_emailUsuario);
        autorizoPublicidade = rowView.findViewById(R.id.checkBox_autorizoPublicidade);
        escolaridade = rowView.findViewById(R.id.spinner_escolaridadeUsuario);

        nome.setText(listaDeUsuarios.get(position).getNome());
        sobrenome.setText(listaDeUsuarios.get(position).getSobrenome());
        cpf.setText(listaDeUsuarios.get(position).getCpf());
        fone.setText(listaDeUsuarios.get(position).getFone());
        email.setText(listaDeUsuarios.get(position).getEmail());
        autorizoPublicidade.setText(listaDeUsuarios.get(position).getAutorizo() ? "Autorizo" : "Não autorizo");
        escolaridade.setText(listaDeUsuarios.get(position).getEscolaridade());

        if(position % 2 == 0) {
            rowView.setBackgroundColor(Color.GRAY);
        }

        return rowView;
    }

    @Override
    public int getCount() {
        return listaDeUsuarios.size();
    }

    @Nullable
    @Override
    public UsuarioEntity getItem(int position) {
        return listaDeUsuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaDeUsuarios.get(position).getIdUsuario();
    }
}
