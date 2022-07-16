package br.com.devvader.easycloset.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.EGrauEscolaridade;
import br.com.devvader.easycloset.domain.UsuarioEntity;
import br.com.devvader.easycloset.recursos.IUsuarioRepository;
import br.com.devvader.easycloset.recursos.UsuarioRepository;

public final class CadastrarUsuarioActivity extends AppCompatActivity {

    private static final String CADASTRAR_USUARIO = "Cadastrar Usuário";
    private static final List<String> LISTA_ENUM_ESCOLARIDADE = new ArrayList<>();

    private IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private UsuarioEntity usuario;

    private RadioGroup enderecoSexoUsuario;
    private Spinner enderecoEscolaridadeUsuario;

    private ArrayList<String> listaSpinnerDeGrausDeEscolaridade = new ArrayList<>();

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        // captarIntentDeRetornoDaTelaDeListarUsuariosParaEditarUsuario();
    }

        // -------------------- Atualizar ou editar Usuário (contém bugs) --------------------
        private void captarIntentDeRetornoDaTelaDeListarUsuariosParaEditarUsuario() {
            Intent receberDadosVindosDaPonteComTelaListarUsuario = getIntent();
            UsuarioEntity usuario = (UsuarioEntity) receberDadosVindosDaPonteComTelaListarUsuario
                    .getSerializableExtra("usuario");

            if(usuario != null) {
                capturarEnderecosDosCampos();
                capturarEnderecosDoRadioGroupSexoAndSpinnerEscolaridade();

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

        capturarEnderecosDosCampos();
        capturarEnderecosDoRadioGroupSexoAndSpinnerEscolaridade();
        capturarEnderecosDosBotoes();

        popularSpinner();

        ativarRadioGroupDeSexoDoUsuario();
        ativarSpinnerDeEscolaridadeDoUsuario();
        ativarBotaoDeSalvarCadastrarUsuario();
        ativarBotaoDeLimparFormularioDeCadastrarUsuario();
    }

        private void colocarTituloNaTela() {
            setTitle(CADASTRAR_USUARIO);
        }

        private void capturarEnderecosDoRadioGroupSexoAndSpinnerEscolaridade() {
            enderecoSexoUsuario = findViewById(R.id.radioGroup_sexo);
            enderecoEscolaridadeUsuario = findViewById(R.id.spinner_escolaridadeUsuario);
        }

        private void ativarRadioGroupDeSexoDoUsuario() {
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

        private void popularSpinner() {
            listaSpinnerDeGrausDeEscolaridade.add(EGrauEscolaridade.ESCOLHER_ESCOLARIDADE.getValor()); // position 0
            listaSpinnerDeGrausDeEscolaridade.add(EGrauEscolaridade.DOUTORADO.getValor()); // position 1
            listaSpinnerDeGrausDeEscolaridade.add(EGrauEscolaridade.MESTRADO.getValor()); // position 2
            listaSpinnerDeGrausDeEscolaridade.add(EGrauEscolaridade.ESPECIALIZACAO_OU_MBA.getValor()); // position 3
            listaSpinnerDeGrausDeEscolaridade.add(EGrauEscolaridade.SUPERIOR.getValor()); // position 4
            listaSpinnerDeGrausDeEscolaridade.add(EGrauEscolaridade.ENSINO_TECNICO.getValor()); // position 5
            listaSpinnerDeGrausDeEscolaridade.add(EGrauEscolaridade.ENSINO_MEDIO.getValor()); // position 6
            listaSpinnerDeGrausDeEscolaridade.add(EGrauEscolaridade.ENSINO_FUNDAMENTAL.getValor()); // position 7
            listaSpinnerDeGrausDeEscolaridade.add(EGrauEscolaridade.ENSINO_FUNDAMENTAL_INCOMPLETO.getValor()); // position 8
        }

        private void ativarSpinnerDeEscolaridadeDoUsuario() {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item,
                    listaSpinnerDeGrausDeEscolaridade);

            enderecoEscolaridadeUsuario.setAdapter(adapter);

            enderecoEscolaridadeUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    escolaridadeUsuario = (String) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });
        }

            private void pegarEscolaridadeDoUsuarioNoSpinner(View view) {
                escolaridadeUsuario = (String) enderecoEscolaridadeUsuario.getSelectedItem();
            }

        private void capturarEnderecosDosBotoes() {
            botaoSalvarUsuario = findViewById(R.id.button_salvarCadastroUsuario);
            botaoLimparCamposCadastroUsuario = findViewById(R.id.button_limparCadastroUsuario);
        }

        private void ativarBotaoDeSalvarCadastrarUsuario() {
            botaoSalvarUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pegarEscolaridadeDoUsuarioNoSpinner(view);
                    criarUsuario();
                    salvarOuEditarUsuario();
                    imprimirNomeDoUsuarioNaTela();
                    imprimirUsuarioCompletoNoTerminal();
                    limparCamposDoFormularioDeCadastrarUsuario();
                    finish();
                }
            });
        }

        private void ativarBotaoDeLimparFormularioDeCadastrarUsuario() {
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
                        escolaridadeUsuario,
                        enderecoAutorizoPublicidade.isChecked());
            }

            private void salvarOuEditarUsuario() {
                usuarioRepository.salvarOuEditar(usuario);
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
                // enderecoEscolaridadeUsuario.

                enderecoAutorizoPublicidade.setChecked(false);
            }

            private void direcionarFocoDoUsuarioParaCampoNome() {
                enderecoNomeUsuario.requestFocus();
            }
}