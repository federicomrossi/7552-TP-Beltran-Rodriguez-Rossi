package ar.com.taller2.papers.controller.algoritmos;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.SimpleDirectedGraph;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Vertice;
import ar.com.taller2.papers.model.graphs.CerraduraTransitiva;

public class AlgoritmosCerraduraTransitivaItemListener  implements ItemListener{

AprendiendoGrafos app;
	
	public AlgoritmosCerraduraTransitivaItemListener(AprendiendoGrafos app){
		this.app =app;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			CerraduraTransitiva algoritmo = new CerraduraTransitiva((ListenableDirectedWeightedGraph)app.getModelo().getGraph());
			app.getModelo().setAlgorithm(algoritmo);
			app.getVista().mostrarInfoAlgoritmo(algoritmo.getTitulo(), algoritmo.getDescripcion(), algoritmo.getAlgoritmo());
		}
	}
	
}
