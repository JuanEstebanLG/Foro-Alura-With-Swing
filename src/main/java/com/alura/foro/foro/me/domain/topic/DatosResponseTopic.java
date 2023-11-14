package com.alura.foro.foro.me.domain.topic;

import lombok.NonNull;

public record DatosResponseTopic(

        @NonNull
        Long id,

        @NonNull
        String title,

        @NonNull
        String message
) {
}
