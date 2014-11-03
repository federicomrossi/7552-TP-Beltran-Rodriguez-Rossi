package ar.com.taller2.papers.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import ar.com.taller2.papers.model.graphs.Dijkstra;

public class AlgoritmosDijkstraItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public AlgoritmosDijkstraItemListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void itemStateChanged(ItemEvent e) {
		// TODO Poder elegir vértices de inicio y fin.
		app.getModelo().setAlgorithm(new Dijkstra(app.getModelo().getGraph(), app.getModelo().getVertex("v1"), app.getModelo().getVertex("v8")));
	}

}
