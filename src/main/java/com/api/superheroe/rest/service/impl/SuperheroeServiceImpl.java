package com.api.superheroe.rest.service.impl;

import com.api.superheroe.rest.models.dto.SuperheroeDTO;
import com.api.superheroe.rest.models.entity.Superheroe;
import com.api.superheroe.rest.models.mappers.SuperheroeMapper;
import com.api.superheroe.rest.repository.SuperheroeRepository;
import com.api.superheroe.rest.service.SuperheroeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuperheroeServiceImpl implements SuperheroeService {

    private final SuperheroeRepository repository;
    private final SuperheroeMapper superheroeMapper;

    @Override
    @Cacheable("superheroes")
    public List<SuperheroeDTO> findAll() {
        return superheroeMapper.toSuperheroe(repository.findAll());
    }

    @Override
    public SuperheroeDTO findById(Long id) {
        Optional<Superheroe> opt = repository.findById(id);
        if (opt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El superhéroe solicitado no existe.");
        }
        return superheroeMapper.toSuperheroe(opt.get());
    }

    @Override
    public List<SuperheroeDTO> buscar(String criterio) {
        List<Superheroe> superheroes = repository.findAllByNombreContainingIgnoreCase(criterio.toUpperCase());
        if (superheroes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el super héroe buscado");
        }
        return superheroeMapper.toSuperheroe(superheroes);
    }

    @Override
    @CacheEvict(value = "superheroes", allEntries = true)
    public void update(SuperheroeDTO superHeroe) {
        Superheroe superheroeDb = repository.findById(superHeroe.getId()).orElse(null);
        if (superheroeDb == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El héroe provisto no se encuentra cargado");
        }
        superheroeDb.setNombre(superHeroe.getNombre());
        repository.save(superheroeDb);
    }

    @Override
    @CacheEvict("superheroes")
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El héroe provisto no se encuentra cargado");
        }
        repository.deleteById(id);
    }
}
