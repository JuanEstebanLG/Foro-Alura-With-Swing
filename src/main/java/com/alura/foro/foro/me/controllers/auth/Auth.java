package com.alura.foro.foro.me.controllers.auth;

import com.alura.foro.foro.me.domain.user.DatosAutenticacionUsuario;
import com.alura.foro.foro.me.domain.user.User;
import com.alura.foro.foro.me.infra.services.DatosJwt;
import com.alura.foro.foro.me.infra.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logins")
public class Auth{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService tokenService;


    @PostMapping
    public ResponseEntity<DatosJwt> autenticarUsuario(@RequestBody @Validated DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.nombre(),
                datosAutenticacionUsuario.password());

        var usuarioAutenticado =  authenticationManager.authenticate(authToken);

        var jwtToken = tokenService.generarToken((User) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJwt(jwtToken));

    }




}
