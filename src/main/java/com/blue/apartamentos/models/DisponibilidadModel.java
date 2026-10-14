package com.blue.apartamentos.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disponibilidades")
public class DisponibilidadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidad")
    private Long idDisponibilidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PropiedadModel propiedad;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;
    
    @Column(name = "disponible", nullable = false)
    private Boolean disponible = true;
    
    @Column(name = "precio_especial")
    private Double precioEspecial;

    // Constructores
    public DisponibilidadModel() {
    }

    public DisponibilidadModel(PropiedadModel propiedad, LocalDate fechaInicio, LocalDate fechaFin, Boolean disponible, Double precioEspecial) {
        this.propiedad = propiedad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.disponible = disponible != null ? disponible : true;
        this.precioEspecial = precioEspecial;
    }

    public DisponibilidadModel(Long idDisponibilidad, PropiedadModel propiedad, LocalDate fechaInicio, LocalDate fechaFin, Boolean disponible, Double precioEspecial) {
        this.idDisponibilidad = idDisponibilidad;
        this.propiedad = propiedad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.disponible = disponible != null ? disponible : true;
        this.precioEspecial = precioEspecial;
    }

    // Getters y Setters
    public Long getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public void setIdDisponibilidad(Long idDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
    }

    public PropiedadModel getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(PropiedadModel propiedad) {
        this.propiedad = propiedad;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Double getPrecioEspecial() {
        return precioEspecial;
    }

    public void setPrecioEspecial(Double precioEspecial) {
        this.precioEspecial = precioEspecial;
    }
}