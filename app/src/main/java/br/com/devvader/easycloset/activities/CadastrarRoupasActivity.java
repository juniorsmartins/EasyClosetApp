package br.com.devvader.easycloset.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import br.com.devvader.easycloset.MainActivity;
import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.RoupaEntity;
import br.com.devvader.easycloset.recursos.RoupaRepository;

public final class CadastrarRoupasActivity extends AppCompatActivity {

    private final String tituloDeTelaCadastrarRoupas = getString(R.string.titulo_cadastrar_roupas);

    private final RoupaRepository roupaRepository = new RoupaRepository();
    private RoupaEntity roupaEntity;

    private Spinner enderecoCadastrarTipoDeRoupa;
    private Spinner enderecoCadastrarTecidoDaRoupa;
    private Spinner enderecoCadastrarCorPrincipalDaRoupa;
    private Spinner enderecoCadastrarTamanhoDaRoupa;

    private String tipoDeRoupa;
    private String tecidoDeRoupa;
    private String corPrincipalDeRoupa;
    private String tamanhoDeRoupa;

    private boolean camposValidados;
    private Intent capturarIntentDeListar;
    private int tipoDeCaminho;

    // Padrão estático para salvar roupas - com retorno de resultado
    public static final String MODO = "MODO";
    public static final int SALVAR = 1;
    public static void cadastrarRoupaComRetorno(AppCompatActivity activity) {
        Intent intent = new Intent(activity, CadastrarRoupasActivity.class);
        intent.putExtra(MODO, SALVAR);
        activity.startActivityForResult(intent, SALVAR);
    }

    // Padrão estático para atualizar roupas - com retorno de resultado
    public static final String ROUPA = "ROUPA";
    public static final int ATUALIZAR = 2;
    public static void atualizarRoupaComRetorno(AppCompatActivity activity, RoupaEntity roupa) {
        Intent intent = new Intent(activity, CadastrarRoupasActivity.class);
        intent.putExtra(MODO, ATUALIZAR);
        intent.putExtra(ROUPA, roupa);
        activity.startActivityForResult(intent, ATUALIZAR);
    }

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_roupas);

        criarBotaoUpNaBarraDoApp();
        caminhoDaActivityListarDentroDaActivityCadastrarParaAtualizarRoupaComRetorno();
    }

        private void criarBotaoUpNaBarraDoApp() {
            ActionBar barraDeAcao = getSupportActionBar();
            if(barraDeAcao != null)
                barraDeAcao.setDisplayHomeAsUpEnabled(true);
        }

        private void caminhoDaActivityListarDentroDaActivityCadastrarParaAtualizarRoupaComRetorno() {
            capturarIntentDeListar = getIntent();
            Bundle bundle = capturarIntentDeListar.getExtras();

            if(bundle != null) {
                tipoDeCaminho = bundle.getInt(MODO);
                if(tipoDeCaminho == ATUALIZAR) {
                    carregarDadosDaRoupaNoFormularioParaAtualizar();
                }
            }
        }

            private void carregarDadosDaRoupaNoFormularioParaAtualizar() {
                roupaEntity = (RoupaEntity) capturarIntentDeListar.getSerializableExtra(
                        CadastrarRoupasActivity.ROUPA);

                if(roupaEntity != null) {
                    mapearEnderecosDosCampos();

                    enderecoCadastrarTipoDeRoupa.setSelection(verificarPosicaoDoTipoDeRoupa());
                    enderecoCadastrarTecidoDaRoupa.setSelection(verificarPosicaoDoTecidoDaRoupa());
                    enderecoCadastrarCorPrincipalDaRoupa.setSelection(verificarPosicaoDaCorPrincipalDaRoupa());
                    enderecoCadastrarTamanhoDaRoupa.setSelection(verificarPosicaoDoTamanhoDaRoupa());
                }
            }

                private int verificarPosicaoDoTipoDeRoupa() {
                    switch(roupaEntity.getTipo()) {
                        case "Hat":
                        case "Chapéu":
                            return 1;
                        case "Cap":
                        case "Boné":
                            return 2;
                        case "Shirt":
                        case "Camisa":
                            return 3;
                        case "T-shirt":
                        case "Camiseta":
                            return 4;
                        case "Pants":
                        case "Calça":
                            return 5;
                        case "Shorts":
                        case "Bermuda":
                            return 6;
                        case "Underwear":
                        case "Cueca":
                            return 7;
                        case "Half":
                        case "Meia":
                            return 8;
                        case "Shoe":
                        case "Sapato":
                            return 9;
                        case "Sneakers":
                        case "Tênis":
                            return 10;
                        default:
                            return 0;
                    }
                }

                private int verificarPosicaoDoTecidoDaRoupa() {
                    switch(roupaEntity.getTecido()) {
                        case "Tricoline":
                            return 1;
                        case "Mesh":
                        case "Malha":
                            return 2;
                        case "Satin":
                        case "Cetim":
                            return 3;
                        case "Canvas":
                            return 4;
                        case "Cordoba":
                        case "Córdoba":
                            return 5;
                        case "Microfiber":
                        case "Microfibra":
                            return 6;
                        case "Cotton":
                        case "Algodão":
                            return 7;
                        case "Wool":
                        case "Lã":
                            return 8;
                        case "Silk":
                        case "Seda":
                            return 9;
                        case "Linen":
                        case "Linho":
                            return 10;
                        default:
                            return 0;
                    }
                }

                private int verificarPosicaoDaCorPrincipalDaRoupa() {
                    switch(roupaEntity.getCorPrincipal()) {
                        case "Blue":
                        case "Azul":
                            return 1;
                        case "Green":
                        case "Verde":
                            return 2;
                        case "White":
                        case "Branco":
                            return 3;
                        case "Black":
                        case "Preto":
                            return 4;
                        case "Yellow":
                        case "Amarelo":
                            return 5;
                        case "Brown":
                        case "Marrom":
                            return 6;
                        case "Orange":
                        case "Laranja":
                            return 7;
                        case "Purple":
                        case "Roxo":
                            return 8;
                        case "Pink":
                        case "Rosa":
                            return 9;
                        case "Red":
                        case "Vermelho":
                            return 10;
                        default:
                            return 0;
                    }
                }

                private int verificarPosicaoDoTamanhoDaRoupa() {
                    switch(roupaEntity.getTamanho()) {
                        case "PP":
                            return 1;
                        case "P":
                            return 2;
                        case "M":
                            return 3;
                        case "G":
                            return 4;
                        case "GG":
                            return 5;
                        case "XG":
                            return 6;
                        case "XGG":
                            return 7;
                        default:
                            return 0;
                    }
                }

    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();

        mapearEnderecosDosCampos();
        ativarSpinnersDoFormularioDeCadastrar();
    }

        private void colocarTituloNaTela() {
            setTitle(tituloDeTelaCadastrarRoupas);
        }

        private void mapearEnderecosDosCampos() {
            enderecoCadastrarTipoDeRoupa = findViewById(R.id.spinner_cadastrar_tipo_de_roupa);
            enderecoCadastrarTecidoDaRoupa = findViewById(R.id.spinner_cadastrar_tecido_de_roupa);
            enderecoCadastrarCorPrincipalDaRoupa = findViewById(R.id.spinner_cadastrar_cor_principal_de_roupa);
            enderecoCadastrarTamanhoDaRoupa = findViewById(R.id.spinner_cadastrar_tamanho_de_roupa);
        }

        private void ativarSpinnersDoFormularioDeCadastrar() {
            ativarSpinnerDeTipoDeRoupa();
            ativarSpinnerDeTecidoDeRoupa();
            ativarSpinnerDeCorPrincipalDeRoupa();
            ativarSpinnerDeTamanhoDeRoupa();
        }

            private void ativarSpinnerDeTipoDeRoupa() {
                enderecoCadastrarTipoDeRoupa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        tipoDeRoupa = (String) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });
            }

            private void ativarSpinnerDeTecidoDeRoupa() {
                enderecoCadastrarTecidoDaRoupa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        tecidoDeRoupa = (String) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });
            }

            private void ativarSpinnerDeCorPrincipalDeRoupa() {
                enderecoCadastrarCorPrincipalDaRoupa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        corPrincipalDeRoupa = (String) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });
            }

            private void ativarSpinnerDeTamanhoDeRoupa() {
                enderecoCadastrarTamanhoDaRoupa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        tamanhoDeRoupa = (String) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });
            }

        private void salvarCadastroDeRoupa() {
            validarFormulario();
            if(camposValidados) {
                pegarValoresDosSpinners();

                if(tipoDeCaminho == SALVAR) {
                    criarRoupa();
                    devolucaoDeResultadoParaStartActivityForResult(SALVAR);
                } else if(tipoDeCaminho == ATUALIZAR) {
                    alterarRoupa();
                    devolucaoDeResultadoParaStartActivityForResult(ATUALIZAR);
                } else {
                    caminhoBifurcaEntreSalvarOuEditarRoupa();
                    imprimirNomeNaTela();
                    limparCamposDoFormularioDeCadastrarRoupas();
                    finish();
                }
            } else {
                mostrarMensagemNaTela(getString(R.string.formulario_incompleto));
            }
        }

            private void validarFormulario() {
                camposValidados = false;
                if(enderecoCadastrarTipoDeRoupa.getSelectedItemPosition() == 0) {
                    enderecoCadastrarTipoDeRoupa.requestFocus();
                    return;
                }
                if(enderecoCadastrarTecidoDaRoupa.getSelectedItemPosition() == 0) {
                    enderecoCadastrarTecidoDaRoupa.requestFocus();
                    return;
                }
                if(enderecoCadastrarCorPrincipalDaRoupa.getSelectedItemPosition() == 0) {
                    enderecoCadastrarCorPrincipalDaRoupa.requestFocus();
                    return;
                }
                if(enderecoCadastrarTamanhoDaRoupa.getSelectedItemPosition() == 0) {
                    enderecoCadastrarTamanhoDaRoupa.requestFocus();
                    return;
                }
                camposValidados = true;
            }

            private void pegarValoresDosSpinners() {
                tipoDeRoupa = (String) enderecoCadastrarTipoDeRoupa.getSelectedItem();
                tecidoDeRoupa = (String) enderecoCadastrarTecidoDaRoupa.getSelectedItem();
                corPrincipalDeRoupa = (String) enderecoCadastrarCorPrincipalDaRoupa.getSelectedItem();
                tamanhoDeRoupa = (String) enderecoCadastrarTamanhoDaRoupa.getSelectedItem();
            }

            private void devolucaoDeResultadoParaStartActivityForResult(int salvarOuAtualizar) {
                Intent intent = getIntent();
                intent.putExtra(MODO, salvarOuAtualizar);
                intent.putExtra(CadastrarRoupasActivity.ROUPA, roupaEntity);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }

            private void caminhoBifurcaEntreSalvarOuEditarRoupa() {
                if(roupaEntity != null && roupaEntity.getId() > 0) {
                    alterarRoupa();
                    roupaRepository.atualizarRoupa(roupaEntity);
                } else {
                    criarRoupa();
                    roupaRepository.salvarRoupa(roupaEntity);
                }
            }

                private void alterarRoupa() {
                    roupaEntity.setTipo(tipoDeRoupa);
                    roupaEntity.setTecido(tecidoDeRoupa);
                    roupaEntity.setCorPrincipal(corPrincipalDeRoupa);
                    roupaEntity.setTamanho(tamanhoDeRoupa);
                }

                private void criarRoupa() {
                    roupaEntity = new RoupaEntity(tipoDeRoupa, tecidoDeRoupa, corPrincipalDeRoupa, tamanhoDeRoupa);
                }

            private void imprimirNomeNaTela() {
                Toast.makeText(CadastrarRoupasActivity.this,
                        roupaEntity.getTipo().concat(" ").concat(roupaEntity.getCorPrincipal()),
                        Toast.LENGTH_SHORT)
                        .show();
            }

            private void limparCamposDoFormularioDeCadastrarRoupas() {
                enderecoCadastrarTipoDeRoupa.setSelection(0);
                enderecoCadastrarTecidoDaRoupa.setSelection(0);
                enderecoCadastrarCorPrincipalDaRoupa.setSelection(0);
                enderecoCadastrarTamanhoDaRoupa.setSelection(0);
                roupaEntity = null;

                mostrarMensagemNaTela(getString(R.string.formulario_limpo));
            }

            private void direcionarFocoDoUsuarioParaPrimeiroCampoDoFormulario() {
                enderecoCadastrarTipoDeRoupa.requestFocus();
            }


    // ------------------------------ MENU DE OPÇÕES ------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcoes_cadastrar_roupas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int menuItemSalvarCadastrarRoupas = R.id.menu_item_salvar_cadastrar_roupas;
        final int menuItemLimparFormularioRoupas = R.id.menu_item_limpar_formulario_roupas;
        final int menuItemHome = R.id.menu_item_tela_principal;
        final int menuItemInfoApp = R.id.menu_item_sobre_app;
        final int menuItemCadastrarRoupas = R.id.menu_item_cadastrar_roupas;
        final int menuItemListarRoupas = R.id.menu_item_listar_roupas;

        switch (item.getItemId()) {
            case menuItemSalvarCadastrarRoupas:
                salvarCadastroDeRoupa();
                return true;
            case menuItemLimparFormularioRoupas:
                limparCamposDoFormularioDeCadastrarRoupas();
                direcionarFocoDoUsuarioParaPrimeiroCampoDoFormulario();
                return true;
            case menuItemHome:
                mostrarMensagemNaTela(getString(R.string.home));
                startActivity(new Intent(CadastrarRoupasActivity.this, MainActivity.class));
                return true;
            case menuItemInfoApp:
                mostrarMensagemNaTela(getString(R.string.sobre));
                startActivity(new Intent(CadastrarRoupasActivity.this, InfoAppActivity.class));
                return true;
            case menuItemCadastrarRoupas:
                mostrarMensagemNaTela(getString(R.string.cadastrar));
                startActivity(new Intent(CadastrarRoupasActivity.this, CadastrarRoupasActivity.class));
                return true;
            case menuItemListarRoupas:
                mostrarMensagemNaTela(getString(R.string.listar));
                startActivity(new Intent(CadastrarRoupasActivity.this, ListarRoupasActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarMensagemNaTela(String texto) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }
}
