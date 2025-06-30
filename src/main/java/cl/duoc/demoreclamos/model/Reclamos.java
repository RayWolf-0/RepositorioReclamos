package cl.duoc.demoreclamos.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="RECLAMOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa un reclamo")

public class Reclamos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RECLAMO")
    @Schema(description = "id reclamos")

    private int id_reclamo;

    @Column(name= "FECHA_RECLAMO", nullable = false)
    @JsonFormat(pattern = "dd-mm-yyyy")
    @Schema(description = "fecha reclamos")

    private String fecha_reclamo;

    @Column(name= "DESCRIPCION", nullable = false, length = 500)
    @Schema(description = "descripcion reclamos")

    private String descripcion;

    @Column(name= "ESTADO", nullable = false, length = 10)
    @Schema(description = "estado del reclamo")

    private String estado;


}
