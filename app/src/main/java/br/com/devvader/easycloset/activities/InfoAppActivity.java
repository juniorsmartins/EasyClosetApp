package br.com.devvader.easycloset.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import br.com.devvader.easycloset.MainActivity;
import br.com.devvader.easycloset.R;

public final class InfoAppActivity extends AppCompatActivity {

    private final String tituloDeTelaInfoApp = getString(R.string.titulo_informacoes);

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);

        criarBotaoUpNaBarraDoApp();
    }

        private void criarBotaoUpNaBarraDoApp() {
            ActionBar barraDeAcao = getSupportActionBar();
            if(barraDeAcao != null)
                barraDeAcao.setDisplayHomeAsUpEnabled(true);
        }

    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();
    }

        private void colocarTituloNaTela() {
            setTitle(tituloDeTelaInfoApp);
        }


    // ------------------------------ MENU DE OPÇÕES ------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcoes_info_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int menuItemHome = R.id.menu_item_tela_principal;
        final int menuItemCadastrarRoupas = R.id.menu_item_cadastrar_roupas;
        final int menuItemListarRoupas = R.id.menu_item_listar_roupas;
        final int menuItemVoltar = R.id.menu_item_voltar;

        switch (item.getItemId()) {
            case menuItemHome:
                mostrarMensagemNaTela(getString(R.string.home));
                startActivity(new Intent(InfoAppActivity.this, MainActivity.class));
                return true;
            case menuItemCadastrarRoupas:
                mostrarMensagemNaTela(getString(R.string.cadastrar));
                startActivity(new Intent(InfoAppActivity.this, CadastrarRoupasActivity.class));
                return true;
            case menuItemListarRoupas:
                mostrarMensagemNaTela(getString(R.string.listar));
                startActivity(new Intent(InfoAppActivity.this, ListarRoupasActivity.class));
                return true;
            case menuItemVoltar:
                mostrarMensagemNaTela(getString(R.string.voltar));
                setResult(Activity.RESULT_CANCELED);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarMensagemNaTela(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

// OnBackPressed interfere no funcionamento do Botão UP - por isso foi desativado. Assim o botão Up retorna para Listar como pré-definido.
//    @Override
//    public void onBackPressed() {
//        setResult(Activity.RESULT_CANCELED);
//        finish();
//    }
}
