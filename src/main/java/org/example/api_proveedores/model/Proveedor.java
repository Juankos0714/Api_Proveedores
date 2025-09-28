package org.example.api_proveedores.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("proveedores")
public record Proveedor(
        @Id Long id,
        String nombre,
        String documento,
        String telefono,
        String correo,
        Boolean estado,
        @Column("creado_en") Instant creadoEn
) {
    public static Proveedor nuevo(String nombre, String documento, String telefono, String correo) {
        return new Proveedor(null, nombre, documento, telefono, correo, true, Instant.now());
    }

    public Proveedor conId(Long id) {
        return new Proveedor(id, this.nombre, this.documento, this.telefono, this.correo, this.estado, this.creadoEn);
    }
}