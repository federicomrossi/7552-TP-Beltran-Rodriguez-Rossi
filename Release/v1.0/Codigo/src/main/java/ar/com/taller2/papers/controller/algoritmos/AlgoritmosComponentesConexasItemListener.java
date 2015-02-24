package ar.com.taller2.papers.controller.algoritmos;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.jgrapht.graph.ListenableDirectedGraph;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Vertice;
import ar.com.taller2.papers.model.graphs.ComponentesFuertementeConexas;

public class AlgoritmosComponentesConexasItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public AlgoritmosComponentesConexasItemListener(AprendiendoGrafos app){
		this.app =app;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			ComponentesFuertementeConexas algoritmo = new ComponentesFuertementeConexas((ListenableDirectedGraph<Vertice,Arista>)app.getModelo().getGraph());
			app.getModelo().setAlgorithm(algoritmo);
			app.getVista().mostrarInfoAlgoritmo(algoritmo.getTitulo(), algoritmo.getDescripcion(), algoritmo.getAlgoritmo());
		}
	}

}
