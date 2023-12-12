package com.alura.foro.foro.me.controllers;
import com.alura.foro.foro.me.domain.user.DatosRegistroUsuario;
import com.alura.foro.foro.me.domain.user.DatosRespuestUsuario;
import com.alura.foro.foro.me.domain.user.Usuario;
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

        Usuario usuario = userRepository.save(new Usuario(datosRegistroUsuario));

        DatosRespuestUsuario datosRespuestUsuario =
                new DatosRespuestUsuario(usuario.getId(),
                        usuario.getNombre(),
                        usuario.getEmail());

        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestUsuario);

    }



}
