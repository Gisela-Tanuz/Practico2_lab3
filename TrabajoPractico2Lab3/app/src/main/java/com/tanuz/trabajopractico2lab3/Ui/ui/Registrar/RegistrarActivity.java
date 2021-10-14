package com.tanuz.trabajopractico2lab3.Ui.ui.Registrar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tanuz.trabajopractico2lab3.R;
import com.tanuz.trabajopractico2lab3.Ui.model.Usuario;

public class RegistrarActivity extends AppCompatActivity {
    private EditText dni, nombre, apellido, email,pass;
    private Button guardar;
    private TextView mensaje;
    private RegistrarViewModel rv;
    private Usuario usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        Usuario usuarioActual = (Usuario) getIntent().getSerializableExtra("usuario");
        rv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistrarViewModel.class);
        inicializar();
       rv.getMensaje().observe(this, new Observer<String>() {
           @Override
           public void onChanged(String s) {
               mensaje.setText(s);
               mensaje.setVisibility(View.VISIBLE);
           }
       });
       rv.getUsuario().observe(this, new Observer<Usuario>() {
           @Override
           public void onChanged(Usuario usuario) {
               dni.setText(usuario.getDni()+"");
               nombre.setText(usuario.getNombre());
               apellido.setText(usuario.getApellido());
               email.setText(usuario.getMail());
               pass.setText(usuario.getPassword());

           }
       });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv.registrar(Long.parseLong(dni.getText().toString()), apellido.getText().toString(), nombre.getText().toString(), email.getText().toString(), pass.getText().toString(), usuarioActual);
                guardar.setVisibility(View.GONE);
            }
        });

        rv.mostrar(usuarioActual);
    }

    private void inicializar() {

        dni = findViewById(R.id.etDni);
        nombre = findViewById(R.id.etNombre);
        apellido = findViewById(R.id.etApellido);
        email = findViewById(R.id.etMail);
        pass = findViewById(R.id.etPassword);
        mensaje = findViewById(R.id.tvMensaje);
        guardar = findViewById(R.id.btGuardar);

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        System.exit(0);
    }

}

