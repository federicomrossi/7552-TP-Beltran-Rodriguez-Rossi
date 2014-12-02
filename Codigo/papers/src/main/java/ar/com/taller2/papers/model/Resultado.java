package ar.com.taller2.papers.model;

import java.util.ArrayList;
import java.util.List;

public class Resultado {

	private List<Vertice> vertices;
	private List<Arista> aristas;
	
	public Resultado(){
		vertices=new ArrayList<Vertice>();
		aristas=new ArrayList<Arista>();
	}
	
	public void addArista(Arista a){
		aristas.add(a);
	}
	
	public List<Arista> getAristas(){
		return aristas;
	}
	
	public void addVertice(Vertice v){
		vertices.add(v);
	}
	
	public List<Vertice> getVertices(){
		return vertices;
	}
}
