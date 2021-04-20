package com.ipt.dashboard.repository;

import com.ipt.dashboard.entity.Actividad;
import com.ipt.dashboard.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad,Integer> {

    @Query(value="select * from actividades where idproyecto like %?1%",nativeQuery = true)
    List<Actividad> listarActividadesPorProyecto(int idproyecto);
}
