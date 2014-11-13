package ar.com.taller2.papers.controller;

import ar.com.taller2.papers.model.Vertice;

public class Tutor {

	private boolean modoAprendizajeOn = true;
	
	public boolean esModoAprendizaje() {
		return modoAprendizajeOn;
	}
	
	public boolean esModoEvaluacion() {
		return !modoAprendizajeOn;
	}
	
	public boolean esIgual(Vertice v) {
		return true;
	}
	
	public void setModoAprendizaje() {
		this.modoAprendizajeOn = true;
	}
	
	public void setModoEvaluacion() {
		this.modoAprendizajeOn = false;
	}
}
