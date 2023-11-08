package com.example.du_an_md6.mapper;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper<D, E> {


    E toEntity(D dto);

    D toDto(E entity);

    List<D> toListDto(List<E> entityList);


}
