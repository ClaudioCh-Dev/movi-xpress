package proyectosCibertec.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import proyectosCibertec.com.model.Capacidad;
import proyectosCibertec.com.repository.ICapacidadRepository;

@Controller
@RequestMapping("/capacidades")
public class CapacidadController {

    @Autowired
    private ICapacidadRepository repo;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<Capacidad> lista = repo.findByEstado(1);
        model.addAttribute("lstCapacidades", lista);
        model.addAttribute("capacidad", new Capacidad());
        model.addAttribute("vista", "activas");
        return "private-pages/capacidades";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Capacidad c, RedirectAttributes ra) {
        try {
            repo.save(c);
            ra.addFlashAttribute("mensaje", "Capacidad registrada correctamente");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al registrar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/capacidades/listado";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Capacidad c, RedirectAttributes ra) {
        try {
            repo.save(c);
            ra.addFlashAttribute("mensaje", "Capacidad actualizada correctamente");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al actualizar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/capacidades/listado";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes ra) {
        try {
            Capacidad c = repo.findById(id).orElseThrow();
            c.setEstado(2);
            repo.save(c);

            ra.addFlashAttribute("mensaje", "Capacidad eliminada");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al eliminar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/capacidades/listado";
    }

    @GetMapping("/cancelados")
    public String cancelados(Model model) {
        List<Capacidad> lista = repo.findByEstado(2);
        model.addAttribute("lstCapacidades", lista);
        model.addAttribute("capacidad", new Capacidad());
        model.addAttribute("vista", "canceladas");
        return "private-pages/capacidades";
    }

    @GetMapping("/restaurar/{id}")
    public String restaurar(@PathVariable int id, RedirectAttributes ra) {
        try {
            Capacidad c = repo.findById(id).orElseThrow();
            c.setEstado(1);
            repo.save(c);

            ra.addFlashAttribute("mensaje", "Capacidad restaurada correctamente");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al restaurar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/capacidades/cancelados";
    }
}