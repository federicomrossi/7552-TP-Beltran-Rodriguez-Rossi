package ar.com.taller2.papers.controller;

import ar.com.taller2.papers.Main;
import ar.com.taller2.papers.controller.algoritmos.AlgoritmosComponentesConexasItemListener;
import ar.com.taller2.papers.controller.algoritmos.AlgoritmosDijkstraItemListener;
import ar.com.taller2.papers.controller.algoritmos.AlgoritmosFloydItemListener;
import ar.com.taller2.papers.controller.algoritmos.AlgoritmosFordFulkersonItemListener;
import ar.com.taller2.papers.controller.algoritmos.AlgoritmosPruebaAciclidadItemListener;
import ar.com.taller2.papers.controller.algoritmos.AlgoritmosRecorridoAnchuraItemListener;
import ar.com.taller2.papers.controller.algoritmos.AlgoritmosRecorridoProfundidadItemListener;
import ar.com.taller2.papers.controller.algoritmos.AlgoritmosSpanningTreeItemListener;
import ar.com.taller2.papers.model.GraphModel;

public class AprendiendoGrafos {
	Main vista;
	GraphModel modelo = new GraphModel();
	Tutor tutor = new Tutor();
	
	
	public AprendiendoGrafos(Main main){
		this.vista=main;
//		vista.setGraph(modelo.getGraph());
		vista.addToolbarNextActionListener(new NextActionListener(this));
		vista.addToolbarPreviousActionListener(new PreviousActionListener(this));
		vista.addToolBarPlayActionListener(new PlayActionListener(this));
		vista.addToolbarInitActionListener(new InitActionListener(this));
		vista.addToolbarEndActionListener(new EndActionListener(this));
		
		vista.addModoAprendizajeItemListener(new ModoAprendizajeItemListener(this));
		vista.addModoEvaluacionItemListener(new ModoEvaluacionItemListener(this));
		
		vista.addAlgoritmosRecorridoProfundidadItemListener(new AlgoritmosRecorridoProfundidadItemListener(this));
		vista.addAlgoritmosRecorridoAnchuraItemListener(new AlgoritmosRecorridoAnchuraItemListener(this));
		vista.addAlgoritmosDijkstraItemListener(new AlgoritmosDijkstraItemListener(this));
		vista.addAlgoritmosPruebaAciclidadItemListener(new AlgoritmosPruebaAciclidadItemListener(this));
		vista.addAlgoritmosComponentesFuertementeConexasItemListener(new AlgoritmosComponentesConexasItemListener(this));
		vista.addAlgoritmosArbolExpansionCosteMinimoItemListener(new AlgoritmosSpanningTreeItemListener(this));
		vista.addAlgoritmosFloydItemListener(new AlgoritmosFloydItemListener(this));
		vista.addAlgoritmosFordFulkersonItemListener(new AlgoritmosFordFulkersonItemListener(this));
		
		vista.addMenuArchivoNuevoGrafoOrientadoActionListener(new MenuArchivoNuevoGrafoOrientadoActionListener(this));
		vista.addMenuArchivoNuevoGrafoNoOrientadoActionListener(new MenuArchivoNuevoGrafoNoOrientadoActionListener(this));
		vista.addMenuEditarNuevoVerticeActionListener(new MenuEditarNuevoVerticeActionListener(this));
		vista.addMenuArchivoGuardarActionListener(new MenuArchivoGuardarActionListener(this));
		vista.addMenuArchivoGuardarComoActionListener(new MenuArchivoGuardarComoActionListener(this));
	}
	
	public Main getVista(){
		return vista;
	}
	
	public GraphModel getModelo(){
		return modelo;
	}
	
	public Tutor getTutor() {
		return tutor;
	}
}
