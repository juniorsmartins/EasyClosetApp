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

    private Button botaoCadastrarUsuario;
    private Button botaoListarUsuarios;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        capturarEnderecosDosBotoes();
        ativarBotaoDeChamarTelaDeCadastrarUsuario();
        ativarBotaoDeChamarTelaDeListarUsuarios();
    }

        private void capturarEnderecosDosBotoes() {
            botaoCadastrarUsuario = findViewById(R.id.button_cadastrarUsuario);
            botaoListarUsuarios = findViewById(R.id.button_listarUsuarios);
        }

        private void ativarBotaoDeChamarTelaDeCadastrarUsuario() {
            botaoCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirTelaDeCadastrarUsuario();
                }
            });
        }

            private void abrirTelaDeCadastrarUsuario() {
                startActivity(new Intent(MainActivity.this, CadastrarUsuarioActivity.class));
            }

        private void ativarBotaoDeChamarTelaDeListarUsuarios() {
            botaoListarUsuarios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirTelaDeListarUsuários();
                }
            });
        }

            private void abrirTelaDeListarUsuários() {
                startActivity(new Intent(MainActivity.this, ListarUsuariosActivity.class));
            }
}
