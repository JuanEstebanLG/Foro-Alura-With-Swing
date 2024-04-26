package com.alura.foro.foro.me.domain.user;

import lombok.NonNull;

public record DatosAutenticacionUsuario(

        @NonNull
        String nombre,

        @NonNull
        String password) {
}
