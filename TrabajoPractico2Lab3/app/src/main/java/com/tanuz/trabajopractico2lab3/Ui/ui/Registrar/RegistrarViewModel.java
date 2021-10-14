package com.tanuz.trabajopractico2lab3.Ui.ui.Registrar;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tanuz.trabajopractico2lab3.Ui.model.Usuario;
import com.tanuz.trabajopractico2lab3.Ui.request.ApiClient;

public class RegistrarViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient apiClient;
    private MutableLiveData<Usuario> usuario;
    private MutableLiveData<String> mensaje;
    public RegistrarViewModel(@NonNull Application application) {
        super(application);
        this.context= application.getApplicationContext();
        this.apiClient= new ApiClient();
    }
    public LiveData<Usuario> getUsuario(){
        if(usuario==null){
            usuario=new MutableLiveData<>();
        }
        return usuario;
    }
    public LiveData<String> getMensaje(){
        if(mensaje==null){
            mensaje=new MutableLiveData<>();
        }
        return mensaje;
    }
    public void registrar(Long dni, String apellido, String nombre, String email, String pass,Usuario usuarioActual){
        Usuario u =new Usuario(dni,apellido,nombre,email,pass);
        apiClient.guardar(context,u);
        //mensaje.setValue("El usuarios se genero con exito");
        mensaje.setValue( usuarioActual == null? "El usuario se genero con exito" : "El usuario se actualizo con exito" );

    }

    public void mostrar(Usuario u){
        if(u != null){
            u = apiClient.leer();
            usuario.setValue(u);
        }
    }



}
