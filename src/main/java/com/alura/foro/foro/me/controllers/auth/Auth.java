package com.alura.foro.foro.me.controllers.auth;

import com.alura.foro.foro.me.domain.user.DatosAutenticacionUsuario;
import com.alura.foro.foro.me.domain.user.Usuario;
import com.alura.foro.foro.me.infra.services.DatosJwt;
import com.alura.foro.foro.me.infra.services.JwtService;
import jakarta.annotation.security.PermitAll;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    @PermitAll
    @Transactional
    public ResponseEntity<DatosJwt> autenticarUsuario(@RequestBody @Validated DatosAutenticacionUsuario datosAutenticacionUsuario) throws InternalAuthenticationServiceException {
        System.out.println("Solicitud de login recibida");
        try {

            Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.nombre(),
                    datosAutenticacionUsuario.password());

            Authentication usuarioAutenticado = authenticationManager.authenticate(authToken);
            System.out.println( usuarioAutenticado.getPrincipal());
            String jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
            return ResponseEntity.ok(new DatosJwt(jwtToken));

        }catch (InternalAuthenticationServiceException err){

            err.printStackTrace();
            System.out.println("Exception message: " + err.getMessage());
            System.out.println("Exception cause: " + err.getCause());
            throw new InternalAuthenticationServiceException(err.getMessage());
        }
    }



}
