package com.api.superheroe.rest.models.mappers;

import com.api.superheroe.rest.models.dto.SuperheroeDTO;
import com.api.superheroe.rest.models.entity.Superheroe;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface SuperheroeMapper {

    SuperheroeDTO toSuperheroe(Superheroe superheroe);
    List<SuperheroeDTO> toSuperheroe(List<Superheroe> superheroeList);
}
