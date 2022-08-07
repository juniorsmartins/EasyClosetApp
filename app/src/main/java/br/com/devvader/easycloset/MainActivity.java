package br.com.devvader.easycloset;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

    private Button enderecoBotaoCadastrarUsuario;
    private Button enderecoBotaoListarUsuarios;
    private Button enderecoBotaoCadastrarRoupas;
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
        ativarBotaoDeChamarTelaDeCadastrarRoupas();
        ativarBotaoDeChamarTelaDeListarRoupas();
        ativarBotaoDeChamarTelaDeInfosDoApp();
    }

        private void capturarEnderecosDosBotoes() {
            enderecoBotaoCadastrarUsuario = findViewById(R.id.button_cadastrarUsuario);
            enderecoBotaoListarUsuarios = findViewById(R.id.button_listarUsuarios);
            enderecoBotaoCadastrarRoupas = findViewById(R.id.button_cadastrarRoupas);
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

        private void ativarBotaoDeChamarTelaDeCadastrarRoupas() {
            enderecoBotaoCadastrarRoupas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirTelaDeCadastrarRoupas();
                }
            });
        }

            private void abrirTelaDeCadastrarRoupas() {
                startActivity(new Intent(MainActivity.this, CadastrarRoupasActivity.class));
            }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_opcoes, menu);
        return true;
    }

    // ----- parei aqui - vídeo de Menu de Opções - aos 13 minutos e 30 segundos ----

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_cadastrar_usuarios:
                mostrarMensagem("Cadastrar Usuários");
                break;
            case R.id.menu_item_listar_usuarios:
                mostrarMensagem("Listar Usuários");
                break;
            case R.id.menu_item_cadastrar_roupas:
                mostrarMensagem("Cadastrar Roupas");
                break;
            case R.id.menu_item_listar_roupas:
                mostrarMensagem("Listar Roupas");
                break;
            case R.id.menu_item_sobre:
                mostrarMensagem("Sobre");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarMensagem(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}
