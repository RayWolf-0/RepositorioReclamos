package cl.duoc.demoreclamos.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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

public class Reclamos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RECLAMO")
    private Long id_reclamo;

    @Column(name= "FECHA_RECLAMO", nullable = false)
    @JsonFormat(pattern = "dd-mm-yyyy")
    private String fecha_reclamo;

    @Column(name= "DESCRIPCION", nullable = false, length = 500)
    private String descripcion;

    @Column(name= "ESTADO", nullable = false, length = 10)
    private String estado;


}
