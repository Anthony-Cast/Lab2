package com.ipt.dashboard.repository;

import com.ipt.dashboard.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

    @Query(value="select * from usuarios where idarea like %?1%",nativeQuery = true)
    List<Usuario> listarUsuariosPorArea(int idarea);
}

