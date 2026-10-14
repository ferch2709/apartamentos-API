package com.blue.apartamentos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.models.ClienteModel.ClienteStatus;
import com.blue.apartamentos.models.ClienteModel.TipoCliente;
import com.blue.apartamentos.services.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //Listar clientes (GET /clientes)
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.getAllClientes());
        return "clientes/lista";
    }

    //Formulario nuevo cliente (GET /clientes/nuevo)
    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("cliente", new ClienteModel());
        model.addAttribute("accion", "Crear");
        model.addAttribute("tipos", TipoCliente.values());
        model.addAttribute("estatus", ClienteStatus.values());
        return "clientes/formulario";
    }

    //Formulario editar cliente (GET /clientes/editar/{id})
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttrs) {
        return clienteService.getClienteById(id).map(cliente -> {
            model.addAttribute("cliente", cliente);
            model.addAttribute("accion", "Editar");
            model.addAttribute("tipos", TipoCliente.values());
            model.addAttribute("estatus", ClienteStatus.values());
            return "clientes/formulario";
        }).orElseGet(() -> {
            redirectAttrs.addFlashAttribute("error", "Cliente no encontrado.");
            return "redirect:/clientes";
        });
    }

    //Guardar cliente y mostrar mensaje de éxito (POST /clientes/guardar)
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") ClienteModel cliente, RedirectAttributes redirectAttrs) {
        try {
            if (cliente.getId() != null) {
                clienteService.updateCliente(cliente.getId(), cliente);
                redirectAttrs.addFlashAttribute("success", "Cliente actualizado con éxito.");
            } else {
                clienteService.saveCliente(cliente);
                redirectAttrs.addFlashAttribute("success", "Cliente registrado con éxito.");
            }
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Error al guardar el cliente: " + e.getMessage());
        }
        return "redirect:/clientes";
    }

    //Eliminar cliente compatible con el modal POST (POST /clientes/eliminar/{id})
    @PostMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id, RedirectAttributes redirectAttrs) {
        if (clienteService.deleteCliente(id)) {
            redirectAttrs.addFlashAttribute("success", "Cliente eliminado correctamente.");
        } else {
            redirectAttrs.addFlashAttribute("error", "No se pudo eliminar el cliente.");
        }
        return "redirect:/clientes";
    }
}