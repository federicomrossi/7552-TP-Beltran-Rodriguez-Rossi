package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.DijkstraShortestPath;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Selectable;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.LineCode;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Vertice;

public class Dijkstra extends GraphAlgorithm {

	private ListenableGraph<Vertice, Arista> graph;
	private Vertice inicio;
	private Vertice fin;
	private int indiceSiguientePaso;
	private List<Arista> camino = new ArrayList<Arista>();
	private List<Selectable> items = new ArrayList<Selectable>();
	
	
	private void createItemList() {
		this.items.add(new LineCode(2));
		this.items.add(new LineCode(3));
		for (int i = 0; i < camino.size(); i++) {
			this.items.add(new LineCode(4));
			this.items.add(new LineCode(5));
			this.items.add(camino.get(i));
			this.items.add(new LineCode(6));
		}
	}
	
	public Dijkstra(ListenableGraph<Vertice, Arista> graph){
		this.graph = graph;
	}
	
	public void setSource(Vertice v){
		this.inicio=v;
	}
	
	public void setDest(Vertice v){
		this.fin=v;
	}
	
	public void iniciar() {
		DijkstraShortestPath<Vertice,Arista> dfit = new DijkstraShortestPath<Vertice,Arista>(this.graph, this.inicio, this.fin);
		this.camino = dfit.getPathEdgeList();
		this.indiceSiguientePaso = 0;
		Logger.getLogger("Dijkstra").info("Inicie el algoritmo");
		createItemList();
	}
	
	public String siguiente() throws NextStepNotExistsException {
		Logger.getLogger("Dijkstra").info("Siguiente");
		if (this.indiceSiguientePaso < this.items.size()) {
			Selectable v = this.items.get(this.indiceSiguientePaso++);
			v.select(true);
		}
		else {
			throw new NextStepNotExistsException("Algoritmo finalizado");
		}
//		if(this.indiceSiguientePaso < this.camino.size()) {
//			Arista v = this.camino.get(this.indiceSiguientePaso++);
//			v.select(true);
//		}
		return "";
	}

	public String anterior() {
		Logger.getLogger("Dijkstra").info("Anterior");

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
	
	public String principio() {
		Logger.getLogger("Dijkstra").info("Principio");
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
		Logger.getLogger("Dijkstra").info("Fin");
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

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.items.size());
//		return (this.indiceSiguientePaso < this.camino.size());
	}

	public boolean cumpleCondicionesIniciales() {
		return (inicio != null && fin != null);
	}

	public String getCondicionesIniciales() {
		// TODO Auto-generated method stub
		return null;
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

	public Boolean isSourceDest() {
		return Boolean.TRUE;
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/dijkstra-pseudocode.txt");
	}

	public String getTitulo() {
		return "Algoritmo de caminos mínimos de Dijsktra";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/dijkstra-info.html");
	}

	public Boolean isCorrect(Resultado r) {
		Logger.getLogger(this.getClass().getName()).info("Siguiente Evaluación");
		List<Arista> res = r.getAristas();
		if(this.indiceSiguientePaso < this.camino.size()) {
			Arista v = this.camino.get(this.indiceSiguientePaso++);
			if(res.size() > 0){
				Arista ver = res.get(res.size()-1);
				if(v.equals(ver)){
					v.select(true);
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	
	public Selectable getCurrentItem() {
		if (this.indiceSiguientePaso - 1 >= 0) 
			return this.items.get(this.indiceSiguientePaso - 1);
		return this.items.get(this.indiceSiguientePaso);
	}
}
