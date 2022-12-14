package com.alkemy.aceleracion.disney.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.aceleracion.disney.dto.CharacterBasicDTO;
import com.alkemy.aceleracion.disney.dto.MovieBasicDTO;
import com.alkemy.aceleracion.disney.dto.MovieDTO;
import com.alkemy.aceleracion.disney.service.MovieService;

@RestController
@RequestMapping("movies")
public class MovieController {

	@Autowired
	private MovieService service;
	

	@GetMapping
	public ResponseEntity<List<MovieBasicDTO>> getDetailsByFilter(
			@RequestParam(required = false) String titulo,
			@RequestParam(required = false) List<Long> genreId,
			@RequestParam(required = false, defaultValue = "ASC") String order
			){
		List<MovieBasicDTO> movies = service.getByFilters(titulo, genreId, order);
		return ResponseEntity.ok(movies);
	}
	
	@PostMapping
	public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movie) {
		MovieDTO movieSaved = service.save(movie);
		return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovieDTO> update(@PathVariable Long id, @Valid @RequestBody MovieDTO dto){
		MovieDTO resultDTO = service.update(id, dto);
		return ResponseEntity.ok().body(resultDTO);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
	
	@GetMapping("/{id}/details")
    public ResponseEntity<MovieDTO> getDetails(@PathVariable Long id){
        MovieDTO movie = service.getDetails(id);
        return ResponseEntity.ok().body(movie);
    }

}
