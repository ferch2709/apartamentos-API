package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.models.ClienteModel.ClienteStatus;
import com.blue.apartamentos.models.ClienteModel.TipoCliente;
import com.blue.apartamentos.repositories.IClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private IClienteRepository clienteRepository;

    // Obtener todos los clientes
    public List<ClienteModel> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Buscar por ID
    public Optional<ClienteModel> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    // Buscar por email
    public Optional<ClienteModel> getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    // Filtrar por tipo (PROPIETARIO, ARRENDATARIO, etc.)
    public List<ClienteModel> getClientesByTipo(TipoCliente tipo) {
        return clienteRepository.findByTipo(tipo);
    }

    // Filtrar por estatus (ACTIVO, INACTIVO, etc.)
    public List<ClienteModel> getClientesByEstatus(ClienteStatus estatus) {
        return clienteRepository.findByEstatus(estatus);
    }

    // Guardar nuevo cliente
    public ClienteModel saveCliente(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    // Actualizar cliente existente
    public ClienteModel updateCliente(Long id, ClienteModel clienteDetalles) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setTipo(clienteDetalles.getTipo());
            cliente.setNombres(clienteDetalles.getNombres());
            cliente.setApellidos(clienteDetalles.getApellidos());
            cliente.setEmail(clienteDetalles.getEmail());
            cliente.setTelefono(clienteDetalles.getTelefono());
            cliente.setFechaNacimiento(clienteDetalles.getFechaNacimiento());
            cliente.setIne(clienteDetalles.getIne());
            cliente.setDireccion(clienteDetalles.getDireccion());
            cliente.setEstatus(clienteDetalles.getEstatus());
            if (clienteDetalles.getPassword() != null && !clienteDetalles.getPassword().isBlank()) {
                cliente.setPassword(clienteDetalles.getPassword());
            }
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    // Eliminar cliente por ID
    public boolean deleteCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}