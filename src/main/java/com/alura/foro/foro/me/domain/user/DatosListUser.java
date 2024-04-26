package com.alura.foro.foro.me.domain.user;

import lombok.NonNull;

public record DatosListUser(

        @NonNull
        String nombre,

        @NonNull
        String email,

        @NonNull
        String telefono


) {

        public DatosListUser(Usuario user){this(user.getNombre(), user.getEmail(), user.getTelefono());}
}
