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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/Reclamos")
public class ReclamosController {

    @Autowired
    private ReclamosService reclamosservice;

    //Endpoint para listar los reclamos
    @GetMapping
    @Operation(summary = "Reclamos", description = "Operación que lista los reclamos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se listaron correctamente los reclamos",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Reclamos.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro nada en reclamos",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "no se encuentran Datos")))
    })
    public ResponseEntity<?> ListarReclamos(){
        List<Reclamos> Reclamos = reclamosservice.BuscarReclamos();
        if (Reclamos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay reclamos");            
        } else {
            return ResponseEntity.ok(Reclamos);
        }
    }

    //endpoint para buscar un reclamo
    @GetMapping("/{id_reclamo}")
    @Operation(summary = "Endpoint que busca un reclamo", description = "Operación que busca y lista un reclamo")
    @Parameters(value = {
        @Parameter(name = "id_reclamo", description = "Id del reclamo que se va a buscar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente El reclamo",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Reclamos.class))),
        @ApiResponse(responseCode = "404", description = "No se encuentra el reclamo",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "No se encuentran reclamos")))
    })
    public ResponseEntity<?> BuscarunReclamo(@PathVariable int id_reclamo){
        try {
            Reclamos reclamobuscado = reclamosservice.BuscarunReclamo(id_reclamo);
            return ResponseEntity.ok(reclamobuscado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamo no encontrado");
        }
    }

    //endpoint para gardar un reclamo
    @PostMapping
    @Operation(summary = "Endpoint que registra un reclamo", description = "Endpoint que registra un reclamo", 
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto reclamo que se va a registrar", required = true,
    content = @Content(schema = @Schema(implementation = Reclamos.class))
    ))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Indica que se registro correctamente el reclamo", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reclamos.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar el reclamo",
        content = @Content(schema = @Schema(type = "String", example = "No se puede registar El reclamo")))
    })
    public ResponseEntity<?> GuardarReclamo(@RequestBody Reclamos reclamoguardar){
        try {
            Reclamos registrarreclamo = reclamosservice.Guardarreclamo(reclamoguardar);
            return ResponseEntity.ok(registrarreclamo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Reclamo no registrado");
        }
    }

    //endpoint que edita un reclamo
    @PutMapping("/{id_reclamo}")
    @Operation(summary = "Endpoint que edita un reclamo", description = "Endpoint que edita un reclamo", 
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto reclamo que se va a editar", required = true,
    content = @Content(schema = @Schema(implementation = Reclamos.class))
    ))
    @Parameters(value = {
        @Parameter(name = "id_reclamo", description = "Id del reclamo que se va a editar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Indica que se registro correctamente el reclamo", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reclamos.class))),
        @ApiResponse(responseCode = "500", description = "reclamo no esta registrado",
        content = @Content(schema = @Schema(type = "String", example = "reclamo no esta registrado")))
    })
    public ResponseEntity<?> EditarReclamo(@PathVariable int id_reclamo, @RequestBody Reclamos editarreclamos){
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
