package com.alura.foro.foro.me.domain.topic;

import java.time.LocalDateTime;

public record DatosListTopic(

        String title,

        String message,

        LocalDateTime fecha_de_creacion

) {
    public DatosListTopic(Topic topic){
        this(topic.getTitle(), topic.getMessage(), topic.getFechaDeCreacion());
    }



}
