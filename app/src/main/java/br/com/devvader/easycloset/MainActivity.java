package br.com.devvader.easycloset;

import android.content.Intent;
import android.os.Bundle;
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

    private ConstraintLayout constraintLayoutMain;
    private TextView textViewRecadoProfessor;

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capturarEnderecosDeLayoutsParaManipularPreferencias();
    }

        private void capturarEnderecosDeLayoutsParaManipularPreferencias() {
            constraintLayoutMain = findViewById(R.id.constraint_layout_main);
            textViewRecadoProfessor = findViewById(R.id.text_view_main_recado_professor);
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
                modificarCorDoFundoDaTela(R.color.color_white);
                modificarCorDoTexto(R.color.color_black);

                return true;

            case itemMainPreferenciasTemaEscuro:
                item.setChecked(true);
                modificarCorDoFundoDaTela(R.color.color_black);
                modificarCorDoTexto(R.color.color_white);

                return true;

            case itemMainPreferenciasTemaLatino:
                item.setChecked(true);
                modificarCorDoFundoDaTela(R.color.color_green_dark);
                modificarCorDoTexto(R.color.color_yellow_light);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarMensagemNaTela(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    private void modificarCorDoFundoDaTela(int cor) {
        constraintLayoutMain.setBackgroundResource(cor);
    }

    private void modificarCorDoTexto(int cor) {
        textViewRecadoProfessor.setTextColor(getResources().getColor(cor));
    }
}
