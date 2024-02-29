package com.cibertec.assessment.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolygonSquareBean {
	
    private Integer id;

    private String name;

	private int npoints;
	
	private String polygons;
	
	private String x_points;
	
	private String y_points;
}
