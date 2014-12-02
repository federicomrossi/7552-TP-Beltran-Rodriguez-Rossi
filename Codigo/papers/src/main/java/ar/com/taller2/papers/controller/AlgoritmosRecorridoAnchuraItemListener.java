package ar.com.taller2.papers.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import ar.com.taller2.papers.model.graphs.RecorridoAnchura;

public class AlgoritmosRecorridoAnchuraItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public AlgoritmosRecorridoAnchuraItemListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			RecorridoAnchura algoritmo = new RecorridoAnchura(app.getModelo().getGraph(), app.getModelo().getVertex("v1"));
			app.getModelo().setAlgorithm(algoritmo);
			app.getVista().mostrarInfoAlgoritmo(algoritmo.getTitulo(), algoritmo.getDescripcion(), algoritmo.getAlgoritmo());
		}
	}

}
