package com.alura.foro.foro.me.controllers;
import com.alura.foro.foro.me.domain.user.DatosRegistroUsuario;
import com.alura.foro.foro.me.domain.user.DatosRespuestUsuario;
import com.alura.foro.foro.me.domain.user.User;
import com.alura.foro.foro.me.domain.user.UserRepository;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestUsuario> createUser(@RequestBody @Validated DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder){

        User user = userRepository.save(new User(datosRegistroUsuario));

        DatosRespuestUsuario datosRespuestUsuario =
                new DatosRespuestUsuario(user.getId(),
                        user.getNombre(),
                        user.getEmail());

        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestUsuario);

    }



}
