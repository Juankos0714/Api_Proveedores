package org.example.api_proveedores.service;

import org.example.api_proveedores.model.Proveedor;
import org.example.api_proveedores.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.Instant;

@Service
public class ProveedorService {
    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public Mono<Proveedor> create(Proveedor proveedor) {
        Proveedor nuevoProveedor = new Proveedor(
                null,
                proveedor.nombre(),
                proveedor.documento(),
                proveedor.telefono(),
                proveedor.correo(),
                true,
                Instant.now()
        );
        return repository.save(nuevoProveedor);
    }

    public Flux<Proveedor> getAll() {
        return repository.findAll();
    }

    public Mono<Proveedor> getById(Long id) {
        return repository.findById(id);
    }

    public Mono<Proveedor> update(Long id, Proveedor newData) {
        return repository.findById(id)
                .flatMap(existing -> {
                    Proveedor actualizado = new Proveedor(
                            id,
                            newData.nombre() != null ? newData.nombre() : existing.nombre(),
                            newData.documento() != null ? newData.documento() : existing.documento(),
                            newData.telefono() != null ? newData.telefono() : existing.telefono(),
                            newData.correo() != null ? newData.correo() : existing.correo(),
                            newData.estado() != null ? newData.estado() : existing.estado(),
                            existing.creadoEn()
                    );
                    return repository.save(actualizado);
                });
    }

    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}