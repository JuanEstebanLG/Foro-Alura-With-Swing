package com.alura.foro.foro.me.infra.services;
import com.alura.foro.foro.me.domain.user.Usuario;
import com.alura.foro.foro.me.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario =  usuarioRepository.findByNombre(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        return usuario;
    }

}
