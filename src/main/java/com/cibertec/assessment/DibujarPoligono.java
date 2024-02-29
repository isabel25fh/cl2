package com.cibertec.assessment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.cibertec.assessment.model.CustomPolygon;

public class DibujarPoligono extends JPanel {

	// Coordenadas de los vértices del polígono
		Double xpoint1[] = { 10.0, 205.0, 305.0, 405.0, 500.0 };
		Double ypoint1[] = { 10.0, 501.0, 506.0, 107.0, 30.0 };

//	    Double xpoint1[] = {100.0, 605.0, 305.0, 405.0, 500.0};
//	    Double ypoint1[] = {100.0, 601.0, 506.0, 337.0, 300.0};

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;

			// Dibujar el polígono
			g2d.setColor(Color.BLUE);
			g2d.drawPolygon(toIntArray(xpoint1), toIntArray(ypoint1), xpoint1.length);
		}

		// Método para convertir Double[] a int[]
		private int[] toIntArray(Double[] array) {
			int[] intArray = new int[array.length];
			for (int i = 0; i < array.length; i++) {
				intArray[i] = array[i].intValue();
			}
			return intArray;
		}

		public static void main(String[] args) {
			//--------------
			List<Polygon> list = new ArrayList<>();
			
			//-----------------------
			
			//poligono 1:
			// {"30,50,90,90,60,30"};
			// {"20,10,20,50,50,60"};	
			
			//-----------------------
			
			//poligono 2:
			//{140,180,180,150,150};
			//{50,30,50,80,60};	
			
			//-----------------------
			
			
			
			//cuadrado 1  intersecta npoligono 1
			
			 //Double xpoint1[] = {80,110,110,80};
			//Double ypoint1[] = {40,40,70,70};
		   
			
			//cuadrado2:
			//Double xpoint1[] = {70.0, 90.0, 90.0, 70.0};
			//Double ypoint1[] = {60.0, 60.0, 80.0, 80.0};
			
			
			//cuadrado3:
			//Double xpoint1[] = {80.0, 100.0, 100.0, 80.0};
			//Double ypoint1[] = {0.0, 0.0, 20.0, 20.0};
			
			//cuadrado4: 
			//Double xpoint1[] = {20,40,40,20};
			//Double ypoint1[] = {60,60,80,80};
			
			//cuadrado5: intersecta poligono 2
			// "170,200,200,170" = x
			// "10,10,40,40" = y
			
			
			//cuadrado6: intersecta con ambos
					// "80,150,150,80" = x
					// "40,40,110,110" = y
			

			//cuadrado7: ninguno intersecta
					// "100,130,130,100" = x
					// "70,70,100,100" = y
		

			List<CustomPolygon> listaPoligonos = new ArrayList<>();
			
			CustomPolygon polygon1 = new CustomPolygon(51, "CustomPolygon 1",6,"30,50,90,90,60,30","20,10,20,50,50,60");
			CustomPolygon polygon2 = new CustomPolygon(52, "CustomPolygon 2",5,"140,180,180,150,150","50,30,50,80,60");

			listaPoligonos.add(polygon1);
			listaPoligonos.add(polygon2);
			
			
			CustomPolygon cuadrado = new CustomPolygon(3, "Cuadrado",4, "100,130,130,100","70,70,100,100");
			
			
			List<Integer> listaPoligonosIntersec = new ArrayList<>();
			
			int cantPoligonos = listaPoligonos.size();
			
			for (int i = 0 ; i < cantPoligonos; i++) {
			    
			
			    	
			 CustomPolygon poly = listaPoligonos.get(i);  
				// Verificar colisión
			 System.out.println("Cantidad poligonos " + cantPoligonos); 
			 int numpoly = i+1; 
			 
				if (collidesWith(poly, cuadrado)) {
					
					listaPoligonosIntersec.add(poly.getId()); 
					
					//System.out.println("Los polígonos chocan.");
					System.out.println(" El poligono número " + numpoly + " intersecta con el cuadrado ");
				} else {
					//System.out.println("Los polígonos no chocan.");
					System.out.println(" El poligono número " + numpoly + " no intersecta con el cuadrado ");
				}
			
			}
			
			    
			System.out.println(obtenerId(listaPoligonosIntersec));
		
				
			
			
			
			

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
		
		private static String obtenerId (List<Integer> listaPoligonosIntersec){
			
			String cadenaID = "";
			
			// Imprimir todo el contenido de la lista con corchetes
	        StringBuilder sb = new StringBuilder();
	        sb.append("[");
	        for (int i = 0; i < listaPoligonosIntersec.size(); i++) {
	            if (i > 0) {
	                sb.append(", ");
	            }
	            sb.append(listaPoligonosIntersec.get(i));
	        }
	        sb.append("]");

	        String contenido = sb.toString();
	        System.out.println("Contenido de la lista como cadena: " + contenido);
			
			return cadenaID;
			
		}
}
