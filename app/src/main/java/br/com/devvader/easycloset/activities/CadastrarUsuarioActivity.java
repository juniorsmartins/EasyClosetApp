package br.com.devvader.easycloset.activities;

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

    private static final String CADASTRAR_USUARIO = "Cadastrar Usuário";

    private IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private UsuarioEntity usuario = null;

    private RadioGroup enderecoSexoUsuario;
    private Spinner enderecoEscolaridadeUsuario;

    private Button botaoSalvarUsuario;
    private Button botaoLimparCamposCadastroUsuario;

    private EditText enderecoNomeUsuario;
    private EditText enderecoSobrenomeUsuario;
    private EditText enderecoCpfUsuario;
    private EditText enderecoFoneUsuario;
    private EditText enderecoEmailUsuario;
    private CheckBox enderecoAutorizoPublicidade;

    private String sexoUsuario;
    private String escolaridadeUsuario;

    private boolean camposValidados = false;

    // ------------------------------ OnCreate ------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        capturarIntentVindoDaTelaDeListarUsuariosParaEditarUsuario();
    }

        private void capturarIntentVindoDaTelaDeListarUsuariosParaEditarUsuario() {
            Intent receberDadosVindosDaPonteComTelaListarUsuario = getIntent();

            usuario = (UsuarioEntity) receberDadosVindosDaPonteComTelaListarUsuario
                        .getSerializableExtra("usuario");

            carregarDadosDoUsuarioParaEdicaoNoFormulario();
        }

            private void carregarDadosDoUsuarioParaEdicaoNoFormulario() {
                if(usuario != null) {
                    mapearEnderecosDosCampos();
                    mapearEnderecosDoRadioGroupSexoAndSpinnerEscolaridade();

                    enderecoNomeUsuario.setText(usuario.getNome());
                    enderecoSobrenomeUsuario.setText(usuario.getSobrenome());
                    enderecoCpfUsuario.setText(usuario.getCpf());
                    enderecoFoneUsuario.setText(usuario.getFone());
                    enderecoEmailUsuario.setText(usuario.getEmail());
                    enderecoSexoUsuario.check(usuario.getSexo().equalsIgnoreCase("Masculino") ?
                            R.id.radioButton_sexoMasculino : R.id.radioButton_sexoFeminino);
                    enderecoEscolaridadeUsuario.setSelection(0);
                    enderecoAutorizoPublicidade.setChecked(usuario.getAutorizo());
                }
            }


    // ------------------------------ OnResume ------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();

        mapearEnderecosDosCampos();
        mapearEnderecosDoRadioGroupSexoAndSpinnerEscolaridade();
        mapearEnderecosDosBotoes();

        ativarRadioGroupDeSexoDoUsuario();
        ativarSpinnerDeEscolaridadeDoUsuario();
        ativarBotaoDeSalvarCadastrarUsuario();
        ativarBotaoDeLimparFormularioDeCadastrarUsuario();
    }

        private void colocarTituloNaTela() {
            setTitle(CADASTRAR_USUARIO);
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

        private void mapearEnderecosDosBotoes() {
            botaoSalvarUsuario = findViewById(R.id.button_salvarCadastroUsuario);
            botaoLimparCamposCadastroUsuario = findViewById(R.id.button_limparCadastroUsuario);
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

        private void ativarBotaoDeSalvarCadastrarUsuario() {
            botaoSalvarUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    validarFormulario();

                    if(camposValidados) {
                        pegarEscolaridadeDoUsuarioNoSpinner();
                        salvarOuEditarUsuario();
                        imprimirNomeDoUsuarioNaTela();
                        imprimirUsuarioCompletoNoTerminal();
                        limparCamposDoFormularioDeCadastrarUsuario();
                        finish();
                    } else {
                        Toast.makeText(CadastrarUsuarioActivity.this, R.string.formulario_incompleto, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

            private void validarFormulario() {
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

            private void salvarOuEditarUsuario() {


                if(usuario != null && usuario.getIdUsuario() > 0) {
                    preencherUsuario();
                    usuarioRepository.editarUsuario(usuario);
                } else {
                    criarUsuario();
                    usuarioRepository.salvarUsuario(usuario);
                }
            }

                private void preencherUsuario() {
                    mapearEnderecosDosCampos();
                    mapearEnderecosDoRadioGroupSexoAndSpinnerEscolaridade();
                    pegarEscolaridadeDoUsuarioNoSpinner();

                    usuario.setNome(enderecoNomeUsuario.getText().toString());
                    usuario.setSobrenome(enderecoSobrenomeUsuario.getText().toString());
                    usuario.setCpf(enderecoCpfUsuario.getText().toString());
                    usuario.setFone(enderecoFoneUsuario.getText().toString());
                    usuario.setEmail(enderecoEmailUsuario.getText().toString());
                    usuario.setSexo(sexoUsuario);
                    usuario.setEscolaridade(escolaridadeUsuario);
                    usuario.setAutorizo(enderecoAutorizoPublicidade.isChecked());
                }

                private void criarUsuario() {
                    usuario = new UsuarioEntity(
                            enderecoNomeUsuario.getText().toString(),
                            enderecoSobrenomeUsuario.getText().toString(),
                            enderecoCpfUsuario.getText().toString(),
                            enderecoFoneUsuario.getText().toString(),
                            enderecoEmailUsuario.getText().toString(),
                            sexoUsuario,
                            escolaridadeUsuario,
                            enderecoAutorizoPublicidade.isChecked());
                }

            private void imprimirNomeDoUsuarioNaTela() {
                Toast.makeText(CadastrarUsuarioActivity.this,
                        usuario.getNome().trim() + " " + usuario.getSobrenome().trim(),
                        Toast.LENGTH_LONG).show();
            }

            private void imprimirUsuarioCompletoNoTerminal() {
                System.out.println(usuario);
            }

        private void ativarBotaoDeLimparFormularioDeCadastrarUsuario() {
            botaoLimparCamposCadastroUsuario.setOnClickListener(new View.OnClickListener() {
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
                usuario = null;

                Toast.makeText(CadastrarUsuarioActivity.this,
                        "Formulário Limpo!",
                        Toast.LENGTH_LONG)
                        .show();
            }

            private void direcionarFocoDoUsuarioParaCampoNome() {
                enderecoNomeUsuario.requestFocus();
            }
}