package cl.duoc.demoreclamos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.demoreclamos.model.Reclamos;
import cl.duoc.demoreclamos.service.ReclamosService;

@RestController
@RequestMapping("/api/v1/Reclamos")
public class ReclamosController {

    @Autowired
    private ReclamosService reclamosservice;

    @GetMapping
    public ResponseEntity<?> ListarReclamos(){
        List<Reclamos> Reclamos = reclamosservice.BuscarReclamos();
        if (Reclamos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay reclamos");            
        } else {
            return ResponseEntity.ok(Reclamos);
        }
    }

    @GetMapping("/{id_reclamo}")
    public ResponseEntity<?> BuscarunReclamo(@PathVariable Long id_reclamo){
        try {
            Reclamos reclamobuscado = reclamosservice.BuscarunReclamo(id_reclamo);
            return ResponseEntity.ok(reclamobuscado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamo no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<?> GuardarReclamo(@RequestBody Reclamos reclamoguardar){
        try {
            Reclamos registrarreclamo = reclamosservice.Guardarreclamo(reclamoguardar);
            return ResponseEntity.ok(registrarreclamo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Reclamo no registrado");
        }
    }

    @PutMapping("/{id_reclamo}")
    public ResponseEntity<?> EditarReclamo(@PathVariable Long id_reclamo, @RequestBody Reclamos editarreclamos){
        try {
            Reclamos reclamoeditado = reclamosservice.BuscarunReclamo(id_reclamo);
            reclamoeditado.setDescripcion(editarreclamos.getDescripcion());
            reclamoeditado.setId_reclamo(editarreclamos.getId_reclamo());
            reclamoeditado.setFecha_reclamo(editarreclamos.getFecha_reclamo());
            reclamoeditado.setEstado(editarreclamos.getEstado());
            reclamosservice.Guardarreclamo(reclamoeditado);
            return ResponseEntity.ok(reclamoeditado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Reclamo no registrado");

        }
    }

}
