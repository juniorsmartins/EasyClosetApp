package br.com.devvader.easycloset.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.devvader.easycloset.R;

public final class InfoAppActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_INFO_APP = "Informações";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);
    }

    @Override
    protected void onResume() {
        super.onResume();

        colocarTituloNaTela();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_INFO_APP);
        }
}
