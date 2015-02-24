/**
 * 
 */
package ar.com.taller2.papers.controller.algoritmos;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import org.jgrapht.graph.ListenableDirectedGraph;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Vertice;
import ar.com.taller2.papers.model.graphs.TopologicoProfundidad;

/**
 * @author Carlos
 *
 */
public class AlgoritmosTopologicoProfundidadItemListener implements
		ItemListener {

AprendiendoGrafos app;
	
	public AlgoritmosTopologicoProfundidadItemListener(AprendiendoGrafos app){
		this.app =app;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			TopologicoProfundidad algoritmo = new TopologicoProfundidad((ListenableDirectedGraph<Vertice,Arista>)app.getModelo().getGraph());
			app.getModelo().setAlgorithm(algoritmo);
			app.getVista().mostrarInfoAlgoritmo(algoritmo.getTitulo(), algoritmo.getDescripcion(), algoritmo.getAlgoritmo());
		}
	}

}
