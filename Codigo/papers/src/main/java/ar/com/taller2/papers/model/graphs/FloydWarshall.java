package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.FloydWarshallShortestPaths;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Vertice;

public class FloydWarshall extends GraphAlgorithm {

	private ListenableGraph<Vertice, Arista> graph;
	private Vertice inicio;
	private Vertice fin;
	private int indiceSiguientePaso;
	private List<Arista> camino = new ArrayList<Arista>();
	
	public FloydWarshall(ListenableGraph<Vertice, Arista> graph){
		this.graph=graph;
	}
	
	
	public void iniciar() {
		FloydWarshallShortestPaths<Vertice, Arista> fW = new FloydWarshallShortestPaths<Vertice, Arista>(graph);
		camino = fW.getShortestPath(inicio, fin).getEdgeList();
		this.indiceSiguientePaso = 0;
		Logger.getLogger(getClass().getSimpleName()).info("Inicie el algoritmo");
	}
	
	public void siguiente() throws NextStepNotExistsException {
		Logger.getLogger(getClass().getSimpleName()).info("Siguiente");
		if(this.indiceSiguientePaso < this.camino.size()) {
			Arista v = this.camino.get(this.indiceSiguientePaso++);
			v.select(true);
		}
	}

	public boolean anterior() {
		Logger.getLogger(getClass().getSimpleName()).info("Anterior");
		if(this.indiceSiguientePaso - 1 >= 0) {
			Arista v = this.camino.get(--this.indiceSiguientePaso);
			v.select(false);
			return true;
		}
		
		return false;
	}

	

	public void terminar() {
		while(--this.indiceSiguientePaso >= 0) {
			Arista v = this.camino.get(this.indiceSiguientePaso);
			v.select(false);
		}
		this.indiceSiguientePaso = 0;
	}

	public void principio() {
		Logger.getLogger(getClass().getSimpleName()).info("Principio");
		while(--this.indiceSiguientePaso >= 0) {
			Arista v = this.camino.get(this.indiceSiguientePaso);
			v.select(false);
		}
		
		this.indiceSiguientePaso = 0;
	}

	public void fin() {
		Logger.getLogger(getClass().getSimpleName()).info("Fin");
		while(this.indiceSiguientePaso < this.camino.size()) {
			Arista v = this.camino.get(this.indiceSiguientePaso++);
			v.select(true);
		}
	}

	public boolean cumpleCondicionesIniciales() {
		return (inicio != null && fin != null);
	}

	public String getCondicionesIniciales() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.camino.size());
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
		return this.getClass().getResource("/algorithms/floyd-pseudocode.html");
	}

	public String getTitulo() {
		return "Algoritmo de caminos mínimos de Floyd";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/floyd-info.html");
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

}
