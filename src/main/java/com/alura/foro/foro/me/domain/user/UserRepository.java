package com.alura.foro.foro.me.domain.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>{
    Usuario findByNombre(String username);
    Usuario findByEmail(String email);
}
