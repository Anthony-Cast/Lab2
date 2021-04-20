package com.ipt.dashboard.repository;

import com.ipt.dashboard.entity.Actividad;
import com.ipt.dashboard.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    @Query(value = "select sum(peso*estado)/sum(peso)*100 as Avance from actividades where idproyecto=?1",nativeQuery = true)
    Float obtenerAvance(int idproyecto);

}
