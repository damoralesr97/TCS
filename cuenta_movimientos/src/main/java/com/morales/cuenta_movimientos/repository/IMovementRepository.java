package com.morales.cuenta_movimientos.repository;

import com.morales.cuenta_movimientos.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface IMovementRepository extends JpaRepository<Movement, Integer> {

    List<Movement> findByMovementDateIsBetween(Date startDate, Date endDate);

}
