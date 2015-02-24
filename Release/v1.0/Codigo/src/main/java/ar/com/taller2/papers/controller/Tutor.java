package ar.com.taller2.papers.controller;

import java.util.logging.Logger;

import ar.com.taller2.papers.model.Vertice;

public class Tutor {

	private boolean modoAprendizajeOn = true;
	
	public boolean esModoAprendizaje() {
		return modoAprendizajeOn;
	}
	
	public boolean esModoEvaluacion() {
		return !modoAprendizajeOn;
	}
	
	public boolean esIgual(Vertice v1, Vertice v2) {
		Logger.getLogger(this.getClass().getName()).info(v1.toString() + " es igual a " + v2.toString());
		return (v1.toString() == v2.toString());
	}
	
	public void setModoAprendizaje() {
		this.modoAprendizajeOn = true;
	}
	
	public void setModoEvaluacion() {
		this.modoAprendizajeOn = false;
	}
}
