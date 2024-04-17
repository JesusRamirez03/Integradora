package com.example.integradora.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginVM extends ViewModel {
    private MutableLiveData<LoginState> loginState = new MutableLiveData<>();

    public LiveData<LoginState> getLoginState() {
        return loginState;
    }

    public void login(String username, String password) {
        // Aquí va tu lógica de inicio de sesión
        // Por ejemplo, podrías hacer una llamada a una API de inicio de sesión
        // y actualizar el loginState en función de la respuesta

        // Por ahora, vamos a suponer que el inicio de sesión es exitoso si el nombre de usuario y la contraseña no están vacíos
        if (!username.isEmpty() && !password.isEmpty()) {
            loginState.setValue(LoginState.SUCCESS);
        } else {
            loginState.setValue(LoginState.FAILURE);
        }
    }

    public enum LoginState {
        SUCCESS,
        FAILURE
    }
}

