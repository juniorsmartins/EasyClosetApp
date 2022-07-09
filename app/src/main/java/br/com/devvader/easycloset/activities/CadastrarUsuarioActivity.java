package br.com.devvader.easycloset.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.UsuarioEntity;
import br.com.devvader.easycloset.recursos.IUsuarioRepository;
import br.com.devvader.easycloset.recursos.UsuarioRepository;

public final class CadastrarUsuarioActivity extends AppCompatActivity {

    private static final String CADASTRAR_USUARIO = "Cadastrar Usuário";

    private IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private UsuarioEntity usuario;

    private Button botaoSalvarUsuario;
    private Button botaoLimparCamposCadastroUsuario;

    private EditText enderecoNomeUsuario;
    private EditText enderecoSobrenomeUsuario;
    private EditText enderecoCpfUsuario;
    private EditText enderecoFoneUsuario;
    private EditText enderecoEmailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
    }

    @Override
    protected void onResume() {
        super.onResume();
        colocarTituloNaTela();
        capturarEnderecosDosBotoes();
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

        private void configurarBotaoDeSalvarCadastrarUsuario() {
            botaoSalvarUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    capturarEnderecosDosCampos();
                    validarCamposObrigatorios();
                    criarUsuario();
                    salvarUsuario();
                    imprimirUsuario();
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
        }

        private void validarCamposObrigatorios() {
            if(enderecoNomeUsuario.getText().toString() == null ||
                    enderecoNomeUsuario.getText().toString().trim().isEmpty()) {

                Toast.makeText(CadastrarUsuarioActivity.this,
                        getString(R.string.nomeObrigatorioCadastroUsuario),
                        Toast.LENGTH_LONG).show();

                enderecoNomeUsuario.requestFocus();
                return;
            }

            if(enderecoSobrenomeUsuario.getText().toString() == null ||
                    enderecoSobrenomeUsuario.getText().toString().trim().isEmpty()) {

                Toast.makeText(CadastrarUsuarioActivity.this,
                        getString(R.string.sobrenomeObrigatorioCadastroUsuario),
                        Toast.LENGTH_LONG).show();

                enderecoSobrenomeUsuario.requestFocus();
                return;
            }
        }

        private void criarUsuario() {
            String nomeUsuario = enderecoNomeUsuario.getText().toString();
            String sobrenomeUsuario = enderecoSobrenomeUsuario.getText().toString();
            String cpfUsuario = enderecoCpfUsuario.getText().toString();
            String foneUsuario = enderecoFoneUsuario.getText().toString();
            String emailUsuario = enderecoEmailUsuario.getText().toString();
            usuario = new UsuarioEntity(
                    nomeUsuario, sobrenomeUsuario, cpfUsuario, foneUsuario, emailUsuario);
        }

        private void salvarUsuario() {
            usuarioRepository.salvarUsuario(usuario);
        }

        private void imprimirUsuario() {
            Toast.makeText(CadastrarUsuarioActivity.this,
                    usuario.getNomeUsuario().trim() + " " + usuario.getSobrenomeUsuario().trim(),
                    Toast.LENGTH_LONG).show();
        }

        private void limparCamposDoFormularioDeCadastrarUsuario() {
            enderecoNomeUsuario.setText(null);
            enderecoSobrenomeUsuario.setText(null);
            enderecoCpfUsuario.setText(null);
            enderecoFoneUsuario.setText(null);
            enderecoEmailUsuario.setText(null);
        }

        private void direcionarFocoDoUsuarioParaCampoNome() {
            enderecoNomeUsuario.requestFocus();
        }
}