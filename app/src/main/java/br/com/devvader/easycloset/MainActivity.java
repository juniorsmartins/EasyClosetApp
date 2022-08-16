package br.com.devvader.easycloset;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import br.com.devvader.easycloset.activities.CadastrarRoupasActivity;
import br.com.devvader.easycloset.activities.CadastrarUsuarioActivity;
import br.com.devvader.easycloset.activities.InfoAppActivity;
import br.com.devvader.easycloset.activities.ListarRoupasActivity;
import br.com.devvader.easycloset.activities.ListarUsuariosActivity;

public final class MainActivity extends AppCompatActivity {

    private SharedPreferences preferenciasConfig;
    private SharedPreferences.Editor editorDePreferencias;
    private ConstraintLayout constraintLayoutMain;
    private TextView textViewRecadoProfessor;

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapearEnderecosParaManipularPreferencias();
        verificarPreferenciasPreConfiguradas();
        ativarPreferenciasConfiguradas();
    }

        private void mapearEnderecosParaManipularPreferencias() {
            preferenciasConfig = PreferenceManager.getDefaultSharedPreferences(this);
            editorDePreferencias = preferenciasConfig.edit();
            constraintLayoutMain = findViewById(R.id.constraint_layout_main);
            textViewRecadoProfessor = findViewById(R.id.text_view_main_recado_professor);
        }

        private void verificarPreferenciasPreConfiguradas() {
            if(preferenciasConfig.getString("temaPadrao", null) == null)
                editorDePreferencias.putString("temaPadrao", "desativado");
            if(preferenciasConfig.getString("temaEscuro", null) == null)
                editorDePreferencias.putString("temaEscuro", "desativado");
            if(preferenciasConfig.getString("temaLatino", null) == null)
                editorDePreferencias.putString("temaLatino", "desativado");
            editorDePreferencias.apply();
        }

        private void ativarPreferenciasConfiguradas() {
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


    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
    }


    // ------------------------------ MENU DE OPÇÕES ------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcoes_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int itemMainInfoApp = R.id.menu_item_sobre_app;
        final int itemMainCadastrarUsuario = R.id.item_main_cadastrar_usuarios;
        final int itemMainListarUsuario = R.id.item_main_listar_usuarios;
        final int itemMainCadastrarRoupas = R.id.menu_item_cadastrar_roupas;
        final int itemMainListarRoupas = R.id.menu_item_listar_roupas;
        final int itemMainPreferenciasTemaPadrao = R.id.menu_preferencias_tema_padrao;
        final int itemMainPreferenciasTemaEscuro = R.id.menu_preferencias_tema_escuro;
        final int itemMainPreferenciasTemaLatino = R.id.menu_preferencias_tema_latino;

        switch (item.getItemId()) {
            case itemMainInfoApp:
                mostrarMensagemNaTela(getString(R.string.sobre));
                startActivity(new Intent(MainActivity.this, InfoAppActivity.class));
                return true;

            case itemMainCadastrarUsuario:
                mostrarMensagemNaTela(getString(R.string.cadastrar));
                startActivity(new Intent(MainActivity.this, CadastrarUsuarioActivity.class));
                return true;

            case itemMainListarUsuario:
                mostrarMensagemNaTela(getString(R.string.listar));
                startActivity(new Intent(MainActivity.this, ListarUsuariosActivity.class));
                return true;

            case itemMainCadastrarRoupas:
                mostrarMensagemNaTela(getString(R.string.cadastrar));
                startActivity(new Intent(MainActivity.this, CadastrarRoupasActivity.class));
                return true;

            case itemMainListarRoupas:
                mostrarMensagemNaTela(getString(R.string.listar));
                startActivity(new Intent(MainActivity.this, ListarRoupasActivity.class));
                return true;

            case itemMainPreferenciasTemaPadrao:
                item.setChecked(true);
                ativarTemaPadrao();
                return true;

            case itemMainPreferenciasTemaEscuro:
                item.setChecked(true);
                ativarTemaEscuro();
                return true;

            case itemMainPreferenciasTemaLatino:
                item.setChecked(true);
                ativarTemaLatino();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

        private void mostrarMensagemNaTela(String texto) {
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
        }

        private void ativarTemaPadrao() {
            editorDePreferencias.putString("temaPadrao", "ativado");
            editorDePreferencias.putString("temaEscuro", "desativado");
            editorDePreferencias.putString("temaLatino", "desativado");
            editorDePreferencias.apply();

            modificarCorDeFundoDaTela(R.color.color_white);
            modificarCorDoTexto(R.color.color_black);
            modificarCorDoBarraDeAcao(R.color.color_purple_700);
        }

        private void ativarTemaEscuro() {
            editorDePreferencias.putString("temaPadrao", "desativado");
            editorDePreferencias.putString("temaEscuro", "ativado");
            editorDePreferencias.putString("temaLatino", "desativado");
            editorDePreferencias.apply();

            modificarCorDeFundoDaTela(R.color.color_black);
            modificarCorDoTexto(R.color.color_white);
            modificarCorDoBarraDeAcao(R.color.color_black);
        }

        private void ativarTemaLatino() {
            editorDePreferencias.putString("temaPadrao", "desativado");
            editorDePreferencias.putString("temaEscuro", "desativado");
            editorDePreferencias.putString("temaLatino", "ativado");
            editorDePreferencias.apply();

            modificarCorDeFundoDaTela(R.color.color_green_dark);
            modificarCorDoTexto(R.color.color_yellow_light);
            modificarCorDoBarraDeAcao(R.color.color_blue_dark);
        }

            private void modificarCorDeFundoDaTela(int cor) {
                constraintLayoutMain.setBackgroundResource(cor);
            }

            private void modificarCorDoTexto(int cor) {
                textViewRecadoProfessor.setTextColor(getResources().getColor(cor));
                textViewRecadoProfessor.setHintTextColor(getResources().getColor(cor));
            }

            private void modificarCorDoBarraDeAcao(int cor) {
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(cor)));
            }
}
