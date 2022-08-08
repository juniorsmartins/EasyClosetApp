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

    final int itemMainCadastrarUsuario = R.id.item_main_cadastrar_usuarios;
    final int itemMainListarUsuario = R.id.item_main_listar_usuarios;
    final int itemMainCadastrarRoupas = R.id.item_main_cadastrar_roupas;
    final int itemMainListarRoupas = R.id.item_main_listar_roupas;
    final int itemMainInfoApp = R.id.menu_item_sobre;

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
        getMenuInflater().inflate(R.menu.main_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case itemMainCadastrarUsuario:
                mostrarMensagem("Cadastrar Usuários");
                startActivity(new Intent(MainActivity.this, CadastrarUsuarioActivity.class));
                return true;
            case itemMainListarUsuario:
                mostrarMensagem("Listar Usuários");
                startActivity(new Intent(MainActivity.this, ListarUsuariosActivity.class));
                return true;
            case itemMainCadastrarRoupas:
                mostrarMensagem("Cadastrar Roupas");
                startActivity(new Intent(MainActivity.this, CadastrarRoupasActivity.class));
                return true;
            case itemMainListarRoupas:
                mostrarMensagem("Listar Roupas");
                startActivity(new Intent(MainActivity.this, ListarRoupasActivity.class));
                return true;
            case itemMainInfoApp:
                mostrarMensagem("Sobre");
                startActivity(new Intent(MainActivity.this, InfoAppActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarMensagem(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}
