package br.com.devvader.easycloset.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.UsuarioEntity;
import br.com.devvader.easycloset.recursos.IUsuarioRepository;
import br.com.devvader.easycloset.recursos.UsuarioRepository;

public class ListarUsuariosActivity extends AppCompatActivity {

    private static final String LISTAR_USUARIOS = "Listar Usuários";

    private IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private ListView enderecoListaDeUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(LISTAR_USUARIOS);
        capturarEnderecoDaListaDeUsuarios();
        mostrarNaTelaListaDeUsuarios();
    }

        private void capturarEnderecoDaListaDeUsuarios() {
            enderecoListaDeUsuarios = findViewById(R.id.listView_listaDeUsuarios);
        }

        private List<UsuarioEntity> buscarListaDeUsuariosNoRepository() {
            return usuarioRepository.buscarTodosUsuarios();
        }

        private void mostrarNaTelaListaDeUsuarios() {
            enderecoListaDeUsuarios.setAdapter(new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    buscarListaDeUsuariosNoRepository())
            );
        }
}

