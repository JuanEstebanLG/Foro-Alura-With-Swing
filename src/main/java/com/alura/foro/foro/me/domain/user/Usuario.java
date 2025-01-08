package com.alura.foro.foro.me.domain.user;

import com.alura.foro.foro.me.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Embeddable
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String telefono;
    private String nombre;
    private String contraseña;

    @OneToMany(mappedBy = "usuario")
    public transient List<Topic> topics;


    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {

        this.email = datosRegistroUsuario.email();
        this.telefono = datosRegistroUsuario.telefono();
        this.nombre = datosRegistroUsuario.nombre();
        this.contraseña = datosRegistroUsuario.contraseña();
    }

    public void actualizarDatos(DatosActualizarUsuario datosActualizarUsuario){
        this.email = datosActualizarUsuario.email();
        this.telefono = datosActualizarUsuario.telefono();
        this.nombre = datosActualizarUsuario.nombre();
        this.contraseña = datosActualizarUsuario.password();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.contraseña;
    }

    @Override
    public String getUsername() {
        return this.nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
