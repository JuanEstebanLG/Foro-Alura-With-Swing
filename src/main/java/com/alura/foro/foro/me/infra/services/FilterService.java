package com.alura.foro.foro.me.infra.services;

import com.alura.foro.foro.me.domain.user.UserRepository;
import com.alura.foro.foro.me.infra.exceptions.TokenNullException;
import com.alura.foro.foro.me.infra.exceptions.TokenSubjectNullException;
import com.alura.foro.foro.me.infra.exceptions.TokenVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterService extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtToken;

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");

        if (authHeader != null) {

            var token = authHeader.replace("Bearer ", "");
            System.out.println("Este token es el de auth " + token);
            try{

            String subJect = jwtToken.getSubject(token);

            if (subJect != null) {
                var usuario = usuarioRepository.findByNombre(subJect);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            }catch (TokenNullException | TokenSubjectNullException | TokenVerificationException e){
                e.printStackTrace();
            }
        }

            filterChain.doFilter(request, response);
    }

}
