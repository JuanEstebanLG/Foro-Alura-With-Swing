package com.alura.foro.foro.me.domain.user;

import lombok.NonNull;

public record DatosActualizarUsuario(



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
