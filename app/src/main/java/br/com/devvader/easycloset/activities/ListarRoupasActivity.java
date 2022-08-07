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
    private List<RoupaEntity> listaDeRoupas;
    private RoupaEntity roupa;
    private Button enderecoBotaoAdicionarRoupa;
    private RoupaAdapter roupaAdapter;

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_roupas);

        System.out.println("\n\n\nTESTE - ONCREATE - LISTAR ----------------\n\n\n");
    }


    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();

        System.out.println("\n\n\nTESTE - ONRESUME - LISTAR ----------------\n\n\n");

        mapearEnderecoDaListaDeRoupas();
        mostrarListaDeRoupasNaTelaComAdapterCustomizado();

        mapearEnderecoDoBotaoAdicionar();
        ativarBotaoAdicionarRoupa();

        ativarCliqueNosItensDalistaParaEditarRoupa();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_LISTAR_ROUPAS);
        }

        private void mapearEnderecoDaListaDeRoupas() {
            enderecoDaListaDeRoupas = findViewById(R.id.listView_listaDeRoupas);
        }

        private void mostrarListaDeRoupasNaTelaComAdapterCustomizado() {
            roupaAdapter = new RoupaAdapter(this, buscarListaDeRoupasNoRepository());
            enderecoDaListaDeRoupas.setAdapter(roupaAdapter);
            System.out.println("\n\n\n--------------- mostrarListaDeRoupasNaTelaComAdapterCustomizado() ----------------\n\n\n");
        }

            private List<RoupaEntity> buscarListaDeRoupasNoRepository() {
                listaDeRoupas = roupaRepository.listarRoupas();
                return listaDeRoupas;
            }

        private void mapearEnderecoDoBotaoAdicionar() {
            enderecoBotaoAdicionarRoupa = findViewById(R.id.button_listar_adicionar_roupa);
        }

        private void ativarBotaoAdicionarRoupa() {
            enderecoBotaoAdicionarRoupa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CadastrarRoupasActivity.cadastrarRoupaComRetorno(ListarRoupasActivity.this);
                }
            });
        }

        private void ativarCliqueNosItensDalistaParaEditarRoupa() {
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

        mapearEnderecoDaListaDeRoupas();
        mostrarListaDeRoupasNaTelaComAdapterCustomizado();
        System.out.println("\n\n\nTESTE - ONACTIVITYRESULT - LISTAR ----------------\n\n\n");

        if(resultCode == Activity.RESULT_OK) {
            Bundle bundle = intent.getExtras();
            roupa = (RoupaEntity) bundle.getSerializable(CadastrarRoupasActivity.ROUPA);
            System.out.println("OnActivityResult - " + roupa);

            if(bundle.getInt(CadastrarRoupasActivity.MODO) == CadastrarRoupasActivity.ATUALIZAR) {
                roupaRepository = new RoupaRepository();
                roupaRepository.atualizarRoupa(roupa);
                System.out.println("\n\n---------------- ATUALIZAR ---------------------\n\n");
            }

            if(bundle.getInt(CadastrarRoupasActivity.MODO) == CadastrarRoupasActivity.SALVAR) {
                roupaRepository.salvarRoupa(roupa);
                System.out.println("\n\n---------------- SALVAR ---------------------\n\n");
            }

            roupaRepository.listarRoupas()
                    .stream()
                    .forEach(item -> System.out.println("ForEach - " + item.toString()));

            roupaAdapter.notifyDataSetChanged();
        }
    }
}