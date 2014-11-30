package ar.com.taller2.papers.model;

import org.jgrapht.graph.DefaultEdge;

public class Arista extends DefaultEdge {

	/**
	 * 
	 */
	private static final long serialVersionUID = 814309159104236517L;
	Integer peso;
	Boolean selected;
	
	public Arista(Integer peso, Boolean selected){
		this.peso=peso;
		this.selected=selected;
	}
	
	public Arista(Integer peso){
		this.peso=peso;
		this.selected=false;
	}
	
	public Arista(){
		this.peso=1;
		this.selected=false;
	}
	
	public Integer getPeso(){
		return peso;
	}
	
	public Boolean isSelected(){
		return selected;
	}
	
}
