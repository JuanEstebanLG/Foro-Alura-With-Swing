package com.alura.foro.foro.me.domain.topic;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;

import java.time.LocalDateTime;
public record DatosCreacionTopic(

        @NonNull
        String title,

        @NonNull
        String message,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime fecha_de_creacion


        ) {
}
