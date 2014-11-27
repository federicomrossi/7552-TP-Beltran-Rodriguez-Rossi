package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Vertice;

public class PruebaAciclidad extends GraphAlgorithm {
	
	ListenableGraph<Vertice, DefaultEdge> graph;
	Set<Vertice> ciclo = null;
	Iterator<Vertice> it = null;
	
	public PruebaAciclidad(ListenableGraph<Vertice, DefaultEdge> graph){
		this.graph = graph;
	}

	public Vertice siguiente() throws NextStepNotExistsException {
		if (it != null && it.hasNext()) {
			return it.next();
		}
		throw new NextStepNotExistsException("No tiene ciclos");
	}

	public boolean anterior() {
		// TODO Auto-generated method stub
		return false;
	}

	public void iniciar() {
		
		CycleDetector<Vertice,DefaultEdge> detector = new CycleDetector<Vertice, DefaultEdge>((DirectedGraph<Vertice, DefaultEdge>) this.graph);
		Logger.getLogger(this.getClass().getName()).info("Cree el detector de ciclos");
		if (detector.detectCycles()) {
			Logger.getLogger(this.getClass().getName()).info("Tiene ciclos");
			this.ciclo = detector.findCycles();
			it = this.ciclo.iterator();
		}
	}

	public void terminar() {
		// TODO Auto-generated method stub
		
	}

	public void principio() {
		// TODO Auto-generated method stub
		
	}

	public void fin() {
		// TODO Auto-generated method stub
		
	}

	public boolean cumpleCondicionesIniciales() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getCondicionesIniciales() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean tieneSiguiente() {
		if (it != null) {
			return it.hasNext();
		}
		return false;
	}

	public URL getAlgoritmo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitulo() {
		// TODO Auto-generated method stub
		return null;
	}

	public URL getDescripcion() {
		// TODO Auto-generated method stub
		return null;
	}

}