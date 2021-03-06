package ar.com.taller2.papers.model;

import java.io.Serializable;

public class Vertice implements Serializable, Selectable, Comparable<Vertice>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 802425425901492413L;
	private String name;
	private Boolean selected;
	private Double priority;
	
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

	public void setPriority(Double p){
		this.priority=p;
	}
	
	@Override
	public String toString(){
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int compareTo(Vertice o) {
		return Double.compare(priority, o.priority);
	}
	
	public void setName(String name){
		this.name=name;
	}
	
}
