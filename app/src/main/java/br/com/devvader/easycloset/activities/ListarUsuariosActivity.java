package br.com.devvader.easycloset.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
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
import br.com.devvader.easycloset.domain.entities.UsuarioEntity;
import br.com.devvader.easycloset.domain.adapters.UsuarioAdapter;
import br.com.devvader.easycloset.recursos.daos.UsuarioDAORoom;
import br.com.devvader.easycloset.recursos.database.EasyClosetDatabaseRoom;

public final class ListarUsuariosActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_LISTAR_USUARIOS = "Listar Usuários";

    private UsuarioDAORoom usuarioDAORoom;

    private ListView enderecoDaListaDeUsuarios;
    private UsuarioEntity usuarioEntity;
    private Button enderecoBotaoAdicionar;
    private UsuarioAdapter usuarioAdapter;

    private List<UsuarioEntity> listaDeUsuarios;


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
        ativarCliqueRapidoNosItensDalistaParaEditar();
        limitarQuantiaDeItensSelecionadosPorCliqueNalista();

        mapearEnderecoDoBotaoAdicionar();
        ativarBotaoAdicionar();
    }

        private List<UsuarioEntity> buscarTodasEntidadesDoBancoDeDadosOrdenadasPorIdDecrescente() {
            acessarBancoDeDados();
            listaDeUsuarios = usuarioDAORoom.queryAll();
            return listaDeUsuarios;
        }

            private void acessarBancoDeDados() {
                usuarioDAORoom = EasyClosetDatabaseRoom.getConexaoDatabaseRoom(this).getUsuarioDAORoom();
            }

        private void salvarNoBancoDeDados() {
            acessarBancoDeDados();
            usuarioDAORoom.insert(usuarioEntity);
        }

        private void atualizarNoBancoDeDados() {
            acessarBancoDeDados();
            usuarioDAORoom.update(usuarioEntity);
        }

        private void excluirNoBancoDeDados() {
            acessarBancoDeDados();
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    usuarioDAORoom.delete(usuarioEntity);
                }
            });
        }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_LISTAR_USUARIOS);
        }

        private void mapearEnderecoDaLista() {
            enderecoDaListaDeUsuarios = findViewById(R.id.listView_listaDeUsuarios);
        }

        private void mostrarListaNaTelaComAdapterCustomizado() {
            usuarioAdapter = new UsuarioAdapter(this, buscarTodasEntidadesDoBancoDeDadosOrdenadasPorIdDecrescente());
            enderecoDaListaDeUsuarios.setAdapter(usuarioAdapter);
        }

        private void limitarQuantiaDeItensSelecionadosPorCliqueNalista() {
            enderecoDaListaDeUsuarios.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }

        private void ativarCliqueRapidoNosItensDalistaParaEditar() {
            enderecoDaListaDeUsuarios.setOnItemClickListener((parent, view, position, id) -> {

                usuarioEntity = (UsuarioEntity) parent.getItemAtPosition(position);
                usuarioAdapter = (UsuarioAdapter) parent.getAdapter();

                publicarMensagemNaTela(usuarioEntity.getNome()
                        .concat(" ")
                        .concat(usuarioEntity.getSobrenome())
                        .concat(" ")
                        .concat(getString(R.string.selecionar)));

                gerarLogSobreQualItemFoiSelecionadoNaLista(position);

                CadastrarUsuarioActivity
                        .atualizarUsuarioComRetorno(ListarUsuariosActivity.this, usuarioEntity);
            });
        }

            private void publicarMensagemNaTela(String mensagem) {
                Toast.makeText(getApplicationContext(),
                        mensagem,
                        Toast.LENGTH_SHORT)
                        .show();
            }

            private void gerarLogSobreQualItemFoiSelecionadoNaLista(int position) {
                Log.i(getString(R.string.usuario), " "
                        .concat(usuarioEntity.getNome())
                        .concat(" ")
                        .concat(usuarioEntity.getSobrenome())
                        .concat(" - ")
                        .concat(getString(R.string.na_posicao))
                        .concat(": ")
                        .concat(String.valueOf(position)));
            }

        private void mapearEnderecoDoBotaoAdicionar() {
            enderecoBotaoAdicionar = findViewById(R.id.button_listar_adicionar_usuario);
        }

        private void ativarBotaoAdicionar() {
            enderecoBotaoAdicionar.setOnClickListener(view -> CadastrarUsuarioActivity
                    .cadastrarUsuarioComRetorno(ListarUsuariosActivity.this));
        }


    // -------------------- OnActivityResult - recepciona retorno de startActivityForResult --------------------
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == Activity.RESULT_OK) {
            Bundle bundle = intent.getExtras();

            if(bundle.getInt(CadastrarUsuarioActivity.MODO) == CadastrarUsuarioActivity.SALVAR) {
                usuarioEntity = (UsuarioEntity) bundle.getSerializable(CadastrarUsuarioActivity.USUARIO);
                salvarNoBancoDeDados();

                publicarMensagemNaTela(usuarioEntity.getNome()
                        .concat(" ")
                        .concat(usuarioEntity.getSobrenome())
                        .concat(" ")
                        .concat(getString(R.string.salvo)));

            } else if(bundle.getInt(CadastrarUsuarioActivity.MODO) == CadastrarUsuarioActivity.ATUALIZAR) {
//                excluirNoBancoDeDados();
                usuarioEntity = (UsuarioEntity) bundle.getSerializable(CadastrarUsuarioActivity.USUARIO);
                atualizarNoBancoDeDados();

//                mostrarListaNaTelaComAdapterCustomizado();

                publicarMensagemNaTela(usuarioEntity.getNome()
                        .concat(" ")
                        .concat(usuarioEntity.getSobrenome())
                        .concat(" ")
                        .concat(getString(R.string.atualizado)));

            } else {
                publicarMensagemNaTela(getString(R.string.erro_retorno_incompativel));
            }
            notificarAdapterSobreModificacaoNaListView();
        }
    }

        private void notificarAdapterSobreModificacaoNaListView() {
            usuarioAdapter.notifyDataSetChanged();
        }
}

