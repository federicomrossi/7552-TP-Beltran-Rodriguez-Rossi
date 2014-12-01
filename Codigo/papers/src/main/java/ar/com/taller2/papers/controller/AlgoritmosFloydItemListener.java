package ar.com.taller2.papers.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import ar.com.taller2.papers.model.graphs.Dijkstra;
import ar.com.taller2.papers.model.graphs.FloydWarshall;

public class AlgoritmosFloydItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public AlgoritmosFloydItemListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void itemStateChanged(ItemEvent e) {
		app.getModelo().setAlgorithm(new FloydWarshall(app.getModelo().getGraph()));
	}

}
