package com.api.superheroe.rest.repository;

import com.api.superheroe.rest.models.entity.Superheroe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SuperheroeRepository extends JpaRepository<Superheroe, Long> {

    List<Superheroe> findAllByNombreContainingIgnoreCase(String criterio);
}
