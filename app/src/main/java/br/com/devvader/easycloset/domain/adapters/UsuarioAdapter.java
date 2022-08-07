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

    public UsuarioAdapter(Context context, List<UsuarioEntity> listaDeUsuarios) {
        super(context, R.layout.item_usuario, listaDeUsuarios);
        this.context = context;
        this.listaDeUsuarios = listaDeUsuarios;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_usuario, parent, false);

        TextView nome = rowView.findViewById(R.id.editText_nomeUsuario);
        TextView sobrenome = rowView.findViewById(R.id.editText_sobrenomeUsuario);
        TextView cpf = rowView.findViewById(R.id.editText_cpfUsuario);
        TextView fone = rowView.findViewById(R.id.editText_foneUsuario);
        TextView email = rowView.findViewById(R.id.editText_emailUsuario);
        TextView autorizoPublicidade = rowView.findViewById(R.id.checkBox_autorizoPublicidade);
        TextView sexo = rowView.findViewById(R.id.radioGroup_sexo);
        TextView escolaridade = rowView.findViewById(R.id.spinner_escolaridadeUsuario);

        nome.setText(listaDeUsuarios.get(position).getNome());
        sobrenome.setText(listaDeUsuarios.get(position).getSobrenome());
        cpf.setText(listaDeUsuarios.get(position).getCpf());
        fone.setText(listaDeUsuarios.get(position).getFone());
        email.setText(listaDeUsuarios.get(position).getEmail());
        autorizoPublicidade.setText(listaDeUsuarios.get(position).getAutorizo() ? "Autorizo" : "Não autorizo");
        sexo.setText(listaDeUsuarios.get(position).getSexo().equalsIgnoreCase("Masculino") ?
                "Masculino" : "Feminino");
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

    public int getPosicaoPorId(long id) {
        for(UsuarioEntity usuario : listaDeUsuarios) {
            if(usuario.getIdUsuario() == id) {
                return listaDeUsuarios.indexOf(usuario);
            }
        }
        return -1;
    }
}
