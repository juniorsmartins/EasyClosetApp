package br.com.devvader.easycloset.activities;

import androidx.annotation.Nullable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import br.com.devvader.easycloset.R;

public final class InfoAppActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_INFO_APP = "Informações";

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);
    }

    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_INFO_APP);
        }
}
