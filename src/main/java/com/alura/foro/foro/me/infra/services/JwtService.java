package com.alura.foro.foro.me.infra.services;

import com.alura.foro.foro.me.domain.user.User;
import com.alura.foro.foro.me.infra.exceptions.TokenNullException;
import com.alura.foro.foro.me.infra.exceptions.TokenSubjectNullException;
import com.alura.foro.foro.me.infra.exceptions.TokenVerificationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.logging.Logger;


@Service
public class JwtService {


    public static final String secret = "1234567";

    public String generarToken(User usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Foro me")
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(fechaDeExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            Logger.getLogger(exception.toString());
            throw exception;
        }
    }

    public String getSubject(String token) throws TokenNullException, TokenSubjectNullException, TokenVerificationException {

        if(token == null){
            throw new TokenNullException("The token is null");
        }

        DecodedJWT verifier = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            verifier = JWT.require(algorithm)
                    .withIssuer("Foro me")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            Logger.getLogger(exception.toString());
        }

        if(verifier == null){
            throw  new TokenVerificationException("The verifier is null");
        }

        if(verifier.getSubject() == null){
            throw new TokenSubjectNullException("The subject is null");
        }

        return verifier.getSubject();
    }


    public Instant fechaDeExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
