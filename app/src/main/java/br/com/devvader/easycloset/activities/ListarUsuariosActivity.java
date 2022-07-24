package br.com.devvader.easycloset.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.UsuarioEntity;
import br.com.devvader.easycloset.recursos.IUsuarioRepository;
import br.com.devvader.easycloset.recursos.UsuarioRepository;

public class ListarUsuariosActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_LISTAR_USUARIOS = "Listar Usuários";
    public static final String USUARIO = "Usuario";

    private IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private ListView enderecoDaListaDeUsuarios;
    private UsuarioEntity usuario;
    private Intent ponteEntreListarAndCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuarios);
    }

    @Override
    protected void onResume() {
        super.onResume();

        colocarTituloNaTela();
        mapearEnderecoDaListaDeUsuarios();
        mostrarListaDeUsuariosNaTela();
        ativarCliqueNaListaParaEditarUsuario();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_LISTAR_USUARIOS);
        }

        private void mapearEnderecoDaListaDeUsuarios() {
            enderecoDaListaDeUsuarios = findViewById(R.id.listView_listaDeUsuarios);
        }

        private void mostrarListaDeUsuariosNaTela() {
            enderecoDaListaDeUsuarios.setAdapter(new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    buscarListaDeUsuariosNoRepository())
            );
        }

            private List<UsuarioEntity> buscarListaDeUsuariosNoRepository() {
                return usuarioRepository.buscarTodosUsuarios();
            }

        private void ativarCliqueNaListaParaEditarUsuario() {
            enderecoDaListaDeUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    buscarUsuarioNoRepositoryPorPosicao(position);
                    gerarMensagemDoUsuarioEscolhido();
                    gerarLogDoUsuarioEscolhido(position);
                    criarPonteDaTelaListarUsuarioParaTelaCadastrarUsuario();
                    guardarUsuarioEscolhidoParaEnviarParaEditarNaTelaCadastrarUsuario();
                    abrirTelaDeCadastrarUsuarioParaEditarUsuario();
                }
            });
        }

            private void buscarUsuarioNoRepositoryPorPosicao(int position) {
                usuario = usuarioRepository.consultarUsuarioPorPosicao(position);
            }

            private void gerarMensagemDoUsuarioEscolhido() {
                Toast.makeText(getApplicationContext(),
                        usuario.getNome().concat(" ").concat(usuario.getSobrenome()),
                        Toast.LENGTH_LONG)
                        .show();
            }

            private void gerarLogDoUsuarioEscolhido(int position) {
                Log.i("Usuário:", " " + usuario + " e posição: " + position);
            }

            private void criarPonteDaTelaListarUsuarioParaTelaCadastrarUsuario() {
                ponteEntreListarAndCadastrar = new Intent(
                        ListarUsuariosActivity.this, CadastrarUsuarioActivity.class);
            }

            private void guardarUsuarioEscolhidoParaEnviarParaEditarNaTelaCadastrarUsuario() {
                ponteEntreListarAndCadastrar.putExtra(USUARIO, usuario);
            }

            private void abrirTelaDeCadastrarUsuarioParaEditarUsuario() {
                startActivity(ponteEntreListarAndCadastrar);
            }
}

