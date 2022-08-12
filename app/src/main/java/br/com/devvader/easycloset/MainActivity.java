package br.com.devvader.easycloset;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.devvader.easycloset.activities.CadastrarRoupasActivity;
import br.com.devvader.easycloset.activities.CadastrarUsuarioActivity;
import br.com.devvader.easycloset.activities.InfoAppActivity;
import br.com.devvader.easycloset.activities.ListarRoupasActivity;
import br.com.devvader.easycloset.activities.ListarUsuariosActivity;

public final class MainActivity extends AppCompatActivity {

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        switch (item.getItemId()) {
            case itemMainInfoApp:
                mostrarMensagemNaTela("Sobre App");
                startActivity(new Intent(MainActivity.this, InfoAppActivity.class));
                return true;
            case itemMainCadastrarUsuario:
                mostrarMensagemNaTela("Cadastrar Usuários");
                startActivity(new Intent(MainActivity.this, CadastrarUsuarioActivity.class));
                return true;
            case itemMainListarUsuario:
                mostrarMensagemNaTela("Listar Usuários");
                startActivity(new Intent(MainActivity.this, ListarUsuariosActivity.class));
                return true;
            case itemMainCadastrarRoupas:
                mostrarMensagemNaTela("Cadastrar Roupas");
                startActivity(new Intent(MainActivity.this, CadastrarRoupasActivity.class));
                return true;
            case itemMainListarRoupas:
                mostrarMensagemNaTela("Listar Roupas");
                startActivity(new Intent(MainActivity.this, ListarRoupasActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarMensagemNaTela(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}
