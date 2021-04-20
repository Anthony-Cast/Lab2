package com.ipt.dashboard.entity;

import javax.persistence.*;

@Entity
@Table(name = "proyectos")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idproyecto;
    private String nombreproyecto;
    @Column(nullable = false)
    private String usuario_owner;

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto;
    }

    public String getUsuario_owner() {
        return usuario_owner;
    }

    public void setUsuario_owner(String usuario_owner) {
        this.usuario_owner = usuario_owner;
    }
}
