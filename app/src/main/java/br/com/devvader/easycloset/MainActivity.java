package br.com.devvader.easycloset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.devvader.easycloset.activities.CadastrarUsuarioActivity;
import br.com.devvader.easycloset.activities.InfoAppActivity;
import br.com.devvader.easycloset.activities.ListarRoupasActivity;
import br.com.devvader.easycloset.activities.ListarUsuariosActivity;

public final class MainActivity extends AppCompatActivity {

    private Button enderecoBotaoCadastrarUsuario;
    private Button enderecoBotaoListarUsuarios;
    private Button enderecoBotaoListarRoupas;
    private Button enderecoBotaoInfosApp;

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
        ativarBotaoDeChamarTelaDeListarRoupas();
        ativarBotaoDeChamarTelaDeInfosDoApp();
    }

        private void capturarEnderecosDosBotoes() {
            enderecoBotaoCadastrarUsuario = findViewById(R.id.button_cadastrarUsuario);
            enderecoBotaoListarUsuarios = findViewById(R.id.button_listarUsuarios);
            enderecoBotaoListarRoupas = findViewById(R.id.button_listarRoupas);
            enderecoBotaoInfosApp = findViewById(R.id.button_infosApp);
        }

        private void ativarBotaoDeChamarTelaDeCadastrarUsuario() {
            enderecoBotaoCadastrarUsuario.setOnClickListener(new View.OnClickListener() {
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
            enderecoBotaoListarUsuarios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirTelaDeListarUsuarios();
                }
            });
        }

            private void abrirTelaDeListarUsuarios() {
                startActivity(new Intent(MainActivity.this, ListarUsuariosActivity.class));
            }

        private void ativarBotaoDeChamarTelaDeListarRoupas() {
            enderecoBotaoListarRoupas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirTelaDeListarRoupas();
                }
            });
        }

            private void abrirTelaDeListarRoupas() {
                startActivity(new Intent(MainActivity.this, ListarRoupasActivity.class));
            }

        private void ativarBotaoDeChamarTelaDeInfosDoApp() {
            enderecoBotaoInfosApp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirTelaDeInfosDoApp();
                }
            });
        }

            private void abrirTelaDeInfosDoApp() {
                startActivity(new Intent(MainActivity.this, InfoAppActivity.class));
            }
}
