package ar.com.taller2.papers.controller.algoritmos;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.graphs.PruebaAciclidad;

public class AlgoritmosPruebaAciclidadItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public AlgoritmosPruebaAciclidadItemListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			PruebaAciclidad algoritmo = new PruebaAciclidad(app.getModelo().getGraph());
			app.getModelo().setAlgorithm(algoritmo);
			app.getVista().mostrarInfoAlgoritmo(algoritmo.getTitulo(), algoritmo.getDescripcion(), algoritmo.getAlgoritmo());
		}
	}

}
