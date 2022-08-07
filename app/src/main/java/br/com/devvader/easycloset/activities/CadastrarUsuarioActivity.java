package br.com.devvader.easycloset.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.UsuarioEntity;
import br.com.devvader.easycloset.recursos.IUsuarioRepository;
import br.com.devvader.easycloset.recursos.UsuarioRepository;

public final class CadastrarUsuarioActivity extends AppCompatActivity {

    private static final String TITULO_DE_TELA_CADASTRAR_USUARIO = "Cadastrar Usuário";

    private IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private UsuarioEntity usuarioEntity = null;

    private RadioGroup enderecoSexoUsuario;
    private Spinner enderecoEscolaridadeUsuario;

    private Button enderecoBotaoSalvarUsuario;
    private Button enderecoBotaoLimparCamposCadastroUsuario;
    private Button enderecoBotaoVoltar;

    private EditText enderecoNomeUsuario;
    private EditText enderecoSobrenomeUsuario;
    private EditText enderecoCpfUsuario;
    private EditText enderecoFoneUsuario;
    private EditText enderecoEmailUsuario;
    private CheckBox enderecoAutorizoPublicidade;

    private String sexoUsuario;
    private String escolaridadeUsuario;

    private boolean camposValidados;
    Intent capturarIntentDeListar;
    private int tipoDeCaminho;
    Bundle bundle;

    // Padrão estático para salvar roupas - com retorno de resultado
    public static final String MODO = "MODO";
    public static final int SALVAR = 1;
    public static void cadastrarUsuarioComRetorno(AppCompatActivity activity) {
        Intent intent = new Intent(activity, CadastrarUsuarioActivity.class);
        intent.putExtra(MODO, SALVAR);
        activity.startActivityForResult(intent, SALVAR);
    }

    // Padrão estático para atualizar roupas - com retorno de resultado
    public static final String USUARIO = "USUARIO";
    public static final int ATUALIZAR = 2;
    public static void atualizarUsuarioComRetorno(AppCompatActivity activity, UsuarioEntity usuario) {
        Intent intent = new Intent(activity, CadastrarUsuarioActivity.class);
        intent.putExtra(MODO, ATUALIZAR);
        intent.putExtra(USUARIO, usuario);
        activity.startActivityForResult(intent, ATUALIZAR);
    }

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        caminhoDaActivityListarDentroDaActivityCadastrarParaAtualizarUsuarioComRetorno();
    }

        private void caminhoDaActivityListarDentroDaActivityCadastrarParaAtualizarUsuarioComRetorno() {
            capturarIntentDeListar = getIntent();
            bundle = capturarIntentDeListar.getExtras();

            if(bundle != null) {
                tipoDeCaminho = bundle.getInt(MODO);
                if(tipoDeCaminho == ATUALIZAR) {
                    carregarDadosDoUsuarioNoFormularioParaAtualizar();
                }
            }
        }

            private void carregarDadosDoUsuarioNoFormularioParaAtualizar() {
                usuarioEntity = (UsuarioEntity) capturarIntentDeListar.getSerializableExtra(
                        CadastrarUsuarioActivity.USUARIO);

                if(usuarioEntity != null) {
                    mapearEnderecosDosCampos();
                    mapearEnderecosDoRadioGroupSexoAndSpinnerEscolaridade();

                    enderecoNomeUsuario.setText(usuarioEntity.getNome());
                    enderecoSobrenomeUsuario.setText(usuarioEntity.getSobrenome());
                    enderecoCpfUsuario.setText(usuarioEntity.getCpf());
                    enderecoFoneUsuario.setText(usuarioEntity.getFone());
                    enderecoEmailUsuario.setText(usuarioEntity.getEmail());
                    enderecoSexoUsuario.check(usuarioEntity.getSexo().equalsIgnoreCase("Masculino") ?
                            R.id.radioButton_sexoMasculino : R.id.radioButton_sexoFeminino);
                    enderecoEscolaridadeUsuario.setSelection(verificarPosicaoDaEscolaridadeDoUsuario());
                    enderecoAutorizoPublicidade.setChecked(usuarioEntity.getAutorizo());
                }
            }

                private int verificarPosicaoDaEscolaridadeDoUsuario() {
                    switch(usuarioEntity.getEscolaridade()) {
                        case "Doutorado":
                            return 1;
                        case "Mestrado":
                            return 2;
                        case "Especializações e MBAs":
                            return 3;
                        case "Superior":
                            return 4;
                        case "Técnico":
                            return 5;
                        case "Ensino Médio":
                            return 6;
                        case "Ensino Fundamental":
                            return 7;
                        case "Ensino Fundamental Incompleto":
                            return 8;
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

        mapearEnderecosDoRadioGroupSexoAndSpinnerEscolaridade();
        ativarRadioGroupDeSexoDoUsuario();
        ativarSpinnerDeEscolaridadeDoUsuario();

        mapearEnderecosDosBotoes();
        ativarButtonsDoFormularioDeCadastrar();
    }

        private void colocarTituloNaTela() {
            setTitle(TITULO_DE_TELA_CADASTRAR_USUARIO);
        }

        private void mapearEnderecosDosCampos() {
            enderecoNomeUsuario = findViewById(R.id.editText_nomeUsuario);
            enderecoSobrenomeUsuario = findViewById(R.id.editText_sobrenomeUsuario);
            enderecoCpfUsuario = findViewById(R.id.editText_cpfUsuario);
            enderecoFoneUsuario = findViewById(R.id.editText_foneUsuario);
            enderecoEmailUsuario = findViewById(R.id.editText_emailUsuario);
            enderecoAutorizoPublicidade = findViewById(R.id.checkBox_autorizoPublicidade);
        }

        private void mapearEnderecosDoRadioGroupSexoAndSpinnerEscolaridade() {
            enderecoSexoUsuario = findViewById(R.id.radioGroup_sexo);
            enderecoEscolaridadeUsuario = findViewById(R.id.spinner_escolaridadeUsuario);
        }

        private void ativarRadioGroupDeSexoDoUsuario() {
            enderecoSexoUsuario.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButton_sexoMasculino:
                            sexoUsuario = "Masculino";
                            break;
                        case R.id.radioButton_sexoFeminino:
                            sexoUsuario = "Feminino";
                            break;
                    }
                }
            });
        }

        private void ativarSpinnerDeEscolaridadeDoUsuario() {
            enderecoEscolaridadeUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    escolaridadeUsuario = (String) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });
        }

        private void mapearEnderecosDosBotoes() {
            enderecoBotaoSalvarUsuario = findViewById(R.id.button_salvarCadastroUsuario);
            enderecoBotaoLimparCamposCadastroUsuario = findViewById(R.id.button_limparCadastroUsuario);
            enderecoBotaoVoltar = findViewById(R.id.button_voltar_cadastrar_usuario);
        }

        private void ativarButtonsDoFormularioDeCadastrar() {
            ativarBotaoDeSalvarCadastrarUsuario();
            ativarBotaoDeLimparFormularioDeCadastrar();
            ativarButtonVoltarCadastrar();
        }

            private void ativarBotaoDeSalvarCadastrarUsuario() {
                enderecoBotaoSalvarUsuario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        validarFormulario();
                        if(camposValidados) {
                            pegarEscolaridadeDoUsuarioNoSpinner();

                            if(tipoDeCaminho == SALVAR) {
                              criarUsuario();

                              Intent intent = getIntent();
                              intent.putExtra(MODO, SALVAR);
                              intent.putExtra(CadastrarUsuarioActivity.USUARIO, usuarioEntity);
                              setResult(Activity.RESULT_OK, intent);
                              finish();
                            } else if(tipoDeCaminho == ATUALIZAR) {
                              alterarUsuario();

                                Intent intent = getIntent();
                                intent.putExtra(MODO, ATUALIZAR);
                                intent.putExtra(CadastrarUsuarioActivity.USUARIO, usuarioEntity);
                                setResult(Activity.RESULT_OK, intent);
                                finish();
                            } else if(tipoDeCaminho != SALVAR && tipoDeCaminho != ATUALIZAR) {
                                caminhoBifurcaEntreSalvarOuEditarRoupa();
                                imprimirNomeNaTela();
                                limparCamposDoFormularioDeCadastrarUsuario();
                                finish();
                            }
                        } else {
                            Toast.makeText(CadastrarUsuarioActivity.this,
                                    R.string.formulario_incompleto,
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
            }

                private void validarFormulario() {
                    camposValidados = false;
                    if(TextUtils.isEmpty(enderecoNomeUsuario.getText().toString())) {
                        enderecoNomeUsuario.setError("*");
                        enderecoNomeUsuario.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(enderecoSobrenomeUsuario.getText().toString())) {
                        enderecoSobrenomeUsuario.setError("*");
                        enderecoSobrenomeUsuario.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(enderecoCpfUsuario.getText().toString())) {
                        enderecoCpfUsuario.setError("*");
                        enderecoCpfUsuario.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(enderecoFoneUsuario.getText().toString())) {
                        enderecoFoneUsuario.setError("*");
                        enderecoFoneUsuario.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(enderecoEmailUsuario.getText().toString())) {
                        enderecoEmailUsuario.setError("*");
                        enderecoEmailUsuario.requestFocus();
                        return;
                    }
                    if(enderecoSexoUsuario.getCheckedRadioButtonId() == -1) {
                        enderecoSexoUsuario.requestFocus();
                        return;
                    }
                    if(enderecoEscolaridadeUsuario.getSelectedItemPosition() == 0) {
                        enderecoEscolaridadeUsuario.requestFocus();
                        return;
                    }
                    if(!enderecoAutorizoPublicidade.isChecked()) {
                        enderecoAutorizoPublicidade.setError("*");
                        enderecoAutorizoPublicidade.requestFocus();
                        return;
                    }
                    camposValidados = true;
                }

                private void pegarEscolaridadeDoUsuarioNoSpinner() {
                    escolaridadeUsuario = (String) enderecoEscolaridadeUsuario.getSelectedItem();
                }

                private void caminhoBifurcaEntreSalvarOuEditarRoupa() {
                    if(usuarioEntity != null && usuarioEntity.getIdUsuario() > 0) {
                        alterarUsuario();
                        usuarioRepository.atualizarUsuario(usuarioEntity);
                    } else {
                        criarUsuario();
                        usuarioRepository.salvarUsuario(usuarioEntity);
                    }
                }

                    private void alterarUsuario() {
                        usuarioEntity.setNome(enderecoNomeUsuario.getText().toString());
                        usuarioEntity.setSobrenome(enderecoSobrenomeUsuario.getText().toString());
                        usuarioEntity.setCpf(enderecoCpfUsuario.getText().toString());
                        usuarioEntity.setFone(enderecoFoneUsuario.getText().toString());
                        usuarioEntity.setEmail(enderecoEmailUsuario.getText().toString());
                        usuarioEntity.setSexo(sexoUsuario);
                        usuarioEntity.setEscolaridade(escolaridadeUsuario);
                        usuarioEntity.setAutorizo(enderecoAutorizoPublicidade.isChecked());
                    }

                    private void criarUsuario() {
                        usuarioEntity = new UsuarioEntity(
                                enderecoNomeUsuario.getText().toString(),
                                enderecoSobrenomeUsuario.getText().toString(),
                                enderecoCpfUsuario.getText().toString(),
                                enderecoFoneUsuario.getText().toString(),
                                enderecoEmailUsuario.getText().toString(),
                                sexoUsuario,
                                escolaridadeUsuario,
                                enderecoAutorizoPublicidade.isChecked());
                    }

                private void imprimirNomeNaTela() {
                    Toast.makeText(CadastrarUsuarioActivity.this,
                            usuarioEntity.getNome().concat(" ").concat(usuarioEntity.getSobrenome()),
                            Toast.LENGTH_SHORT).show();
                }

        private void ativarBotaoDeLimparFormularioDeCadastrar() {
            enderecoBotaoLimparCamposCadastroUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mapearEnderecosDosCampos();
                    limparCamposDoFormularioDeCadastrarUsuario();
                    direcionarFocoDoUsuarioParaCampoNome();
                }
            });
        }

            private void limparCamposDoFormularioDeCadastrarUsuario() {
                enderecoNomeUsuario.setText(null);
                enderecoSobrenomeUsuario.setText(null);
                enderecoCpfUsuario.setText(null);
                enderecoFoneUsuario.setText(null);
                enderecoEmailUsuario.setText(null);
                enderecoSexoUsuario.clearCheck();
                enderecoEscolaridadeUsuario.setSelection(0);
                enderecoAutorizoPublicidade.setChecked(false);
                usuarioEntity = null;

                Toast.makeText(CadastrarUsuarioActivity.this,
                        "Formulário Limpo!",
                        Toast.LENGTH_SHORT)
                        .show();
            }

            private void direcionarFocoDoUsuarioParaCampoNome() {
                enderecoNomeUsuario.requestFocus();
            }

        private void ativarButtonVoltarCadastrar() {
            enderecoBotaoVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        @Override
        public void onBackPressed() {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
}