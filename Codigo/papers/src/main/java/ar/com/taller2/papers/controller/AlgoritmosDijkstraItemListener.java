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
		if(e.getStateChange() == ItemEvent.SELECTED){
			Dijkstra algoritmo = new Dijkstra(app.getModelo().getGraph());
			app.getVista().addSourceDestSelectionListener();
			app.getModelo().setAlgorithm(algoritmo);
			app.getVista().mostrarInfoAlgoritmo(algoritmo.getTitulo(), algoritmo.getDescripcion(), algoritmo.getAlgoritmo());
		}else{
			app.getVista().removeSourceDestSelectionListener();
		}
	}

}
