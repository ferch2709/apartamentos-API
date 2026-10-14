package com.blue.apartamentos.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "propiedades")
public class PropiedadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propiedad")
    private Long idPropiedad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})   
    private ClienteModel propietario;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_propiedad", nullable = false)
    private TipoPropiedad tipo;
    
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;

    @Column(name = "ciudad", nullable = false, length = 50)
    private String ciudad;
    
    @Column(name = "estado", nullable = false, length = 50)
    private String entidadFederativa;

    @Column(name = "codigo_postal", nullable = false, length = 5)
    private int codigoPostal;

    @Column(name = "pais", nullable = false, length = 50)
    private String pais;
    
    @Column(name = "latitud", nullable = false)
    private double latitud;
    
    @Column(name = "longitud", nullable = false)
    private double longitud;
    
    @Column(name = "precio_noche", nullable = false)
    private double precioNoche;

    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    @Column(name = "num_habitaciones", nullable = false)
    private int numHabitaciones;

    @Column(name = "num_banos", nullable = false)
    private int numBanos;
    
    @Column(name = "metro_cuadrados", nullable = false)
    private int metroCuadrados;

    @Column(name = "comodidades", length = 300)
    private String comodidades;
    
    @Column(name = "reglas", length = 300)
    private String reglas;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_hab", nullable = false)
    private EstadoHab estado = EstadoHab.DISPONIBLE;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    public enum EstadoHab {
        DISPONIBLE,
        NO_DISPONIBLE,
        MANTENIMIENTO
    }

    public enum TipoPropiedad {
        CASA,
        PISO,
        HABITACION
    }

    @PrePersist
    public void onPrePersist() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
        this.fechaActualizacion = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoHab.DISPONIBLE;
        }
    }

    @PreUpdate
    public void onPreUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    // Constructores
    public PropiedadModel() {
    }

    public PropiedadModel(Long idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public PropiedadModel(ClienteModel propietario, TipoPropiedad tipo, String titulo, String descripcion, String direccion, String ciudad, String entidadFederativa, int codigoPostal, String pais, double latitud, double longitud, double precioNoche, int capacidad, int numHabitaciones, int numBanos, int metroCuadrados, String comodidades, String reglas, EstadoHab estado) {
        this.propietario = propietario;
        this.tipo = tipo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.entidadFederativa = entidadFederativa;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.latitud = latitud;
        this.longitud = longitud;
        this.precioNoche = precioNoche;
        this.capacidad = capacidad;
        this.numHabitaciones = numHabitaciones;
        this.numBanos = numBanos;
        this.metroCuadrados = metroCuadrados;
        this.comodidades = comodidades;
        this.reglas = reglas;
        this.estado = estado;
    }

    public PropiedadModel(Long idPropiedad, ClienteModel propietario, TipoPropiedad tipo, String titulo, String descripcion, String direccion, String ciudad, String entidadFederativa, int codigoPostal, String pais, double latitud, double longitud, double precioNoche, int capacidad, int numHabitaciones, int numBanos, int metroCuadrados, String comodidades, String reglas, EstadoHab estado, LocalDateTime fechaRegistro, LocalDateTime fechaActualizacion) {
        this.idPropiedad = idPropiedad;
        this.propietario = propietario;
        this.tipo = tipo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.entidadFederativa = entidadFederativa;
        this.codigoPostal = codigoPostal;
        this.pais = pais;
        this.latitud = latitud;
        this.longitud = longitud;
        this.precioNoche = precioNoche;
        this.capacidad = capacidad;
        this.numHabitaciones = numHabitaciones;
        this.numBanos = numBanos;
        this.metroCuadrados = metroCuadrados;
        this.comodidades = comodidades;
        this.reglas = reglas;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro != null ? fechaRegistro : LocalDateTime.now();
        this.fechaActualizacion = fechaActualizacion != null ? fechaActualizacion : LocalDateTime.now();
    }

    // Getters y Setters
    public Long getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(Long idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public ClienteModel getPropietario() {
        return propietario;
    }

    public void setPropietario(ClienteModel propietario) {
        this.propietario = propietario;
    }

    public TipoPropiedad getTipo() {
        return tipo;
    }

    public void setTipo(TipoPropiedad tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    public void setEntidadFederativa(String entidadFederativa) {
        this.entidadFederativa = entidadFederativa;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public int getNumBanos() {
        return numBanos;
    }

    public void setNumBanos(int numBanos) {
        this.numBanos = numBanos;
    }

    public int getMetroCuadrados() {
        return metroCuadrados;
    }

    public void setMetroCuadrados(int metroCuadrados) {
        this.metroCuadrados = metroCuadrados;
    }

    public String getComodidades() {
        return comodidades;
    }

    public void setComodidades(String comodidades) {
        this.comodidades = comodidades;
    }

    public String getReglas() {
        return reglas;
    }

    public void setReglas(String reglas) {
        this.reglas = reglas;
    }

    public EstadoHab getEstado() {
        return estado;
    }

    public void setEstado(EstadoHab estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}


