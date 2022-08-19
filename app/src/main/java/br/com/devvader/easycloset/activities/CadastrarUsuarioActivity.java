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

    private final IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private final String MASCULINO = "Masculino";
    private final String FEMININO = "Feminino";

    private UsuarioEntity usuarioEntity;
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
    private Intent capturarIntentDeListar;
    private int tipoDeCaminho;

    private final int radioButtonMasculino = R.id.radioButton_sexoMasculino;
    private final int radioButtonFeminino = R.id.radioButton_sexoFeminino;

    // Padrão estático para salvar roupas - com retorno de resultado
    public static final String MODO = "MODO";
    public static final int SALVAR = 1;
    public static void cadastrarUsuarioComRetorno(AppCompatActivity activityOrigem) {
        Intent intent = new Intent(activityOrigem, CadastrarUsuarioActivity.class);
        intent.putExtra(MODO, SALVAR);
        activityOrigem.startActivityForResult(intent, SALVAR);
    }

    // Padrão estático para atualizar roupas - com retorno de resultado
    public static final String USUARIO = "USUARIO";
    public static final int ATUALIZAR = 2;
    public static void atualizarUsuarioComRetorno(AppCompatActivity activityOrigem, UsuarioEntity usuarioParaAtualizar) {
        Intent intent = new Intent(activityOrigem, CadastrarUsuarioActivity.class);
        intent.putExtra(MODO, ATUALIZAR);
        intent.putExtra(USUARIO, usuarioParaAtualizar);
        activityOrigem.startActivityForResult(intent, ATUALIZAR);
    }


    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        caminhoDaActivityListarDentroDaActivityCadastrarParaAtualizarComRetorno();
    }

        private void caminhoDaActivityListarDentroDaActivityCadastrarParaAtualizarComRetorno() {
            capturarIntentDeListar = getIntent();
            Bundle bundle = capturarIntentDeListar.getExtras();

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
                    enderecoSexoUsuario.check(usuarioEntity.getSexo().equalsIgnoreCase(MASCULINO) ?
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
            enderecoSexoUsuario.setOnCheckedChangeListener((group, checkedId) -> {
                switch (checkedId) {
                    case radioButtonMasculino:
                        sexoUsuario = MASCULINO;
                        break;
                    case radioButtonFeminino:
                        sexoUsuario = FEMININO;
                        break;
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
                enderecoBotaoSalvarUsuario.setOnClickListener(view -> {

                    validarFormulario();

                    if(camposValidados) {
                        pegarEscolaridadeDoUsuarioNoSpinner();
                        if(tipoDeCaminho == SALVAR) {
                            criarUsuario();
                            devolucaoDeResultadoParaStartActivityForResult(SALVAR);
                        } else if(tipoDeCaminho == ATUALIZAR) {
                            alterarUsuario();
                            devolucaoDeResultadoParaStartActivityForResult(ATUALIZAR);
                        } else {
                            criarUsuario();
                            usuarioRepository.salvarUsuario(usuarioEntity);

                            publicarMensagemNaTela(usuarioEntity.getNome()
                                    .concat(" ")
                                    .concat(usuarioEntity.getSobrenome())
                                    .concat(" ")
                                    .concat(getString(R.string.salvo)));

//                            caminhoBifurcaEntreSalvarOuEditar();
                            limparCamposDoFormularioDeCadastrar();
                            finish();
                        }
                    } else {
                        publicarMensagemNaTela(getString(R.string.formulario_incompleto));
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

                private void devolucaoDeResultadoParaStartActivityForResult(int salvarOuAtualizar) {
                    Intent intent = getIntent();
                    intent.putExtra(MODO, salvarOuAtualizar);
                    intent.putExtra(CadastrarUsuarioActivity.USUARIO, usuarioEntity);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }

                private void caminhoBifurcaEntreSalvarOuEditar() {
                    if(usuarioEntity != null && usuarioEntity.getIdUsuario() > 0) {
                        alterarUsuario();
                        usuarioRepository.atualizarUsuario(usuarioEntity);

                        publicarMensagemNaTela(usuarioEntity.getNome()
                                .concat(" ")
                                .concat(usuarioEntity.getSobrenome())
                                .concat(" ")
                                .concat(getString(R.string.atualizado)));
                    } else {
                        criarUsuario();
                        usuarioRepository.salvarUsuario(usuarioEntity);

                        publicarMensagemNaTela(usuarioEntity.getNome()
                                .concat(" ")
                                .concat(usuarioEntity.getSobrenome())
                                .concat(" ")
                                .concat(getString(R.string.salvo)));
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

        private void ativarBotaoDeLimparFormularioDeCadastrar() {
            enderecoBotaoLimparCamposCadastroUsuario.setOnClickListener(view -> {
                limparCamposDoFormularioDeCadastrar();
                direcionarFocoDoUsuarioParaCampoNome();
            });
        }

            private void limparCamposDoFormularioDeCadastrar() {
                enderecoNomeUsuario.setText(null);
                enderecoSobrenomeUsuario.setText(null);
                enderecoCpfUsuario.setText(null);
                enderecoFoneUsuario.setText(null);
                enderecoEmailUsuario.setText(null);
                enderecoSexoUsuario.clearCheck();
                enderecoEscolaridadeUsuario.setSelection(0);
                enderecoAutorizoPublicidade.setChecked(false);
                usuarioEntity = null;

                publicarMensagemNaTela(getString(R.string.formulario_limpo));
            }

            private void direcionarFocoDoUsuarioParaCampoNome() {
                enderecoNomeUsuario.requestFocus();
            }

        private void ativarButtonVoltarCadastrar() {
            enderecoBotaoVoltar.setOnClickListener(v -> onBackPressed());
        }

        @Override
        public void onBackPressed() {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }

        private void publicarMensagemNaTela(String mensagem) {
            Toast.makeText(getApplicationContext(),
                    mensagem,
                    Toast.LENGTH_SHORT)
                    .show();
        }
}