package com.despensa.inteligente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.despensa.inteligente.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}