package ar.com.taller2.papers.model;

import org.jgrapht.graph.DefaultWeightedEdge;


public class Arista extends DefaultWeightedEdge implements Selectable {

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
		return ((Double)getWeight()).intValue();
	}
	
	public Boolean isSelected(){
		return selected;
	}
	
	public void select(Boolean selected){
		this.selected=selected;
	}
	
	public String toString(){
		return ((Double)getWeight()).toString();
	}
	
}
