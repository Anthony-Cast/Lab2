package com.ipt.dashboard.repository;

import com.ipt.dashboard.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
