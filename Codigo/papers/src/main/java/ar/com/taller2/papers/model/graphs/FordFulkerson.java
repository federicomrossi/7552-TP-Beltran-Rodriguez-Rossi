package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.jgrapht.alg.EdmondsKarpMaximumFlow;
import org.jgrapht.graph.ListenableDirectedGraph;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.LineCode;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Selectable;
import ar.com.taller2.papers.model.Vertice;

public class FordFulkerson extends GraphAlgorithm {

	private ListenableDirectedGraph<Vertice, Arista> graph;
	private Vertice inicio;
	private Vertice fin;
	private int indiceSiguientePaso;
	private List<Arista> camino = new ArrayList<Arista>();
	private List<Selectable> items = new ArrayList<Selectable>();

	private void createItemList() {
		//TODO falta agregar el algoritmo
		for (int i = 0; i < camino.size(); i++) {
			this.items.add(camino.get(i));
		}
	}
	
	public FordFulkerson(ListenableDirectedGraph<Vertice, Arista> graph){
		this.graph=graph;
	}
	
	public void iniciar() {
		EdmondsKarpMaximumFlow<Vertice, Arista> aKMF = new EdmondsKarpMaximumFlow<Vertice, Arista>(graph);
		aKMF.calculateMaximumFlow(inicio, fin);
		Map<Arista, Double> map = aKMF.getMaximumFlow();
		camino = new ArrayList<Arista>(map.keySet());
		Logger.getLogger(getClass().getSimpleName()).info("Inicie el algoritmo");
		createItemList();
	}

	public String siguiente() throws NextStepNotExistsException {
		Logger.getLogger(getClass().getSimpleName()).info("Siguiente");
		if(this.indiceSiguientePaso < this.items.size()) {
			Selectable v = this.items.get(this.indiceSiguientePaso++);
			v.select(true);
		}
//		if(this.indiceSiguientePaso < this.camino.size()) {
//			Arista v = this.camino.get(this.indiceSiguientePaso++);
//			v.select(true);
//		}
		return "";
	}

	public String anterior() {
		Logger.getLogger(getClass().getSimpleName()).info("Anterior");
		if(this.indiceSiguientePaso - 1 >= 0) {
			Selectable v = this.items.get(--this.indiceSiguientePaso);
			v.select(false);
			return "";
		}

//		if(this.indiceSiguientePaso - 1 >= 0) {
//			Arista v = this.camino.get(--this.indiceSiguientePaso);
//			v.select(false);
//			return true;
//		}
		return "";
	}

	public void terminar() {
		while(--this.indiceSiguientePaso >= 0) {
			Selectable v = this.items.get(this.indiceSiguientePaso);
			v.select(false);
		}
//		while(--this.indiceSiguientePaso >= 0) {
//			Arista v = this.camino.get(this.indiceSiguientePaso);
//			v.select(false);
//		}
		this.indiceSiguientePaso = 0;
	}

	public String principio() {
		Logger.getLogger(getClass().getSimpleName()).info("Principio");
		while(--this.indiceSiguientePaso >= 0) {
			Selectable v = this.items.get(this.indiceSiguientePaso);
			v.select(false);
		}

//		while(--this.indiceSiguientePaso >= 0) {
//			Arista v = this.camino.get(this.indiceSiguientePaso);
//			v.select(false);
//		}
		this.indiceSiguientePaso = 0;
		return "";
	}

	public String fin() {
		Logger.getLogger(getClass().getSimpleName()).info("Fin");
		while(this.indiceSiguientePaso < this.items.size()) {
			Selectable v = this.items.get(this.indiceSiguientePaso++);
			v.select(true);
		}
//		while(this.indiceSiguientePaso < this.camino.size()) {
//			Arista v = this.camino.get(this.indiceSiguientePaso++);
//			v.select(true);
//		}
		return "";
	}

	public boolean cumpleCondicionesIniciales() {
		return (inicio != null && fin != null);
	}

	public String getCondicionesIniciales() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.items.size());
//		return (this.indiceSiguientePaso < this.camino.size());
	}

	public Boolean isSourceDest() {
		return Boolean.TRUE;
	}

	public void setSource(Vertice v) {
		this.inicio=v;
	}

	public void setDest(Vertice v) {
		this.fin=v;
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/ford-fulkerson-pseudocode.html");
	}

	public String getTitulo() {
		return "Algoritmo de Flujos Máximos";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/ford-fulkerson-info.html");
	}

	public Boolean isCorrect(Resultado r) {
		// TODO Auto-generated method stub
		return null;
	}

	public Selectable getCurrentItem() {
		if (this.indiceSiguientePaso - 1 >= 0) 
			return this.items.get(this.indiceSiguientePaso - 1);
		return this.items.get(this.indiceSiguientePaso);
	}

}
