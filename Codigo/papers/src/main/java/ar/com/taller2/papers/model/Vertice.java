package ar.com.taller2.papers.model;

import java.io.Serializable;

public class Vertice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 802425425901492413L;
	private String name;
	private Boolean selected;
	
	public Vertice(String name, Boolean selected){
		this.name=name;
		this.selected=selected;
	}
	
	public void select(Boolean select){
		this.selected=select;
	}
	
	public Boolean isSelected(){
		return selected;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
}
