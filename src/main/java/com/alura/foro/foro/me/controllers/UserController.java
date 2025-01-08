package com.alura.foro.foro.me.controllers;
import com.alura.foro.foro.me.domain.user.*;


import com.alura.foro.foro.me.infra.services.configuration.SecurityConfiguration;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @Autowired
    SecurityConfiguration securityConfiguration;


    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestUsuario> createUser(@RequestBody @Validated DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder){

        String password =
        securityConfiguration.passwordEncoder().encode(datosRegistroUsuario.contraseña());
        DatosRegistroUsuario datosfinal = new DatosRegistroUsuario(datosRegistroUsuario.email(), datosRegistroUsuario.telefono(), datosRegistroUsuario.nombre(), password );

        Usuario usuario = userRepository.save(new Usuario(datosfinal));



        DatosRespuestUsuario datosRespuestUsuario =
                new DatosRespuestUsuario(usuario.getId(),
                        usuario.getNombre(),
                        usuario.getEmail());

        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestUsuario);

    }

    @GetMapping
    @Transactional
    public ResponseEntity<Page<DatosListUser>> getUsers(@PageableDefault(sort = "id", size = 4) Pageable paginacion) {
        return ResponseEntity.ok(userRepository.findAll(paginacion).map(DatosListUser::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaEdicionUsuario> updateUser(@Validated @RequestBody DatosActualizarUsuario datosActualizarUsuario, UriComponentsBuilder uri){
        Usuario usuario = userRepository.findByEmail(datosActualizarUsuario.email());

        usuario.actualizarDatos(datosActualizarUsuario);


        return
        ResponseEntity.ok(new DatosRespuestaEdicionUsuario(usuario.getNombre()));

    }




}
