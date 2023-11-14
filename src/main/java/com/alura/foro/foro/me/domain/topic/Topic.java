package com.alura.foro.foro.me.domain.topic;
import com.alura.foro.foro.me.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity(name="Topic")
@Table(name="topic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String message;
    private LocalDateTime fechaDeCreacion;


    @ManyToOne
    private User usuario;


    public Topic(DatosCreacionTopic datosCreacionTopic) {
        this.title = datosCreacionTopic.title();
        this.message = datosCreacionTopic.message();
        this.fechaDeCreacion = datosCreacionTopic.fecha_de_creacion();
    }

    public void actualizarDatos(DatosActualizarTopic datosActualizarTopic) {

        if(datosActualizarTopic.title() != null){

            this.title = datosActualizarTopic.title();
        }

        if (datosActualizarTopic.message() != null){

            this.message = datosActualizarTopic.message();
        }

        if(datosActualizarTopic.fecha_de_creacion() != null){

            this.fechaDeCreacion = datosActualizarTopic.fecha_de_creacion();
        }
    }
}

