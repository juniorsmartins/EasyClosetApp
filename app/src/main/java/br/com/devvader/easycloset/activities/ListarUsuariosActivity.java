package br.com.devvader.easycloset.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.UsuarioEntity;
import br.com.devvader.easycloset.recursos.IUsuarioRepository;
import br.com.devvader.easycloset.recursos.UsuarioRepository;

public class ListarUsuariosActivity extends AppCompatActivity {

    private static final String LISTAR_USUARIOS = "Listar Usuários";

    private IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private ListView enderecoListaDeUsuarios;
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
        capturarEnderecoDaListaDeUsuarios();
        buscarListaDeUsuariosNoRepository();
        mostrarListaDeUsuariosNaTela();
        configurarCliqueNaListaParaEditarUsuario();
    }

        private void colocarTituloNaTela() {
            setTitle(LISTAR_USUARIOS);
        }

        private void capturarEnderecoDaListaDeUsuarios() {
            enderecoListaDeUsuarios = findViewById(R.id.listView_listaDeUsuarios);
        }

        private List<UsuarioEntity> buscarListaDeUsuariosNoRepository() {
            return usuarioRepository.buscarTodosUsuarios();
        }

        private void mostrarListaDeUsuariosNaTela() {
            enderecoListaDeUsuarios.setAdapter(new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    buscarListaDeUsuariosNoRepository())
            );
        }

        private void configurarCliqueNaListaParaEditarUsuario() {
            enderecoListaDeUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    buscarUsuarioNoRepositoryPorPosicao(position);
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

            private void gerarLogDoUsuarioEscolhido(int position) {
                Log.i("Usuário:", " " + usuario + " e posição: " + position);
            }

            private void criarPonteDaTelaListarUsuarioParaTelaCadastrarUsuario() {
                ponteEntreListarAndCadastrar = new Intent(
                        ListarUsuariosActivity.this, CadastrarUsuarioActivity.class);
            }

            private void guardarUsuarioEscolhidoParaEnviarParaEditarNaTelaCadastrarUsuario() {
                ponteEntreListarAndCadastrar.putExtra("usuario", usuario);
            }

            private void abrirTelaDeCadastrarUsuarioParaEditarUsuario() {
                startActivity(ponteEntreListarAndCadastrar);
            }
}

