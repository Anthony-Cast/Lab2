package com.ipt.dashboard.entity;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    @Column(nullable = false)
    private String correo;

    @ManyToOne
    @JoinColumn(name="idarea")
    private Area area;

    private String nombres;
    private String apellidos;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
