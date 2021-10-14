package com.tanuz.trabajopractico2lab3.Ui.ui.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tanuz.trabajopractico2lab3.R;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail, etPass;
    private TextView mensaje;
    private Button btIngresar,btRegistrar;
    private MainActivityViewModel mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        inicializar();


        mv.getMensaje().observe(this, new androidx.lifecycle.Observer<String>() {
            @Override
            public void onChanged(String s) {
                mensaje.setText(s);
                mensaje.setVisibility(View.VISIBLE);
                etEmail.setText("");
                etPass.setText("");
            }
        });


    }

    private void inicializar() {
        etEmail= findViewById(R.id.etEmail);
        etPass= findViewById(R.id.etPass);
        mensaje= findViewById(R.id.tvMensajeError);
        btIngresar= findViewById(R.id.btIngresar);
        btRegistrar= findViewById(R.id.btRegistrar);
        btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                mv.autenticar(etEmail.getText().toString(),etPass.getText().toString());
                etEmail.setText("");
                etPass.setText("");
            }
        });
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.aRegistrar();
            }
        });

    }


    }
