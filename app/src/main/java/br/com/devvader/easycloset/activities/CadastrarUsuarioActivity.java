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

import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.EGrauEscolaridade;
import br.com.devvader.easycloset.domain.UsuarioEntity;
import br.com.devvader.easycloset.recursos.IUsuarioRepository;
import br.com.devvader.easycloset.recursos.UsuarioRepository;

public final class CadastrarUsuarioActivity extends AppCompatActivity {

    private static final String CADASTRAR_USUARIO = "Cadastrar Usuário";
    private static final ArrayList<String> LISTA_SPINNER_ESCOLARIDADE = new ArrayList<>();

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        capturarIntentVindoDaTelaDeListarUsuariosParaEditarUsuario();
    }

        // -------------------- Atualizar ou editar Usuário (contém bugs) --------------------
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
                    popularSpinner();
                    ativarSpinnerDeEscolaridadeDoUsuario();

                    enderecoNomeUsuario.setText(usuario.getNome());
                    enderecoSobrenomeUsuario.setText(usuario.getSobrenome());
                    enderecoCpfUsuario.setText(usuario.getCpf());
                    enderecoFoneUsuario.setText(usuario.getFone());
                    enderecoEmailUsuario.setText(usuario.getEmail());
                    enderecoSexoUsuario.check(usuario.getSexo().equalsIgnoreCase("Masculino") ?
                            R.id.radioButton_sexoMasculino : R.id.radioButton_sexoFeminino);
                    enderecoEscolaridadeUsuario.setSelection(verificarPosicaoDaEscolaridadeNaListaDeGraus());
                    enderecoAutorizoPublicidade.setChecked(usuario.getAutorizo());
                }
            }

            private int verificarPosicaoDaEscolaridadeNaListaDeGraus() {
                int posicao = 0;
                for (String grau : LISTA_SPINNER_ESCOLARIDADE) {
                    if(grau.equalsIgnoreCase(usuario.getEscolaridade()))
                        System.out.println("aqui");
                        posicao = LISTA_SPINNER_ESCOLARIDADE.indexOf(grau);
                }
                return posicao;
            }

    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();

        mapearEnderecosDosCampos();
        mapearEnderecosDoRadioGroupSexoAndSpinnerEscolaridade();
        mapearEnderecosDosBotoes();

        popularSpinner();

        ativarRadioGroupDeSexoDoUsuario();
        ativarSpinnerDeEscolaridadeDoUsuario();
        ativarBotaoDeSalvarCadastrarUsuario();
        ativarBotaoDeLimparFormularioDeCadastrarUsuario();
    }

        private void colocarTituloNaTela() {
            setTitle(CADASTRAR_USUARIO);
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

        private void popularSpinner() {
            if(LISTA_SPINNER_ESCOLARIDADE.isEmpty()) {
                LISTA_SPINNER_ESCOLARIDADE.add(EGrauEscolaridade.ESCOLHER_ESCOLARIDADE.getValor()); // position 0
                LISTA_SPINNER_ESCOLARIDADE.add(EGrauEscolaridade.DOUTORADO.getValor()); // position 1
                LISTA_SPINNER_ESCOLARIDADE.add(EGrauEscolaridade.MESTRADO.getValor()); // position 2
                LISTA_SPINNER_ESCOLARIDADE.add(EGrauEscolaridade.ESPECIALIZACAO_OU_MBA.getValor()); // position 3
                LISTA_SPINNER_ESCOLARIDADE.add(EGrauEscolaridade.SUPERIOR.getValor()); // position 4
                LISTA_SPINNER_ESCOLARIDADE.add(EGrauEscolaridade.ENSINO_TECNICO.getValor()); // position 5
                LISTA_SPINNER_ESCOLARIDADE.add(EGrauEscolaridade.ENSINO_MEDIO.getValor()); // position 6
                LISTA_SPINNER_ESCOLARIDADE.add(EGrauEscolaridade.ENSINO_FUNDAMENTAL.getValor()); // position 7
                LISTA_SPINNER_ESCOLARIDADE.add(EGrauEscolaridade.ENSINO_FUNDAMENTAL_INCOMPLETO.getValor()); // position 8
            }
        }

        private void ativarSpinnerDeEscolaridadeDoUsuario() {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item,
                    LISTA_SPINNER_ESCOLARIDADE);
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

            private void pegarEscolaridadeDoUsuarioNoSpinner() {
                escolaridadeUsuario = (String) enderecoEscolaridadeUsuario.getSelectedItem();
            }

        private void mapearEnderecosDosBotoes() {
            botaoSalvarUsuario = findViewById(R.id.button_salvarCadastroUsuario);
            botaoLimparCamposCadastroUsuario = findViewById(R.id.button_limparCadastroUsuario);
        }

        private void ativarBotaoDeSalvarCadastrarUsuario() {
            botaoSalvarUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pegarEscolaridadeDoUsuarioNoSpinner();
                    salvarOuEditarUsuario();
                    imprimirNomeDoUsuarioNaTela();
                    imprimirUsuarioCompletoNoTerminal();
                    limparCamposDoFormularioDeCadastrarUsuario();
                    finish();
                }
            });
        }

            private void salvarOuEditarUsuario() {
                if(usuario != null && usuario.getId() > 0) {
                    editarUsuario();
                    usuarioRepository.editarUsuario(usuario);
                }
                salvarUsuario();
            }

                private void salvarUsuario() {
                    criarUsuario();
                    usuarioRepository.salvarUsuario(usuario);
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

                private void editarUsuario() {
                    preencherUsuario();
                    usuarioRepository.editarUsuario(usuario);
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

            private void mapearEnderecosDosCampos() {
                enderecoNomeUsuario = findViewById(R.id.editText_nomeUsuario);
                enderecoSobrenomeUsuario = findViewById(R.id.editText_sobrenomeUsuario);
                enderecoCpfUsuario = findViewById(R.id.editText_cpfUsuario);
                enderecoFoneUsuario = findViewById(R.id.editText_foneUsuario);
                enderecoEmailUsuario = findViewById(R.id.editText_emailUsuario);
                enderecoAutorizoPublicidade = findViewById(R.id.checkBox_autorizoPublicidade);
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