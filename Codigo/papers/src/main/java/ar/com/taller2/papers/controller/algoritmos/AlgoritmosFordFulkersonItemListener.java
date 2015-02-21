package ar.com.taller2.papers.controller.algoritmos;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.jgrapht.graph.ListenableDirectedGraph;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Vertice;
import ar.com.taller2.papers.model.graphs.FordFulkerson;

public class AlgoritmosFordFulkersonItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public AlgoritmosFordFulkersonItemListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			FordFulkerson algoritmo = new FordFulkerson((ListenableDirectedGraph<Vertice,Arista>)app.getModelo().getGraph()); 
			app.getVista().addSourceDestSelectionListener();
			//app.getVista().mostrarMensajeEquivocacion("Seleccione los vértices de Origen y Destino");
			app.getModelo().setAlgorithm(algoritmo);
			app.getVista().mostrarInfoAlgoritmo(algoritmo.getTitulo(), algoritmo.getDescripcion(), algoritmo.getAlgoritmo());
		}else{
			app.getVista().removeSourceDestSelectionListener();
		}
	}

}
