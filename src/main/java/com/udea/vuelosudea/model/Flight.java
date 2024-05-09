package com.udea.vuelosudea.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperties;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "Todos los detalles del vuelo")
@Entity
public class Flight implements Serializable {

    @Schema(description = "Identificador del vuelo", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idFlight")
    private Long idFlight;

    @Schema(description = "Nombre del Avion", example = "Boeing 747", required = true)
    @Column(name = "nombreAvion", nullable = false, length = 80)
    private @NonNull String nombreAvion;

    @Schema(description = "Numero de Vuelo", example = "1234", required = true)
    @Column(name = "numeroVuelo", nullable = false, length = 80)
    private @NonNull  String numeroVuelo;

    @Schema(description = "Origen", example = "Bogota", required = true)
    @Column(name = "origen", nullable = false, length = 80)
    private @NonNull String origen;


    @Schema(description = "Destino", example = "Medellin", required = true)
    @Column(name = "destino", nullable = false, length = 80)
    private @NonNull String destino;

    @Schema(description = "Capacidad", example = "100", required = true)
    @Column(name = "capacidad", nullable = false, length = 20)
    private @NonNull int capacidad;

    @Schema(description = "Rating", example = "5", required = true)
    @Column(name = "rating", nullable = false, length = 80)
    @Min(value = 1, message = "El valor debe ser mayor o igual a 1")
    @Max(value = 5, message = "El valor debe ser mayor o igual a 5")
    private  @NonNull int rating;

    @Schema(description = "Plan de vuelo" , example = "1", required = true)
    @Column(name = "planvuelo", nullable = false, length = 80)
    private  @NonNull long planvuelo;

    @Schema(description = "Cumplido", example = "true", required = true)
    private  Boolean cumplido;
}