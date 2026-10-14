package com.blue.apartamentos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.models.PropiedadModel.EstadoHab;
import com.blue.apartamentos.models.PropiedadModel.TipoPropiedad;

@Repository
public interface IPropiedadRepository extends JpaRepository<PropiedadModel, Long> {

    // Filtrar por tipo (CASA, PISO, HABITACION)
    List<PropiedadModel> findByTipo(TipoPropiedad tipo);

    // Filtrar por estado (DISPONIBLE, NO_DISPONIBLE, MANTENIMIENTO)
    List<PropiedadModel> findByEstado(EstadoHab estado);

    // Listar todas las propiedades de un propietario en específico
    List<PropiedadModel> findByPropietario(ClienteModel propietario);

    // Buscar propiedades por ciudad
    List<PropiedadModel> findByCiudadIgnoreCase(String ciudad);

    // Buscar propiedades disponibles por ciudad y capacidad
    List<PropiedadModel> findByCiudadIgnoreCaseAndEstadoAndCapacidadGreaterThanEqual(
            String ciudad, EstadoHab estado, int capacidad);
}