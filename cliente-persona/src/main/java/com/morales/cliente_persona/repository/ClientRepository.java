package com.morales.cliente_persona.repository;

import com.morales.cliente_persona.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByStatusTrue();

    Optional<Client> findByDniAndStatusTrue(String dni);

}
