package com.api.superheroe.rest.controller;

import com.api.superheroe.annotations.Contar;
import com.api.superheroe.rest.models.dto.SuperheroeDTO;
import com.api.superheroe.rest.service.SuperheroeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superheroes")
@AllArgsConstructor
public class SuperheroeController {

    private final SuperheroeService service;

    @GetMapping
    @Contar
    public ResponseEntity<List<SuperheroeDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        SuperheroeDTO superHeroe = service.findById(id);
        return ResponseEntity.ok(superHeroe);
    }

    @GetMapping("/busqueda/{criterio}")
    public ResponseEntity<?> buscar(@PathVariable String criterio) {
        List<SuperheroeDTO> superHeroes = service.buscar(criterio);
        return ResponseEntity.ok(superHeroes);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody SuperheroeDTO superHeroe) {
        service.update(superHeroe);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
