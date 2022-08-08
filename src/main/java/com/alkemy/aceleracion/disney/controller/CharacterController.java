package com.alkemy.aceleracion.disney.controller;

import java.util.List;

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
import com.alkemy.aceleracion.disney.dto.CharacterDTO;
import com.alkemy.aceleracion.disney.service.CharacterService;

@RestController
@RequestMapping("characters")
public class CharacterController {
	
	@Autowired
	private CharacterService service;
	
	@GetMapping("/details")
	public ResponseEntity<List<CharacterDTO>> getAll(){
		List<CharacterDTO> characters = service.getAll();
		return ResponseEntity.ok().body(characters);
	}
	
	@GetMapping
	public ResponseEntity<List<CharacterBasicDTO>> getAllBasicData(){
		List<CharacterBasicDTO> characters = service.getAllBasicData();
		return ResponseEntity.ok().body(characters);
	}
	
	@GetMapping("/filters")
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) List<Long> movies) {

        List<CharacterDTO> characters = service.getByFilters(name, age, movies);
        return ResponseEntity.ok(characters);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO dto) {
        CharacterDTO resultDTO = service.update(id, dto);
        return ResponseEntity.ok().body(resultDTO);
    }
	
	@PostMapping
	public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
		CharacterDTO characterSaved = service.save(character);
		return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
