package com.cibertec.assessment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@Entity
public class CustomPolygon {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public int npoints;

    private String xPoints;// se debe guardar un array de 5 puntos como string "

    private String yPoints;// se debe guardar un array de 5 puntos como string

    public CustomPolygon(Integer id, String name, int npoints, String xPoints, String yPoints) {
        this.id = id;
        this.name = name;
        this.npoints = npoints;
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }
	
}
