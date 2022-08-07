package br.com.devvader.easycloset.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.RoupaEntity;
import br.com.devvader.easycloset.domain.adapters.RoupaAdapter;
import br.com.devvader.easycloset.recursos.IRoupaRepository;
import br.com.devvader.easycloset.recursos.RoupaRepository;

public final class ListarRoupasActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_LISTAR_ROUPAS = "Listar Roupas";

    private IRoupaRepository roupaRepository = new RoupaRepository();
    private ListView enderecoDaListaDeRoupas;
    private RoupaEntity roupa;
    private Button enderecoBotaoAdicionar;
    private RoupaAdapter roupaAdapter;

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_roupas);
    }


    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();

        mapearEnderecoDaLista();
        mostrarListaNaTelaComAdapterCustomizado();

        mapearEnderecoDoBotaoAdicionar();
        ativarBotaoAdicionar();

        ativarCliqueNosItensDalistaParaEditar();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_LISTAR_ROUPAS);
        }

        private void mapearEnderecoDaLista() {
            enderecoDaListaDeRoupas = findViewById(R.id.listView_listaDeRoupas);
        }

        private void mostrarListaNaTelaComAdapterCustomizado() {
            roupaAdapter = new RoupaAdapter(this, buscarListaNoRepository());
            enderecoDaListaDeRoupas.setAdapter(roupaAdapter);
        }

            private List<RoupaEntity> buscarListaNoRepository() {
                return roupaRepository.listar();
            }

        private void mapearEnderecoDoBotaoAdicionar() {
            enderecoBotaoAdicionar = findViewById(R.id.button_listar_adicionar_roupa);
        }

        private void ativarBotaoAdicionar() {
            enderecoBotaoAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CadastrarRoupasActivity.cadastrarRoupaComRetorno(ListarRoupasActivity.this);
                }
            });
        }

        private void ativarCliqueNosItensDalistaParaEditar() {
            enderecoDaListaDeRoupas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    roupa = (RoupaEntity) parent.getItemAtPosition(position);

                    gerarMensagemDeRoupaEscolhida();
                    gerarLogDaRoupaEscolhida(position);
                    CadastrarRoupasActivity.atualizarRoupaComRetorno(ListarRoupasActivity.this, roupa);
                }
            });
        }

            private void gerarMensagemDeRoupaEscolhida() {
                Toast.makeText(getApplicationContext(),
                        roupa.getTipo().concat(" ").concat(roupa.getCorPrincipal()),
                        Toast.LENGTH_SHORT)
                        .show();
            }

            private void gerarLogDaRoupaEscolhida(int posicao) {
                Log.i("Roupa:", " " +
                        roupa.getTipo().concat(" ").concat(roupa.getCorPrincipal()) +
                        " - na posição: " + posicao);
            }

    // ------------------------------ OnActivityResult ------------------------------
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == Activity.RESULT_OK) {
            Bundle bundle = intent.getExtras();
            RoupaEntity roupaNova = (RoupaEntity) bundle.getSerializable(CadastrarRoupasActivity.ROUPA);

            if(bundle.getInt(CadastrarRoupasActivity.MODO) == CadastrarRoupasActivity.ATUALIZAR) {
                roupaRepository.atualizarRoupa(roupaNova);
            }

            if(bundle.getInt(CadastrarRoupasActivity.MODO) == CadastrarRoupasActivity.SALVAR) {
                roupaRepository.salvarRoupa(roupaNova);
            }

            roupaAdapter.notifyDataSetChanged();
        }
    }
}