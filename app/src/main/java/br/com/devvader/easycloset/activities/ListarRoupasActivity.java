package br.com.devvader.easycloset.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import java.util.List;
import br.com.devvader.easycloset.MainActivity;
import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.RoupaEntity;
import br.com.devvader.easycloset.domain.adapters.RoupaAdapter;
import br.com.devvader.easycloset.recursos.IRoupaRepository;
import br.com.devvader.easycloset.recursos.RoupaRepository;

public final class ListarRoupasActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_LISTAR_ROUPAS = "Listar Roupas";

    private final IRoupaRepository iRoupaRepository = new RoupaRepository();
    private ListView enderecoDaListaDeRoupas;
    private RoupaEntity roupaEntity;
    private RoupaAdapter roupaAdapter;
    private ActionMode actionMode;

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_roupas);
    }

    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();

        mapearEnderecoDaLista();
        mostrarListaNaTelaComAdapterCustomizado();

        enderecoDaListaDeRoupas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ativarCliqueRapidoNosItensDalistaParaEditar();
        ativarCliqueDemoradoNosItensDaListaParaMenuDeAcaoContextual();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_LISTAR_ROUPAS);
        }

        private void mapearEnderecoDaLista() {
            enderecoDaListaDeRoupas = findViewById(R.id.listView_listaDeRoupas);
        }

        private void mostrarListaNaTelaComAdapterCustomizado() {
            roupaAdapter = new RoupaAdapter(this, buscarListaNoRepository());
            enderecoDaListaDeRoupas.setAdapter(roupaAdapter);
        }

            private List<RoupaEntity> buscarListaNoRepository() {
                return iRoupaRepository.listar();
            }

        private void ativarCliqueRapidoNosItensDalistaParaEditar() {
            enderecoDaListaDeRoupas.setOnItemClickListener((parent, view, position, id) -> {
                roupaEntity = (RoupaEntity) parent.getItemAtPosition(position);

                publicarMensagemNaTelaDeQualRoupaFoiEscolhida();
                gerarLogSobreQualRoupaFoiEscolhida(position);
                CadastrarRoupasActivity.atualizarRoupaComRetorno(ListarRoupasActivity.this, roupaEntity);
            });
        }

        private void ativarCliqueDemoradoNosItensDaListaParaMenuDeAcaoContextual() {
            enderecoDaListaDeRoupas.setOnItemLongClickListener((parent, view, position, id) -> {

                if (actionMode != null)
                    return false;

                roupaEntity = (RoupaEntity) parent.getItemAtPosition(position);

                publicarMensagemNaTelaDeQualRoupaFoiEscolhida();
                gerarLogSobreQualRoupaFoiEscolhida(position);
                colorirBackgroundDoItemDaLista(view);

                enderecoDaListaDeRoupas.setEnabled(false); // O que faz?
                actionMode = startSupportActionMode(actionModeCallback); // O que faz?

                return true;
            });
        }

            private void publicarMensagemNaTelaDeQualRoupaFoiEscolhida() {
                Toast.makeText(getApplicationContext(),
                        roupaEntity.getTipo().concat(" ").concat(roupaEntity.getCorPrincipal()),
                        Toast.LENGTH_SHORT)
                        .show();
            }

            private void gerarLogSobreQualRoupaFoiEscolhida(int posicao) {
                Log.i("Roupa:", " " +
                        roupaEntity.getTipo().concat(" ").concat(roupaEntity.getCorPrincipal()) +
                        " - na posição: " + posicao);
            }

            private void colorirBackgroundDoItemDaLista(View view) {
                view.setBackgroundColor(Color.LTGRAY);
            }

    // ------------------------------ OnActivityResult ------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == Activity.RESULT_OK) {
            Bundle bundle = intent.getExtras();
            roupaEntity = (RoupaEntity) bundle.getSerializable(CadastrarRoupasActivity.ROUPA);

            if(bundle.getInt(CadastrarRoupasActivity.MODO) == CadastrarRoupasActivity.ATUALIZAR) {
                iRoupaRepository.excluirRoupa(roupaEntity);
                iRoupaRepository.atualizarRoupa(roupaEntity);
            }

            if(bundle.getInt(CadastrarRoupasActivity.MODO) == CadastrarRoupasActivity.SALVAR) {
                iRoupaRepository.salvarRoupa(roupaEntity);
            }

            roupaAdapter.notifyDataSetChanged();
        }
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
                mostrarMensagemNaTela("HOME");
                startActivity(new Intent(ListarRoupasActivity.this, MainActivity.class));
                return true;
            case menuItemAdicionarRoupas:
                mostrarMensagemNaTela("Adicionar Roupas");
                CadastrarRoupasActivity.cadastrarRoupaComRetorno(ListarRoupasActivity.this); // StartActivityForResult
                return true;
            case menuItemInfoApp:
                mostrarMensagemNaTela("Sobre App");
                startActivity(new Intent(ListarRoupasActivity.this, InfoAppActivity.class));
                return true;
            case menuItemCadastrarRoupas:
                mostrarMensagemNaTela("Cadastrar Roupas");
                startActivity(new Intent(ListarRoupasActivity.this, CadastrarRoupasActivity.class));
                return true;
            case menuItemListarRoupas:
                mostrarMensagemNaTela("Listar Roupas");
                startActivity(new Intent(ListarRoupasActivity.this, ListarRoupasActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarMensagemNaTela(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
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
                    iRoupaRepository.excluirRoupa(roupaEntity);
                    CadastrarRoupasActivity.atualizarRoupaComRetorno(ListarRoupasActivity.this, roupaEntity);
                    mode.finish(); // Ação escolhida, então feche o CAB
                    return true;
                case menuItemExcluirRoupas:
                    iRoupaRepository.excluirRoupa(roupaEntity);
                    roupaAdapter.notifyDataSetChanged();
                    mode.finish(); // Ação escolhida, então feche o CAB
                    return true;
                default:
                    return false;
            }
        }

        // Chamado quando o usuário sai do modo de ação
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };


}