package proyectosCibertec.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import proyectosCibertec.com.model.Kilometraje;
import proyectosCibertec.com.repository.IKilometrajeRepository;

@Controller
@RequestMapping("/kilometrajes")
public class KilometrajeController {

    @Autowired
    private IKilometrajeRepository repo;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<Kilometraje> lista = repo.findByEstado(1);
        model.addAttribute("lstKilometrajes", lista);
        model.addAttribute("kilometraje", new Kilometraje());
        model.addAttribute("vista", "activas");
        return "private-pages/kilometrajes";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Kilometraje k, RedirectAttributes ra) {
        try {
            repo.save(k);
            ra.addFlashAttribute("mensaje", "Kilometraje registrado correctamente");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al registrar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/kilometrajes/listado";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Kilometraje k, RedirectAttributes ra) {
        try {
            repo.save(k);
            ra.addFlashAttribute("mensaje", "Kilometraje actualizado correctamente");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al actualizar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/kilometrajes/listado";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes ra) {
        try {
            Kilometraje k = repo.findById(id).orElseThrow();
            k.setEstado(2);
            repo.save(k);

            ra.addFlashAttribute("mensaje", "Kilometraje eliminado");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al eliminar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/kilometrajes/listado";
    }

    @GetMapping("/cancelados")
    public String cancelados(Model model) {
        List<Kilometraje> lista = repo.findByEstado(2);
        model.addAttribute("lstKilometrajes", lista);
        model.addAttribute("kilometraje", new Kilometraje());
        model.addAttribute("vista", "canceladas");
        return "private-pages/kilometrajes";
    }

    @GetMapping("/restaurar/{id}")
    public String restaurar(@PathVariable int id, RedirectAttributes ra) {
        try {
            Kilometraje k = repo.findById(id).orElseThrow();
            k.setEstado(1);
            repo.save(k);

            ra.addFlashAttribute("mensaje", "Kilometraje restaurado correctamente");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al restaurar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/kilometrajes/cancelados";
    }
}