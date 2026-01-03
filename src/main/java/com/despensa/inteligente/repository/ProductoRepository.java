package com.despensa.inteligente.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.despensa.inteligente.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> { 
    Optional<Producto> findByCodigoBarras(String codigoBarras);
    List<Producto> findByFechaExpiracionBefore(LocalDate fecha);
    List<Producto> findByFechaExpiracionBetween(LocalDate startDate, LocalDate endDate);
}