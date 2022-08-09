package br.com.devvader.easycloset.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.UsuarioEntity;
import br.com.devvader.easycloset.domain.adapters.UsuarioAdapter;
import br.com.devvader.easycloset.recursos.IUsuarioRepository;
import br.com.devvader.easycloset.recursos.UsuarioRepository;

public final class ListarUsuariosActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_LISTAR_USUARIOS = "Listar Usuários";

    private final IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private ListView enderecoDaListaDeUsuarios;
    private UsuarioEntity usuario;
    private Button enderecoBotaoAdicionar;
    private UsuarioAdapter usuarioAdapter;

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
    }

    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();

        mapearEnderecoDaLista();
        mostrarListaNaTelaComAdapterCustomizado();

        mapearEnderecoDoBotaoAdicionar();
        ativarBotaoAdicionar();

        ativarCliqueNosItensDalistaParaEditar();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_LISTAR_USUARIOS);
        }

        private void mapearEnderecoDaLista() {
            enderecoDaListaDeUsuarios = findViewById(R.id.listView_listaDeUsuarios);
        }

        private void mostrarListaNaTelaComAdapterCustomizado() {
            usuarioAdapter = new UsuarioAdapter(this, buscarListaNoRepository());
            enderecoDaListaDeUsuarios.setAdapter(usuarioAdapter);
        }

            private List<UsuarioEntity> buscarListaNoRepository() {
                return usuarioRepository.listar();
            }

        private void mapearEnderecoDoBotaoAdicionar() {
            enderecoBotaoAdicionar = findViewById(R.id.button_listar_adicionar_usuario);
        }

        private void ativarBotaoAdicionar() {
            enderecoBotaoAdicionar.setOnClickListener(view -> CadastrarUsuarioActivity.
                    cadastrarUsuarioComRetorno(ListarUsuariosActivity.this));
        }

        private void ativarCliqueNosItensDalistaParaEditar() {
            enderecoDaListaDeUsuarios.setOnItemClickListener((parent, view, position, id) -> {
                usuario = (UsuarioEntity) parent.getItemAtPosition(position);

                gerarMensagemDoUsuarioEscolhido();
                gerarLogDoUsuarioEscolhido(position);
                CadastrarUsuarioActivity.atualizarUsuarioComRetorno(ListarUsuariosActivity.this, usuario);
            });
        }

            private void gerarMensagemDoUsuarioEscolhido() {
                Toast.makeText(getApplicationContext(),
                        usuario.getNome().concat(" ").concat(usuario.getSobrenome()),
                        Toast.LENGTH_LONG)
                        .show();
            }

            private void gerarLogDoUsuarioEscolhido(int position) {
                Log.i("Usuário:", " " + usuario.getNome() + " e posição: " + position);
            }

    // ------------------------------ OnActivityResult ------------------------------
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == Activity.RESULT_OK) {
            Bundle bundle = intent.getExtras();
            usuario = (UsuarioEntity) bundle.getSerializable(CadastrarUsuarioActivity.USUARIO);

            if(bundle.getInt(CadastrarUsuarioActivity.MODO) == CadastrarUsuarioActivity.ATUALIZAR) {
                usuarioRepository.atualizarUsuario(usuario);
            }

            if(bundle.getInt(CadastrarUsuarioActivity.MODO) == CadastrarUsuarioActivity.SALVAR) {
                usuarioRepository.salvarUsuario(usuario);
            }

            usuarioAdapter.notifyDataSetChanged();
        }
    }
}

