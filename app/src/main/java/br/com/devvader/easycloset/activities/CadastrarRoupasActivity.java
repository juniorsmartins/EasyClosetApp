package br.com.devvader.easycloset.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.RoupaEntity;
import br.com.devvader.easycloset.recursos.RoupaRepository;

public final class CadastrarRoupasActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_CADASTRAR_ROUPAS = "Cadastrar Roupas";

    private RoupaRepository roupaRepository = new RoupaRepository();
    private RoupaEntity roupaEntity = null;

    private Spinner enderecoCadastrarTipoDeRoupa;
    private Spinner enderecoCadastrarTecidoDaRoupa;
    private Spinner enderecoCadastrarCorPrincipalDaRoupa;
    private Spinner enderecoCadastrarTamanhoDaRoupa;

    private String tipoDeRoupa;
    private String tecidoDeRoupa;
    private String corPrincipalDeRoupa;
    private String tamanhoDeRoupa;

    private Button enderecoBotaoSalvarRoupa;
    private Button enderecoBotaoLimparFormulario;

    private boolean camposValidados;
    Intent capturadaIntentDeAtualizarRoupas = null;

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_roupas);

        caminhoDeAtualizarRegistroDeRoupas();
    }

        private void caminhoDeAtualizarRegistroDeRoupas() {
            capturarIntentVindoDaTelaDeListarRoupas();
            carregarDadosDaRoupaNoFormularioParaAtualizar();
        }

            private void capturarIntentVindoDaTelaDeListarRoupas() {
                capturadaIntentDeAtualizarRoupas = getIntent();
            }

            private void carregarDadosDaRoupaNoFormularioParaAtualizar() {
                roupaEntity = (RoupaEntity) capturadaIntentDeAtualizarRoupas
                        .getSerializableExtra(ListarRoupasActivity.ROUPA);

                if(roupaEntity != null) {
                    mapearEnderecosDosCampos();
                    ativarSpinnersDoFormularioDeCadastrarRoupas();

                    enderecoCadastrarTipoDeRoupa.setSelection(verificarPosicaoDoTipoDeRoupa());
                    enderecoCadastrarTecidoDaRoupa.setSelection(verificarPosicaoDoTecidoDaRoupa());
                    enderecoCadastrarCorPrincipalDaRoupa.setSelection(verificarPosicaoDaCorPrincipalDaRoupa());
                    enderecoCadastrarTamanhoDaRoupa.setSelection(verificarPosicaoDoTamanhoDaRoupa());
                }
            }

                private int verificarPosicaoDoTipoDeRoupa() {
                    switch(roupaEntity.getTipo()) {
                        case "Chapéu":
                            return 1;
                        case "Boné":
                            return 2;
                        case "Camisa":
                            return 3;
                        case "Camiseta":
                            return 4;
                        case "Calça":
                            return 5;
                        case "Bermuda":
                            return 6;
                        case "Cueca":
                            return 7;
                        case "Meia":
                            return 8;
                        case "Sapato":
                            return 9;
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
                        case "Malha":
                            return 2;
                        case "Cetim":
                            return 3;
                        case "Canvas":
                            return 4;
                        case "Cordoba":
                            return 5;
                        case "Microfibra":
                            return 6;
                        case "Algodão":
                            return 7;
                        case "Lã":
                            return 8;
                        case "Seda":
                            return 9;
                        case "Linho":
                            return 10;
                        default:
                            return 0;
                    }
                }

                private int verificarPosicaoDaCorPrincipalDaRoupa() {
                    switch(roupaEntity.getCorPrincipal()) {
                        case "Azul":
                            return 1;
                        case "Verde":
                            return 2;
                        case "Branco":
                            return 3;
                        case "Preto":
                            return 4;
                        case "Amarelo":
                            return 5;
                        case "Marrom":
                            return 6;
                        case "Laranja":
                            return 7;
                        case "Roxo":
                            return 8;
                        case "Rosa":
                            return 9;
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
        ativarSpinnersDoFormularioDeCadastrarRoupas();

        mapearEnderecosDosBotoes();
        ativarButtonsDoFormularioDeCadastrarRoupas();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_CADASTRAR_ROUPAS);
        }

        private void mapearEnderecosDosCampos() {
            enderecoCadastrarTipoDeRoupa = findViewById(R.id.spinner_cadastrar_tipo_de_roupa);
            enderecoCadastrarTecidoDaRoupa = findViewById(R.id.spinner_cadastrar_tecido_de_roupa);
            enderecoCadastrarCorPrincipalDaRoupa = findViewById(R.id.spinner_cadastrar_cor_principal_de_roupa);
            enderecoCadastrarTamanhoDaRoupa = findViewById(R.id.spinner_cadastrar_tamanho_de_roupa);
        }

        private void ativarSpinnersDoFormularioDeCadastrarRoupas() {
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

        private void mapearEnderecosDosBotoes() {
            enderecoBotaoSalvarRoupa = findViewById(R.id.button_salvar_cadastrar_roupa);
            enderecoBotaoLimparFormulario = findViewById(R.id.button_limpar_cadastrar_roupa);
        }

        private void ativarButtonsDoFormularioDeCadastrarRoupas() {
            ativarButtonSalvarCadastrarRoupas();
            ativarButtonLimparFormularioCadastrarRoupas();
        }

            private void ativarButtonSalvarCadastrarRoupas() {
                enderecoBotaoSalvarRoupa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        validarFormulario();
                        if(camposValidados) {
                            pegarValoresDosSpinners();
                            caminhoBifurcaEntreSalvarOuEditarRoupa();
                            imprimirNomeDaRoupaNaTela();
                            limparCamposDoFormularioDeCadastrarRoupas();
                            finish();
                        } else {
                            Toast.makeText(CadastrarRoupasActivity.this,
                                            R.string.formulario_incompleto,
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
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

                private void caminhoBifurcaEntreSalvarOuEditarRoupa() {
                    if(roupaEntity != null && roupaEntity.getIdRoupa() > 0) {
                        atualizarRoupa();
                        roupaRepository.editarRoupa(roupaEntity);
                    } else {
                        salvarRoupa();
                        roupaRepository.salvarRoupa(roupaEntity);
                    }
                }

                    private void atualizarRoupa() {
                        roupaEntity.setTipo(tipoDeRoupa);
                        roupaEntity.setTecido(tecidoDeRoupa);
                        roupaEntity.setCorPrincipal(corPrincipalDeRoupa);
                        roupaEntity.setTamanho(tamanhoDeRoupa);
                    }

                    private void salvarRoupa() {
                        criarRoupa();
                        roupaRepository.salvarRoupa(roupaEntity);
                    }

                        private void criarRoupa() {
                            roupaEntity = new RoupaEntity(tipoDeRoupa, tamanhoDeRoupa, corPrincipalDeRoupa, tecidoDeRoupa);
                        }

                private void imprimirNomeDaRoupaNaTela() {
                    Toast.makeText(CadastrarRoupasActivity.this,
                            roupaEntity.getTipo().concat(" ").concat(roupaEntity.getCorPrincipal()),
                            Toast.LENGTH_SHORT)
                            .show();
                }

            private void ativarButtonLimparFormularioCadastrarRoupas() {
                enderecoBotaoLimparFormulario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mapearEnderecosDosCampos();
                        limparCamposDoFormularioDeCadastrarRoupas();
                        direcionarFocoDoUsuarioParaPrimeiroCampoDoFormulario();
                    }
                });
            }

                private void limparCamposDoFormularioDeCadastrarRoupas() {
                    enderecoCadastrarTipoDeRoupa.setSelection(0);
                    enderecoCadastrarTecidoDaRoupa.setSelection(0);
                    enderecoCadastrarCorPrincipalDaRoupa.setSelection(0);
                    enderecoCadastrarTamanhoDaRoupa.setSelection(0);
                    roupaEntity = null;

                    Toast.makeText(CadastrarRoupasActivity.this,
                                    "Formulário Limpo!",
                                    Toast.LENGTH_SHORT)
                            .show();
                }

                private void direcionarFocoDoUsuarioParaPrimeiroCampoDoFormulario() {
                    enderecoCadastrarTipoDeRoupa.requestFocus();
                }
}
