package com.cibertec.assessment.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.assessment.model.Polygon;
import com.cibertec.assessment.service.PolygonService;

@RestController
@RequestMapping("/polygon")
public class PolygonController {

	@Autowired
	PolygonService  polygonService;
	
	@PostMapping
	public ResponseEntity<Polygon> add(@RequestBody Polygon p) {
		return new ResponseEntity<>(polygonService.create(p), HttpStatus.CREATED);
	}
	/*
	
	@GetMapping
	public ResponseEntity<List<Polygon>> list() {
		return new ResponseEntity<>(polygonService.list(), HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id")int id) {
		polygonService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}*/
	
}
