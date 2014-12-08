package ar.com.taller2.papers.model;

public class LineCode implements Selectable {
	
	Boolean selected;
	private int id;
	
	public LineCode(int id) {
		this.id = id;
		this.selected = false;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Boolean isSelected(){
		return selected;
	}
	
	public void select(Boolean selected){
		this.selected=selected;
	}

}
