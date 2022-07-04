package br.com.devvader.easycloset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import br.com.devvader.easycloset.activities.CadastrarUsuarioActivity;
import br.com.devvader.easycloset.activities.ListarUsuariosActivity;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botaoCadastrarUsuario = findViewById(R.id.button_cadastrarUsuario);
        botaoCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CadastrarUsuarioActivity.class));
            }
        });

        Button botaoListarUsuarios = findViewById(R.id.button_listarUsuarios);
        botaoListarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListarUsuariosActivity.class));
            }
        });
    }
}
