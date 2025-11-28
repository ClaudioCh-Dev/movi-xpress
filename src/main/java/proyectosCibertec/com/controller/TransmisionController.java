package proyectosCibertec.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import proyectosCibertec.com.model.Transmision;
import proyectosCibertec.com.repository.ITransmisionRepository;

@Controller
@RequestMapping("/transmisiones")
public class TransmisionController {

    @Autowired
    private ITransmisionRepository repo;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<Transmision> lista = repo.findByEstado(1);
        model.addAttribute("lstTransmisiones", lista);
        model.addAttribute("transmision", new Transmision());
        model.addAttribute("vista", "activas");
        return "private-pages/transmisiones";
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute Transmision t, RedirectAttributes ra) {
        try {
            repo.save(t);
            ra.addFlashAttribute("mensaje", "Transmisión registrada correctamente");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al registrar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/transmisiones/listado";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Transmision t, RedirectAttributes ra) {
        try {
            repo.save(t);
            ra.addFlashAttribute("mensaje", "Transmisión actualizada correctamente");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al actualizar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/transmisiones/listado";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes ra) {
        try {
            Transmision t = repo.findById(id).orElseThrow();
            t.setEstado(2);
            repo.save(t);

            ra.addFlashAttribute("mensaje", "Transmisión eliminada");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al eliminar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/transmisiones/listado";
    }

    @GetMapping("/cancelados")
    public String cancelados(Model model) {
        List<Transmision> lista = repo.findByEstado(2);
        model.addAttribute("lstTransmisiones", lista);
        model.addAttribute("transmision", new Transmision());
        model.addAttribute("vista", "canceladas");
        return "private-pages/transmisiones";
    }

    @GetMapping("/restaurar/{id}")
    public String restaurar(@PathVariable int id, RedirectAttributes ra) {
        try {
            Transmision t = repo.findById(id).orElseThrow();
            t.setEstado(1);
            repo.save(t);

            ra.addFlashAttribute("mensaje", "Transmisión restaurada correctamente");
            ra.addFlashAttribute("css_mensaje", "alert alert-success");
            ra.addFlashAttribute("tipoMensaje", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("mensaje", "Error al restaurar: " + e.getMessage());
            ra.addFlashAttribute("css_mensaje", "alert alert-danger");
            ra.addFlashAttribute("tipoMensaje", "error");
        }
        return "redirect:/transmisiones/cancelados";
    }
}