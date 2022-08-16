package br.com.devvader.easycloset.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import br.com.devvader.easycloset.MainActivity;
import br.com.devvader.easycloset.R;

public final class InfoAppActivity extends AppCompatActivity {

    private static final String tituloDeTelaInfoApp = "EasyCloset";

    private SharedPreferences preferenciasConfig;
    private SharedPreferences.Editor editorDePreferencias;
    private ConstraintLayout constraintLayoutMain;

    private TextView textoNomeDoCurso;
    private TextView textoNomeDoAutor;
    private TextView textoTelefoneDoAutor;
    private TextView textoEmailDoAutor;
    private TextView textoDescricaoDoApp;

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);

        criarBotaoUpNaBarraDoApp();

        mapearEnderecosParaManipularPreferencias();
        verificarPreferenciasPreConfiguradas();
    }

        private void criarBotaoUpNaBarraDoApp() {
            ActionBar barraDeAcao = getSupportActionBar();
            if(barraDeAcao != null)
                barraDeAcao.setDisplayHomeAsUpEnabled(true);
        }

        private void mapearEnderecosParaManipularPreferencias() {
            preferenciasConfig = PreferenceManager.getDefaultSharedPreferences(this);
            editorDePreferencias = preferenciasConfig.edit();
            constraintLayoutMain = findViewById(R.id.constraint_layout_info);

            textoNomeDoCurso = findViewById(R.id.text_view_info_curso);
            textoNomeDoAutor = findViewById(R.id.text_view_info_autor);
            textoTelefoneDoAutor = findViewById(R.id.text_view_info_celular);
            textoEmailDoAutor = findViewById(R.id.text_view_info_email);
            textoDescricaoDoApp = findViewById(R.id.text_view_info_descricao);
        }

        private void verificarPreferenciasPreConfiguradas() {
            if(preferenciasConfig.getString("temaPadrao", null).equalsIgnoreCase("ativado")) {
                ativarTemaPadrao();
            }
            if(preferenciasConfig.getString("temaEscuro", null).equalsIgnoreCase("ativado")) {
                ativarTemaEscuro();
            }
            if(preferenciasConfig.getString("temaLatino", null).equalsIgnoreCase("ativado")) {
                ativarTemaLatino();
            }
        }

            private void ativarTemaPadrao() {
                modificarCorDeFundoDaTela(R.color.color_white);
                modificarCorDoTexto(R.color.color_black);
                modificarCorDoBarraDeAcao(R.color.color_purple_700);
            }

            private void ativarTemaEscuro() {
                modificarCorDeFundoDaTela(R.color.color_black);
                modificarCorDoTexto(R.color.color_white);
                modificarCorDoBarraDeAcao(R.color.color_black);
            }

            private void ativarTemaLatino() {
                modificarCorDeFundoDaTela(R.color.color_green_dark);
                modificarCorDoTexto(R.color.color_yellow_light);
                modificarCorDoBarraDeAcao(R.color.color_blue_dark);
            }

                private void modificarCorDeFundoDaTela(int cor) {
                    constraintLayoutMain.setBackgroundResource(cor);
                }

                private void modificarCorDoTexto(int cor) {
                    textoNomeDoCurso.setTextColor(getResources().getColor(cor));
                    textoNomeDoCurso.setHintTextColor(getResources().getColor(cor));
                    textoNomeDoAutor.setTextColor(getResources().getColor(cor));
                    textoNomeDoAutor.setHintTextColor(getResources().getColor(cor));
                    textoTelefoneDoAutor.setTextColor(getResources().getColor(cor));
                    textoTelefoneDoAutor.setHintTextColor(getResources().getColor(cor));
                    textoEmailDoAutor.setTextColor(getResources().getColor(cor));
                    textoEmailDoAutor.setHintTextColor(getResources().getColor(cor));
                    textoDescricaoDoApp.setTextColor(getResources().getColor(cor));
                    textoDescricaoDoApp.setHintTextColor(getResources().getColor(cor));
                }

                private void modificarCorDoBarraDeAcao(int cor) {
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(cor)));
                }


    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();
    }

        private void colocarTituloNaTela() {
            tituloDeTelaInfoApp.concat(getString(R.string.titulo_informacoes));
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
