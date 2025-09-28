package org.example.api_proveedores.controller;

import org.example.api_proveedores.model.Proveedor;
import org.example.api_proveedores.service.ProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Proveedor> create(@RequestBody Proveedor proveedor) {
        return proveedorService.create(proveedor);
    }

    @GetMapping
    public Flux<Proveedor> getAll() {
        return proveedorService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Proveedor> getById(@PathVariable Long id) {
        return proveedorService.getById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Proveedor no encontrado con ID: " + id
                )));
    }

    @PutMapping("/{id}")
    public Mono<Proveedor> update(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return proveedorService.update(id, proveedor)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Proveedor no encontrado con ID: " + id
                )));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return proveedorService.getById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Proveedor no encontrado con ID: " + id
                )))
                .flatMap(existing -> proveedorService.delete(id));
    }
}