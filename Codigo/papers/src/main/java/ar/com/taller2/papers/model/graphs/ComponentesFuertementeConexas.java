package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.jgrapht.alg.StrongConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Vertice;

public class ComponentesFuertementeConexas extends GraphAlgorithm {

	private ListenableDirectedGraph<Vertice, DefaultEdge> graph;
	private int indiceSiguientePaso = 0;
	private List<Set<Vertice>> componentesConexas;
	
	public ComponentesFuertementeConexas(ListenableDirectedGraph<Vertice, DefaultEdge> graph){
		this.graph = graph;
	}
	
	public void iniciar() {
		StrongConnectivityInspector<Vertice, DefaultEdge> sCI = new StrongConnectivityInspector<Vertice, DefaultEdge>(graph);
		componentesConexas = sCI.stronglyConnectedSets();
		Logger.getLogger("ComponentesFuertementeConexas").info("Inicié el algoritmo");
	}
	
	public Vertice siguiente() throws NextStepNotExistsException {
		Logger.getLogger("ComponentesFuertementeConexas").info("Siguiente");

		if(this.indiceSiguientePaso < this.componentesConexas.size()) {
			Set<Vertice> vS = componentesConexas.get(this.indiceSiguientePaso++);
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(true); //TODO No hay que seleccionar, hay que poner un color especifico
			}
		}
		return null;
	}

	public boolean anterior() {
		Logger.getLogger("ComponentesFuertementeConexas").info("Anterior");

		if(this.indiceSiguientePaso - 1 >= 0) {
			Set<Vertice> vS = this.componentesConexas.get(--this.indiceSiguientePaso);
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(false); //TODO No hay que seleccionar, hay que poner un color especifico
			}
		}
		
		return false;
	}

	public void terminar() {
		while(--this.indiceSiguientePaso >= 0) {
			Set<Vertice> vS = this.componentesConexas.get(this.indiceSiguientePaso);
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(false); //TODO No hay que seleccionar, hay que poner un color especifico
			}
		}
		this.indiceSiguientePaso = 0;
		Logger.getLogger(this.getClass().getName()).info("Algoritmo finalizado");
	}

	public void principio() {
		Logger.getLogger("ComponentesFuertementeConexas").info("Principio");
		
		while(--this.indiceSiguientePaso >= 0) {
			Set<Vertice> vS = this.componentesConexas.get(this.indiceSiguientePaso);
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(false); //TODO No hay que seleccionar, hay que poner un color especifico
			}
		}
		
		this.indiceSiguientePaso = 0;

	}

	public void fin() {
		Logger.getLogger("ComponentesFuertementeConexas").info("Fin");
		
		while(this.indiceSiguientePaso < this.componentesConexas.size()) {
			Set<Vertice> vS = this.componentesConexas.get(this.indiceSiguientePaso++);
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(true); //TODO No hay que seleccionar, hay que poner un color especifico
			}
		}
	}

	public boolean cumpleCondicionesIniciales() {
		return true;
	}

	public String getCondicionesIniciales() {
		return "No tiene condiciones Iniciales";
	}

	public boolean tieneSiguiente() {
		// TODO Auto-generated method stub
		return false;
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/componentes-fuertemente-conexas-pseudocode.html");
	}

	public String getTitulo() {
		return "Obtención de Componentes Fuertemente Conexas";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/componentes-fuertemente-conexas-info.html");
	}

}
