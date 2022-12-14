package com.alkemy.aceleracion.disney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.aceleracion.disney.entity.GeneroEntity;

@Repository
public interface GenreRepository extends JpaRepository<GeneroEntity, Long> {

}
