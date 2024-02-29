package com.cibertec.assessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.assessment.beans.PolygonSquareBean;
import com.cibertec.assessment.service.SquareService;

@RestController
@RequestMapping("/square")
public class SquareController {

	@Autowired
	SquareService  squareService;
	
	@PostMapping
	public ResponseEntity<PolygonSquareBean> verificarCuadrado(@RequestBody PolygonSquareBean ps) {
		return new ResponseEntity<>(squareService.verificarPoligono(ps), HttpStatus.CREATED);
	}
}
