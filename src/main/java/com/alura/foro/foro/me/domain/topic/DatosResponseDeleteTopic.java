package com.alura.foro.foro.me.domain.topic;

import lombok.NonNull;

public record DatosResponseDeleteTopic(

        @NonNull
        Long topicId,

        @NonNull
        String reasonMessage
) {
}
