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

    private IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private UsuarioEntity usuario;

    EditText enderecoNomeUsuario;
    EditText enderecoSobrenomeUsuario;
    EditText enderecoCpfUsuario;
    EditText enderecoFoneUsuario;
    EditText enderecoEmailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Button botaoSalvarUsuario = findViewById(R.id.button_salvarCadastroUsuario);
        botaoSalvarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturarEnderecosDosCampos();
                validarCamposObrigatorios();
                criarUsuario();
                salvarUsuario();
                imprimirUsuario();
                limparCamposCadastroUsuario();
                finish();
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

            private void limparCamposCadastroUsuario() {
                enderecoNomeUsuario.setText(null);
                enderecoSobrenomeUsuario.setText(null);
                enderecoCpfUsuario.setText(null);
                enderecoFoneUsuario.setText(null);
                enderecoEmailUsuario.setText(null);
            }
        });

        Button botaoLimparCamposCadastroUsuario = findViewById(R.id.button_limparCadastroUsuario);
        botaoLimparCamposCadastroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturarEnderecosDosCampos();
                limparCamposCadastroUsuario();

                enderecoNomeUsuario.requestFocus();
            }

            private void capturarEnderecosDosCampos() {
                enderecoNomeUsuario = findViewById(R.id.editText_nomeUsuario);
                enderecoSobrenomeUsuario = findViewById(R.id.editText_sobrenomeUsuario);
                enderecoCpfUsuario = findViewById(R.id.editText_cpfUsuario);
                enderecoFoneUsuario = findViewById(R.id.editText_foneUsuario);
                enderecoEmailUsuario = findViewById(R.id.editText_emailUsuario);
            }

            private void limparCamposCadastroUsuario() {
                enderecoNomeUsuario.setText(null);
                enderecoSobrenomeUsuario.setText(null);
                enderecoCpfUsuario.setText(null);
                enderecoFoneUsuario.setText(null);
                enderecoEmailUsuario.setText(null);
            }
        });
    }
}