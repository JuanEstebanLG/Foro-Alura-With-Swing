package com.alura.foro.foro.me.domain.user;

import lombok.NonNull;

public record DatosRespuestUsuario(

        @NonNull
        Long id,

        @NonNull
        String nombre,

        @NonNull
        String email
) {
}
