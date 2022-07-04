package br.com.devvader.easycloset.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.recursos.IUsuarioRepository;
import br.com.devvader.easycloset.recursos.UsuarioRepository;

public class ListarUsuariosActivity extends AppCompatActivity {

    private IUsuarioRepository usuarioRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);

        usuarioRepository = new UsuarioRepository();

        ListView listaDeUsuarios = findViewById(R.id.listView_listaDeUsuarios);
        listaDeUsuarios.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                usuarioRepository.buscarTodosUsuarios()));
    }
}