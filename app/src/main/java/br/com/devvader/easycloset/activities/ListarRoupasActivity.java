package br.com.devvader.easycloset.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import br.com.devvader.easycloset.MainActivity;
import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.RoupaEntity;
import br.com.devvader.easycloset.domain.adapters.RoupaAdapter;
import br.com.devvader.easycloset.domain.utils.Utils;
import br.com.devvader.easycloset.recursos.RoupaDatabase;

public final class ListarRoupasActivity extends AppCompatActivity {

    private static final String TITULO_TELA_LISTAR_ROUPAS = "EasyCloset";

    private ListView enderecoDaListaDeRoupas;
    private RoupaEntity roupaEntity;
    private RoupaAdapter roupaAdapter;
    private ActionMode actionMode;
    private View viewSelecionada;

    // Preferências Compartilhadas
    private SharedPreferences preferenciasConfig;
    private ConstraintLayout constraintLayout;

    // Persistência
    private RoupaDatabase roupaDatabase;

    // Modal para confirmar excluir item do database
    private String mensagem;
    private DialogInterface.OnClickListener ouvidorDoModalDeConfirmarExcluirItemDoDatabase;


    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_roupas);

        mapearEnderecosParaManipularPreferencias();
        verificarPreferenciasPreConfiguradas();
    }

        private void mapearEnderecosParaManipularPreferencias() {
            preferenciasConfig = PreferenceManager.getDefaultSharedPreferences(this);
            constraintLayout = findViewById(R.id.constraint_layout_listar_roupas);
        }

        private void verificarPreferenciasPreConfiguradas() {
            if(preferenciasConfig.getString(Utils.TEMA_ESCURO, null).equalsIgnoreCase(Utils.ATIVADO)) {
                ativarTemaEscuro();
            } else if(preferenciasConfig.getString(Utils.TEMA_LATINO, null).equalsIgnoreCase(Utils.ATIVADO)) {
                ativarTemaLatino();
            } else {
                ativarTemaPadrao();
            }
        }

            private void ativarTemaPadrao() {
                modificarCorDeFundoDaTela(R.color.color_white);
                modificarCorDoBarraDeAcao(R.color.color_purple_700);
            }

            private void ativarTemaEscuro() {
                modificarCorDeFundoDaTela(R.color.color_black);
                modificarCorDoBarraDeAcao(R.color.color_black);
            }

            private void ativarTemaLatino() {
                modificarCorDeFundoDaTela(R.color.color_green_dark);
                modificarCorDoBarraDeAcao(R.color.color_blue_dark);
            }

                private void modificarCorDeFundoDaTela(int cor) {
                    constraintLayout.setBackgroundResource(cor);
                }

                private void modificarCorDoBarraDeAcao(int cor) {
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(cor)));
                }


    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();

        // Persistência
        pegarConexaoComDatabase();

        mapearEnderecoDaLista();
        mostrarListaNaTelaComAdapterCustomizado();
        ativarCliqueRapidoNosItensDalistaParaEditar();
        ativarCliqueDemoradoNosItensDaListaParaAtivarMenuDeAcaoContextual();
        limitarQuantiaDeItensSelecionadosPorCliqueNalista();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_TELA_LISTAR_ROUPAS);
        }

        private void pegarConexaoComDatabase() {
            roupaDatabase = RoupaDatabase.getInstance(this);
        }

        private void mapearEnderecoDaLista() {
            enderecoDaListaDeRoupas = findViewById(R.id.listView_listaDeRoupas);
        }

        private void mostrarListaNaTelaComAdapterCustomizado() {
            roupaAdapter = new RoupaAdapter(this, buscarListaNoDatabase());
            enderecoDaListaDeRoupas.setAdapter(roupaAdapter);
        }

            private List<RoupaEntity> buscarListaNoDatabase() {
                roupaDatabase.getRoupaDAO().carregarTudo();
                return roupaDatabase.getRoupaDAO().listaDeRoupas;
            }

        private void limitarQuantiaDeItensSelecionadosPorCliqueNalista() {
            enderecoDaListaDeRoupas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }

        private void ativarCliqueRapidoNosItensDalistaParaEditar() {
            enderecoDaListaDeRoupas.setOnItemClickListener((parent, view, position, id) -> {

                roupaEntity = (RoupaEntity) parent.getItemAtPosition(position);
                roupaAdapter = (RoupaAdapter) parent.getAdapter();

                publicarMensagemNaTela(roupaEntity.getTipo()
                        .concat(" ")
                        .concat(roupaEntity.getCorPrincipal())
                        .concat(" ")
                        .concat(getString(R.string.selecionar)));

                gerarLogSobreQualItemFoiSelecionadoNaLista(position);

                CadastrarRoupasActivity
                        .atualizarRoupaComRetorno(ListarRoupasActivity.this, roupaEntity);
            });
        }

        private void ativarCliqueDemoradoNosItensDaListaParaAtivarMenuDeAcaoContextual() {
            enderecoDaListaDeRoupas.setOnItemLongClickListener((parent, view, position, id) -> {

                if (actionMode != null)
                    return false;

                roupaEntity = (RoupaEntity) parent.getItemAtPosition(position);
                roupaAdapter = (RoupaAdapter) parent.getAdapter();
                viewSelecionada = view;

                colorirBackgroundDoItemDaLista();

                publicarMensagemNaTela(roupaEntity.getTipo()
                        .concat(" ")
                        .concat(roupaEntity.getCorPrincipal())
                        .concat(" ")
                        .concat(getString(R.string.selecionar)));

                gerarLogSobreQualItemFoiSelecionadoNaLista(position);

                enderecoDaListaDeRoupas.setEnabled(false);
                actionMode = startSupportActionMode(actionModeCallback);

                return true;
            });
        }

            private void publicarMensagemNaTela(String mensagem) {
                Toast.makeText(getApplicationContext(),
                        mensagem,
                        Toast.LENGTH_SHORT)
                        .show();
            }

            private void gerarLogSobreQualItemFoiSelecionadoNaLista(int posicao) {
                Log.i(getString(R.string.roupa), " "
                        .concat(roupaEntity.getTipo())
                        .concat(" ")
                        .concat(roupaEntity.getCorPrincipal())
                        .concat(" - ")
                        .concat(getString(R.string.na_posicao))
                        .concat(": ")
                        .concat(String.valueOf(posicao)));
            }

            private void colorirBackgroundDoItemDaLista() {
                viewSelecionada.setBackgroundColor(Color.RED);
            }


    // ------------------------------ MENU DE AÇÃO CONTEXTUAL ------------------------------
    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

        // Chamado quando o modo de ação é criado; startActionMode() foi chamado
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflar um recurso de menu fornecendo itens de menu de contexto
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_contextual_item_listar_roupas, menu);
            return true;
        }

        // Chamado cada vez que o modo de ação é mostrado. Sempre chamado após onCreateActionMode, mas
        // pode ser chamado várias vezes se o modo for invalidado.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Retorna false se nada for feito
        }

        // Chamado quando o usuário seleciona um item de menu contextual
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            final int menuItemEditarRoupas = R.id.menu_item_editar_roupas;
            final int menuItemExcluirRoupas = R.id.menu_item_excluir_roupas;

            switch (item.getItemId()) {
                case menuItemEditarRoupas:
                    CadastrarRoupasActivity
                            .atualizarRoupaComRetorno(ListarRoupasActivity.this, roupaEntity);
                    mode.finish(); // Ação escolhida, então feche o CAB
                    return true;
                case menuItemExcluirRoupas:
                    ativarOuvidorDeConfirmarExcluirItemDoDatabase();
                    chamarModalParaConfirmarAcaoDeExcluirItemDoDatabase();
                    mode.finish(); // Ação escolhida, então feche o CAB
                    return true;
                default:
                    return false;
            }
        }

        // Chamado quando o usuário sai do modo de ação
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            viewSelecionada = null;
            actionMode = null;
            enderecoDaListaDeRoupas.setEnabled(true);
            notificarAdapterSobreModificacaoNaListView();
        }
    };

    private void ativarOuvidorDeConfirmarExcluirItemDoDatabase() {
        mensagem = getString(R.string.confirmar_exclusao);

        ouvidorDoModalDeConfirmarExcluirItemDoDatabase = (dialog, which) -> {
            switch(which) {
                case DialogInterface.BUTTON_POSITIVE:
                    roupaDatabase.getRoupaDAO().excluir(roupaEntity);

                    publicarMensagemNaTela(roupaEntity.getTipo()
                            .concat(" ")
                            .concat(roupaEntity.getCorPrincipal())
                            .concat(" ")
                            .concat(getString(R.string.excluido)));

                    notificarAdapterSobreModificacaoNaListView();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };
    }

    private void chamarModalParaConfirmarAcaoDeExcluirItemDoDatabase() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirmacao);
        builder.setMessage(mensagem);
        builder.setPositiveButton(R.string.sim, ouvidorDoModalDeConfirmarExcluirItemDoDatabase);
        builder.setNegativeButton(R.string.nao, ouvidorDoModalDeConfirmarExcluirItemDoDatabase);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void notificarAdapterSobreModificacaoNaListView() {
        roupaAdapter.notifyDataSetChanged();
    }


    // ------------------------------ OnActivityResult ------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == Activity.RESULT_OK) {
            Bundle bundle = intent.getExtras();

            if(bundle.getInt(CadastrarRoupasActivity.MODO) == CadastrarRoupasActivity.SALVAR) {
                roupaEntity = (RoupaEntity) bundle.getSerializable(CadastrarRoupasActivity.ROUPA);
                salvarRoupaNoDatabase();

                publicarMensagemNaTela(roupaEntity.getTipo()
                        .concat(" ")
                        .concat(roupaEntity.getCorPrincipal())
                        .concat(" ")
                        .concat(getString(R.string.salvo)));

            } else if(bundle.getInt(CadastrarRoupasActivity.MODO) == CadastrarRoupasActivity.ATUALIZAR) {
                roupaEntity = (RoupaEntity) bundle.getSerializable(CadastrarRoupasActivity.ROUPA);
                atualizarRoupaNoDatabase();

                publicarMensagemNaTela(roupaEntity.getTipo()
                        .concat(" ")
                        .concat(roupaEntity.getCorPrincipal())
                        .concat(" ")
                        .concat(getString(R.string.atualizado)));

            } else {
                publicarMensagemNaTela(getString(R.string.erro_retorno_incompativel));
            }
            notificarAdapterSobreModificacaoNaListView();
        }
    }

        private void salvarRoupaNoDatabase() {
            pegarConexaoComDatabase();
            roupaDatabase.getRoupaDAO().inserir(roupaEntity);
        }

        private void atualizarRoupaNoDatabase() {
            pegarConexaoComDatabase();
            roupaDatabase.getRoupaDAO().atualizar(roupaEntity);
        }


    // ------------------------------ MENU DE OPÇÕES ------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcoes_listar_roupas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int menuItemInfoApp = R.id.menu_item_sobre_app;
        final int menuItemHome = R.id.menu_item_tela_principal;
        final int menuItemAdicionarRoupas = R.id.menu_item_adicionar_roupas;
        final int menuItemCadastrarRoupas = R.id.menu_item_cadastrar_roupas;
        final int menuItemListarRoupas = R.id.menu_item_listar_roupas;

        switch (item.getItemId()) {
            case menuItemHome:
                publicarMensagemNaTela(getString(R.string.home));
                startActivity(new Intent(ListarRoupasActivity.this, MainActivity.class));
                return true;

            case menuItemAdicionarRoupas:
                publicarMensagemNaTela(getString(R.string.adicionar));
                CadastrarRoupasActivity.cadastrarRoupaComRetorno(ListarRoupasActivity.this); // StartActivityForResult
                return true;

            case menuItemInfoApp:
                publicarMensagemNaTela(getString(R.string.sobre));
                startActivity(new Intent(ListarRoupasActivity.this, InfoAppActivity.class));
                return true;

            case menuItemCadastrarRoupas:
                publicarMensagemNaTela(getString(R.string.cadastrar));
                startActivity(new Intent(ListarRoupasActivity.this, CadastrarRoupasActivity.class));
                return true;

            case menuItemListarRoupas:
                publicarMensagemNaTela(getString(R.string.listar));
                startActivity(new Intent(ListarRoupasActivity.this, ListarRoupasActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}