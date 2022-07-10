package br.com.devvader.easycloset.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.UsuarioEntity;
import br.com.devvader.easycloset.recursos.IUsuarioRepository;
import br.com.devvader.easycloset.recursos.UsuarioRepository;

public final class CadastrarUsuarioActivity extends AppCompatActivity {

    private static final String CADASTRAR_USUARIO = "Cadastrar Usuário";

    private IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private UsuarioEntity usuario;

    private RadioGroup enderecoSexoUsuario;
    private Button botaoSalvarUsuario;
    private Button botaoLimparCamposCadastroUsuario;

    private EditText enderecoNomeUsuario;
    private EditText enderecoSobrenomeUsuario;
    private EditText enderecoCpfUsuario;
    private EditText enderecoFoneUsuario;
    private EditText enderecoEmailUsuario;
    private CheckBox enderecoAutorizoPublicidade;

    private String sexoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        captarIntentDeRetornoDaTelaDeListarUsuariosParaEditarUsuario();
    }

        private void captarIntentDeRetornoDaTelaDeListarUsuariosParaEditarUsuario() {
            Intent receberDadosVindosDaPonteComTelaListarUsuario = getIntent();
            UsuarioEntity usuario = (UsuarioEntity) receberDadosVindosDaPonteComTelaListarUsuario
                    .getSerializableExtra("usuario");

            if(usuario != null) {
                capturarEnderecosDosCampos();
                enderecoSexoUsuario = findViewById(R.id.radioGroup_sexo);

                enderecoNomeUsuario.setText(usuario.getNome());
                enderecoSobrenomeUsuario.setText(usuario.getSobrenome());
                enderecoCpfUsuario.setText(usuario.getCpf());
                enderecoFoneUsuario.setText(usuario.getFone());
                enderecoEmailUsuario.setText(usuario.getEmail());
                enderecoSexoUsuario.check(
                        usuario.getSexo().equalsIgnoreCase("Masculino") ?
                                R.id.radioButton_sexoMasculino : R.id.radioButton_sexoFeminino);
                enderecoAutorizoPublicidade.setChecked(usuario.getAutorizo());
            }
        }

    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();
        capturarEnderecosDosBotoes();
        configurarRadioGroupDeSexoDoUsuario();
        configurarBotaoDeSalvarCadastrarUsuario();
        configurarBotaoDeLimparFormularioDeCadastrarUsuario();
    }

        private void colocarTituloNaTela() {
            setTitle(CADASTRAR_USUARIO);
        }

        private void capturarEnderecosDosBotoes() {
            botaoSalvarUsuario = findViewById(R.id.button_salvarCadastroUsuario);
            botaoLimparCamposCadastroUsuario = findViewById(R.id.button_limparCadastroUsuario);
        }

        private void configurarRadioGroupDeSexoDoUsuario() {
            enderecoSexoUsuario = findViewById(R.id.radioGroup_sexo);
            enderecoSexoUsuario.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButton_sexoMasculino:
                            sexoUsuario = "M";
                            break;
                        case R.id.radioButton_sexoFeminino:
                            sexoUsuario = "F";
                            break;
                    }
                }
            });
        }

        private void configurarBotaoDeSalvarCadastrarUsuario() {
            botaoSalvarUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    capturarEnderecosDosCampos();
                    criarUsuario();
                    salvarUsuario();
                    imprimirNomeDoUsuarioNaTela();
                    imprimirUsuarioCompletoNoTerminal();
                    limparCamposDoFormularioDeCadastrarUsuario();
                    finish();
                }
            });
        }

        private void configurarBotaoDeLimparFormularioDeCadastrarUsuario() {
            botaoLimparCamposCadastroUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    capturarEnderecosDosCampos();
                    limparCamposDoFormularioDeCadastrarUsuario();
                    direcionarFocoDoUsuarioParaCampoNome();
                }
            });
        }

            private void capturarEnderecosDosCampos() {
                enderecoNomeUsuario = findViewById(R.id.editText_nomeUsuario);
                enderecoSobrenomeUsuario = findViewById(R.id.editText_sobrenomeUsuario);
                enderecoCpfUsuario = findViewById(R.id.editText_cpfUsuario);
                enderecoFoneUsuario = findViewById(R.id.editText_foneUsuario);
                enderecoEmailUsuario = findViewById(R.id.editText_emailUsuario);
                enderecoAutorizoPublicidade = findViewById(R.id.checkBox_autorizoPublicidade);
            }

            private void criarUsuario() {
                usuario = new UsuarioEntity(
                        enderecoNomeUsuario.getText().toString(),
                        enderecoSobrenomeUsuario.getText().toString(),
                        enderecoCpfUsuario.getText().toString(),
                        enderecoFoneUsuario.getText().toString(),
                        enderecoEmailUsuario.getText().toString(),
                        sexoUsuario,
                        enderecoAutorizoPublicidade.isChecked());
            }

            private void salvarUsuario() {
                usuarioRepository.salvarUsuario(usuario);
            }

            private void imprimirNomeDoUsuarioNaTela() {
                Toast.makeText(CadastrarUsuarioActivity.this,
                        usuario.getNome().trim() + " " + usuario.getSobrenome().trim(),
                        Toast.LENGTH_LONG).show();
            }

            private void imprimirUsuarioCompletoNoTerminal() {
                System.out.println(usuario);
            }

            private void limparCamposDoFormularioDeCadastrarUsuario() {
                enderecoNomeUsuario.setText(null);
                enderecoSobrenomeUsuario.setText(null);
                enderecoCpfUsuario.setText(null);
                enderecoFoneUsuario.setText(null);
                enderecoEmailUsuario.setText(null);
                enderecoSexoUsuario.clearCheck();
                enderecoAutorizoPublicidade.setChecked(false);
            }

            private void direcionarFocoDoUsuarioParaCampoNome() {
                enderecoNomeUsuario.requestFocus();
            }
}