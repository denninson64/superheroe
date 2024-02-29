package com.api.superheroe.rest.service;

import com.api.superheroe.rest.models.dto.SuperheroeDTO;

import java.util.List;

public interface SuperheroeService {

    List<SuperheroeDTO> findAll();

    SuperheroeDTO findById(Long id);

    List<SuperheroeDTO> buscar(String criterio);

    void update(SuperheroeDTO superHeroe);

    void delete(Long id);
}
