package com.alura.foro.foro.me.domain.topic;

import lombok.NonNull;

import java.util.Date;

public record DatosEdicionTopic(


        @NonNull
        String title,

        @NonNull
        String message,

        @NonNull
        Date fecha_de_creacion

) {
}
