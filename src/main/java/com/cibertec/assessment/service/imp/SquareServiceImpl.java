package com.cibertec.assessment.service.imp;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.assessment.beans.PolygonSquareBean;
import com.cibertec.assessment.model.CustomPolygon;
import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.repo.PolygonRepo;
import com.cibertec.assessment.repo.SquareRepo;
import com.cibertec.assessment.service.PolygonService;
import com.cibertec.assessment.service.SquareService;

@Service
public class SquareServiceImpl implements SquareService{

	@Autowired
	PolygonRepo polygonRepo;
	
	@Autowired 
	SquareRepo squareRepo;
	
	@Autowired
	PolygonService polygonService;
	
	//Al momento de crear se debe validar si 
	//alguno de parte del cuadrado se encuentra dentro de algun
	//poligono y de ser asi se debe capturar el id de los poligonos y 
	//guardar como un string pero con formato de array
	//Ejemplo polygons = "["1","2"]"
	//Se guardan los ids correspondites
	//usar los metodos ya existentes para listar polygonos
	@Override
	public Square create(Square s) {
		return null;
	}

	@Override
	public List<Square> list() {
		return null;
	}

	@Override
	public PolygonSquareBean verificarPoligono(PolygonSquareBean square) {
		String x_pointEntrada = square.getX_points();
		String y_pointEntrada = square.getY_points();
		if(square != null) {
			if(square.getX_points() != null) {
				square.setX_points(  square.getX_points().replace("[", "").replace("]", "")  );
			}
			if(square.getY_points() != null) {
				square.setY_points(  square.getY_points().replace("[", "").replace("]", "")  );
			}
		}
		
		System.out.println("Validando objeto ");
		System.out.println("nombre: " + square.getName());
		System.out.println("npoints: " + square.getNpoints());
		System.out.println("polygons: " + square.getPolygons());
		System.out.println("xPoints: " + square.getX_points());
		System.out.println("yPoints: " + square.getY_points());
		//List<CustomPolygon> listaPoligonos = new ArrayList<>();
		
		PolygonSquareBean responsePs = new PolygonSquareBean(); 
	
		//CustomPolygon polygon1 = new CustomPolygon(51, "CustomPolygon 1",6,"30,50,90,90,60,30","20,10,20,50,50,60");
		//CustomPolygon polygon2 = new CustomPolygon(52, "CustomPolygon 2",5,"140,180,180,150,150","50,30,50,80,60");

		//listaPoligonos.add(polygon1);
		//listaPoligonos.add(polygon2);
	
		
		List<com.cibertec.assessment.model.Polygon> listaPolygon = polygonRepo.findAll();

		System.out.println("cantidadPoligonos: " + listaPolygon.size());
		
		//CustomPolygon cuadrado = new CustomPolygon(3, "Cuadrado",4, "100,130,130,100","70,70,100,100");
		CustomPolygon cuadrado2 = new CustomPolygon(square.getId(), square.getName(),square.getNpoints(), square.getX_points(),square.getY_points());
		
		List<Integer> listaIdsPoligonosIntesec = new ArrayList<>();
		
		int cantPoligonos = listaPolygon.size();
		
		for (int i = 0 ; i < cantPoligonos; i++) {
		  
			com.cibertec.assessment.model.Polygon p = listaPolygon.get(i);
			if(p != null) {
				if(p.getX_points() != null) {
					p.setX_points(  p.getX_points().replace("[", "").replace("]", "").replace(" ", "")  );
				}
				if(p.getY_points() != null) {
					p.setY_points(  p.getY_points().replace("[", "").replace("]", "").replace(" ", "")  );
				}
			}
		 //CustomPolygon poly = listaPolygon.get(i);  
			CustomPolygon poly = new CustomPolygon(p.getId(), p.getName(),p.getNpoints(), p.getX_points(),p.getY_points());
			// Verificar colisión
		 
		 int numpoly = i+1; 
		 
			if (collidesWith(poly, cuadrado2)) {
				
				listaIdsPoligonosIntesec.add(poly.getId()); 
				
				//System.out.println("Los polígonos chocan.");
				System.out.println(" El poligono número " + numpoly + " intersecta con el cuadrado ");
			} else {
				//System.out.println("Los polígonos no chocan.");
				System.out.println(" El poligono número " + numpoly + " no intersecta con el cuadrado ");
			}
		
		}
		
		    
		//System.out.println(obtenerId(listaIdsPoligonosIntesec));
	
		responsePs.setId (square.getId());	
		responsePs.setName(square.getName());
		responsePs.setNpoints(square.getNpoints());
		responsePs.setPolygons(obtenerId(listaIdsPoligonosIntesec));
		responsePs.setX_points(x_pointEntrada);
		responsePs.setY_points(y_pointEntrada);
		
		return responsePs;
		
		
		

	}

	// Método para verificar si este polígono colisiona con otro
	public static boolean collidesWith(CustomPolygon polygon1, CustomPolygon polygon2) {
		Polygon thisPolygon = createPolygon(polygon1);
		Polygon otherPolygon = createPolygon(polygon2);
		return thisPolygon.getBounds().intersects(otherPolygon.getBounds());
	}

	// Método para crear un objeto CustomPolygon a partir de los puntos x e y
	private static Polygon createPolygon(CustomPolygon polygon) {
		int[] xpoints = parsePoints(polygon.getXPoints());
		int[] ypoints = parsePoints(polygon.getYPoints());
		return new Polygon(xpoints, ypoints, polygon.getNpoints());
	}

	// Método para convertir una cadena de puntos en un array de enteros
	private static int[] parsePoints(String pointsString) {
		String[] pointsArray = pointsString.split(",");
		int[] points = new int[pointsArray.length];
		for (int i = 0; i < pointsArray.length; i++) {
			points[i] = Integer.parseInt(pointsArray[i]);
		}
		return points;
	}
	
	private static String obtenerId (List<Integer> listaIdsPoligonosIntesec){
		
		String cadenaID = "";
		
		// Imprimir todo el contenido de la lista con corchetes
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < listaIdsPoligonosIntesec.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(listaIdsPoligonosIntesec.get(i));
        }
        sb.append("]");

        String contenido = sb.toString();
        System.out.println("Contenido de la lista como cadena: " + contenido);
		cadenaID = contenido; 
				
		return cadenaID;
		
	}
	
	
	
	

}
