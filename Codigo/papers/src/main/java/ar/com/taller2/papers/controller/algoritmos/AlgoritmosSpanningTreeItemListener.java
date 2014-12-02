package ar.com.taller2.papers.controller.algoritmos;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.jgrapht.graph.ListenableUndirectedGraph;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Vertice;
import ar.com.taller2.papers.model.graphs.SpanningTree;

public class AlgoritmosSpanningTreeItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public AlgoritmosSpanningTreeItemListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			SpanningTree algoritmo = new SpanningTree((ListenableUndirectedGraph<Vertice,Arista>)app.getModelo().getGraph(),app.getModelo().getVertex("v1"));
			app.getModelo().setAlgorithm(algoritmo);
			app.getVista().mostrarInfoAlgoritmo(algoritmo.getTitulo(), algoritmo.getDescripcion(), algoritmo.getAlgoritmo());
		}
	}

}
