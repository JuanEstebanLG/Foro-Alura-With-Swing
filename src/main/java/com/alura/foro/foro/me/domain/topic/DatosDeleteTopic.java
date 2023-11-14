package com.alura.foro.foro.me.domain.topic;

import lombok.NonNull;

public record DatosDeleteTopic(

        @NonNull
        Long id,


        @NonNull
        String reason

) {
}
