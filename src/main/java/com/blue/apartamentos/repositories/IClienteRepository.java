package com.blue.apartamentos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.models.ClienteModel.ClienteStatus;
import com.blue.apartamentos.models.ClienteModel.TipoCliente;

@Repository
public interface IClienteRepository extends JpaRepository<ClienteModel, Long> {

    // Buscar cliente por email
    Optional<ClienteModel> findByEmail(String email);

    // Verificar si ya existe un correo registrado
    boolean existsByEmail(String email);

    // Filtrar por tipo 
    List<ClienteModel> findByTipo(TipoCliente tipo);

    // Filtrar por estatus 
    List<ClienteModel> findByEstatus(ClienteStatus estatus);
}