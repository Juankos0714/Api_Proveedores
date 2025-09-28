package org.example.api_proveedores.repository;

import org.example.api_proveedores.model.Proveedor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends ReactiveCrudRepository<Proveedor, Long> {
}