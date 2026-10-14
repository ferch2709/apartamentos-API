package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.models.PropiedadModel.EstadoHab;
import com.blue.apartamentos.models.PropiedadModel.TipoPropiedad;
import com.blue.apartamentos.repositories.IPropiedadRepository;

@Service
public class PropiedadService {

    @Autowired
    private IPropiedadRepository propiedadRepository;

    // Obtener todas las propiedades
    public List<PropiedadModel> getAllPropiedades() {
        return propiedadRepository.findAll();
    }

    // Buscar propiedad por ID
    public Optional<PropiedadModel> getPropiedadById(Long id) {
        return propiedadRepository.findById(id);
    }

    // Guardar una nueva propiedad
    public PropiedadModel savePropiedad(PropiedadModel propiedad) {
        return propiedadRepository.save(propiedad);
    } 
    
    // Eliminar propiedad por ID
    public boolean deletePropiedad(Long id) {
        if (propiedadRepository.existsById(id)) {
            propiedadRepository.deleteById(id);
            return true;
        }
        return false;
    }
    // Recuperar propiedades por tipo (CASA, PISO, HABITACION)
    public List<PropiedadModel> getPropiedadesByTipo(TipoPropiedad tipo) {
        return propiedadRepository.findByTipo(tipo);
    }

    // Recuperar propiedades por estado de disponibilidad (DISPONIBLE, NO_DISPONIBLE, MANTENIMIENTO)
    public List<PropiedadModel> getPropiedadesByEstado(EstadoHab estado) {
        return propiedadRepository.findByEstado(estado);
    }

    // Recuperar propiedades de un propietario
    public List<PropiedadModel> getPropiedadesByPropietario(ClienteModel propietario) {
        return propiedadRepository.findByPropietario(propietario);
    }

    // Actualizar una propiedad existente
    public PropiedadModel updatePropiedad(Long id, PropiedadModel propiedadDetalles) {
        return propiedadRepository.findById(id).map(propiedad -> {
            propiedad.setPropietario(propiedadDetalles.getPropietario());
            propiedad.setTipo(propiedadDetalles.getTipo());
            propiedad.setTitulo(propiedadDetalles.getTitulo());
            propiedad.setDescripcion(propiedadDetalles.getDescripcion());
            propiedad.setDireccion(propiedadDetalles.getDireccion());
            propiedad.setCiudad(propiedadDetalles.getCiudad());
            propiedad.setEntidadFederativa(propiedadDetalles.getEntidadFederativa());
            propiedad.setCodigoPostal(propiedadDetalles.getCodigoPostal());
            propiedad.setPais(propiedadDetalles.getPais());
            propiedad.setLatitud(propiedadDetalles.getLatitud());
            propiedad.setLongitud(propiedadDetalles.getLongitud());
            propiedad.setPrecioNoche(propiedadDetalles.getPrecioNoche());
            propiedad.setCapacidad(propiedadDetalles.getCapacidad());
            propiedad.setNumHabitaciones(propiedadDetalles.getNumHabitaciones());
            propiedad.setNumBanos(propiedadDetalles.getNumBanos());
            propiedad.setMetroCuadrados(propiedadDetalles.getMetroCuadrados());
            propiedad.setComodidades(propiedadDetalles.getComodidades());
            propiedad.setReglas(propiedadDetalles.getReglas());
            propiedad.setEstado(propiedadDetalles.getEstado());
            return propiedadRepository.save(propiedad);
        }).orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + id));
    }
}
