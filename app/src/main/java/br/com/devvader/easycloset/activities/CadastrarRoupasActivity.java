package br.com.devvader.easycloset.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import br.com.devvader.easycloset.R;

public final class CadastrarRoupasActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_CADASTRAR_ROUPAS = "Cadastrar Roupas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_roupas);
    }

    @Override
    protected void onResume() {
        super.onResume();

        colocarTituloNaTela();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_CADASTRAR_ROUPAS);
        }

}
