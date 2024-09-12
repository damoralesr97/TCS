package com.morales.cuenta_movimientos.repository;

import com.morales.cuenta_movimientos.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovementRepository extends JpaRepository<Movement, Integer> {
}
