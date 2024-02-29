package com.api.superheroe.rest.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperheroeDTO {

    private Long id;
    private String nombre;
}
