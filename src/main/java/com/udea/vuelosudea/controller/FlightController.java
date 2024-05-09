package com.udea.vuelosudea.controller;

import com.udea.vuelosudea.exception.InvalidRating;
import com.udea.vuelosudea.exception.ModelNotFoundException;
import com.udea.vuelosudea.model.Flight;
import com.udea.vuelosudea.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight")
@CrossOrigin("*")
@Tag(name = "Sistema de gestion de vuelos", description = "Operaciones para los vuelos")

public class FlightController {

    @Autowired
    FlightService flightService;

    @Operation(summary = "Add vuelo")
    @PostMapping("/save")
    public long save(
            @Parameter(description = "Objeto vuelo que se va a guardar", required = true)
            @RequestBody Flight flight) throws InvalidRating {
        if(flight.getRating()>5) throw new InvalidRating("Id debe ser menor o igual a 5");
        flightService.save(flight);
        return flight.getIdFlight();
    }


    @Operation(summary = "Ver la lista de vuelos disponibles",
            description = "Obtiene la lista de vuelos disponibles",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de vuelos exitosa"),
                    @ApiResponse(responseCode = "401", description = "Usted no está autorizado para ver este recurso"),
                    @ApiResponse(responseCode = "403", description = "El acceso al recurso que usted intenta alcanzar es prohibido"),
                    @ApiResponse(responseCode = "404", description = "El acceso al recurso que usted intenta alcanzar no se encuentra")
            })
    @GetMapping("/listAll")
    public Iterable<Flight> listAllFlights(){return flightService.list();}

    @Operation(summary = "Ver un vuelo por su ID")
    @GetMapping("/list/{id}")
    public Flight listFlightById(
            @Parameter(description = "El Id del vuelo que se desea consultar", required = true)
            @PathVariable("id") int id){
        Optional<Flight> flight = flightService.listId(id);
        if(flight.isPresent()){
            return flight.get();
        }
        throw new ModelNotFoundException("ID de vuelo Invalido");
    }


    @Operation(summary = "Dame los mejores vuelos")
    @GetMapping("/topFlights")
    public ResponseEntity<List<Flight>> viewBestFlights(){
        List<Flight> list= flightService.viewBestFlight();
        return new ResponseEntity<List<Flight>>(list, HttpStatus.ACCEPTED);
    }

    @PutMapping
    public Flight updateFlight(@RequestBody Flight flight){
        return flightService.update(flight);
    }


    @DeleteMapping("{id}")
    public String deleteFlight(@PathVariable long id){
        return flightService.delete(id);
    }
}
