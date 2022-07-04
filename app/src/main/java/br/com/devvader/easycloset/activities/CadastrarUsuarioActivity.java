package br.com.devvader.easycloset.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import br.com.devvader.easycloset.R;
import br.com.devvader.easycloset.domain.UsuarioEntity;
import br.com.devvader.easycloset.recursos.IUsuarioRepository;
import br.com.devvader.easycloset.recursos.UsuarioRepository;

public final class CadastrarUsuarioActivity extends AppCompatActivity {

    private IUsuarioRepository usuarioRepository = new UsuarioRepository();
    private UsuarioEntity usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        Button botaoSalvarUsuario = findViewById(R.id.button_salvarUsuario);
        botaoSalvarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criarUsuario();
                salvarUsuario();
                imprimirUsuario();
            }

                private void criarUsuario() {
                    String nomeUsuario = findViewById(R.id.editText_nomeUsuario).toString();
                    String sobrenomeUsuario = findViewById(R.id.editText_sobrenomeUsuario).toString();
                    String cpfUsuario = findViewById(R.id.editText_cpfUsuario).toString();
                    String foneUsuario = findViewById(R.id.editText_foneUsuario).toString();
                    String emailUsuario = findViewById(R.id.editText_emailUsuario).toString();

                    usuario = new UsuarioEntity(nomeUsuario, sobrenomeUsuario, cpfUsuario, foneUsuario, emailUsuario);
                }

                private void salvarUsuario() {
                    usuarioRepository.salvarUsuario(usuario);
                }

                private void imprimirUsuario() {
                    Toast.makeText(CadastrarUsuarioActivity.this,
                            usuario.getNomeUsuario() + " " + usuario.getSobrenomeUsuario(),
                            Toast.LENGTH_SHORT).show();
                }
        });


    }
}