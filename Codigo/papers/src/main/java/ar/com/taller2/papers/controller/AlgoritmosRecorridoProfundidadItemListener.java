package ar.com.taller2.papers.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import ar.com.taller2.papers.model.graphs.RecorridoProfundidad;

public class AlgoritmosRecorridoProfundidadItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public AlgoritmosRecorridoProfundidadItemListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void itemStateChanged(ItemEvent e) {
		app.getModelo().setAlgorithm(new RecorridoProfundidad(app.getModelo().getGraph(), app.getModelo().getVertex("v1")));
	}

}