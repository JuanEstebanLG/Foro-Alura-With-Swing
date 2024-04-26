package com.alura.foro.foro.me.controllers;


import com.alura.foro.foro.me.domain.topic.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    TopicRepository topicRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosResponseTopic> makeTopic(@RequestBody @Validated DatosCreacionTopic datosCreacionTopic, UriComponentsBuilder uri){

        Topic topic = topicRepository.save(new Topic(datosCreacionTopic));


        DatosResponseTopic datosResponseTopic = new DatosResponseTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage()
        );


        URI url = uri.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

        return ResponseEntity.created(url).body(datosResponseTopic);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosResponseTopic> updateTopic(@RequestBody @Validated DatosActualizarTopic datosActualizarTopic){

        Topic topic = topicRepository.getReferenceById(datosActualizarTopic.id());
        topic.actualizarDatos(datosActualizarTopic);



        return
        ResponseEntity.ok(new DatosResponseTopic(topic.getId(),
                topic.getTitle(), topic.getMessage()));
        
    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DatosListTopic>> getTopics(@PageableDefault(sort = "id", size = 4) Pageable paginacion){
        return  ResponseEntity.ok(topicRepository.findAll(paginacion).map(DatosListTopic::new));
    }




    @DeleteMapping
    @Transactional
    public ResponseEntity<DatosResponseDeleteTopic> deleteTopic(@RequestBody @Validated DatosDeleteTopic datosDeleteTopic){
        topicRepository.deleteById(datosDeleteTopic.id());

        return  ResponseEntity.ok(new DatosResponseDeleteTopic(datosDeleteTopic.id(), datosDeleteTopic.reason()));

    }



}

