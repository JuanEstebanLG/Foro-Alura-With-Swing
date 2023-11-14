package com.alura.foro.foro.me.domain.user;


import lombok.NonNull;

public record DatosRegistroUsuario(


        @NonNull
        String email,

        @NonNull
        String telefono,

        @NonNull
        String nombre,

        @NonNull
        String password


) {
}
