package ar.com.taller2.papers.controller.algoritmos;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.graphs.FloydWarshall;

public class AlgoritmosFloydItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public AlgoritmosFloydItemListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			FloydWarshall algoritmo = new FloydWarshall(app.getModelo().getGraph());
			app.getVista().addSourceDestSelectionListener();
			app.getVista().mostrarMensajeEquivocacion("Seleccione los vértices de Origen y Destino");
			app.getModelo().setAlgorithm(algoritmo);
			app.getVista().mostrarInfoAlgoritmo(algoritmo.getTitulo(), algoritmo.getDescripcion(), algoritmo.getAlgoritmo());
		}else{
			app.getVista().removeSourceDestSelectionListener();
		}
	}

}
