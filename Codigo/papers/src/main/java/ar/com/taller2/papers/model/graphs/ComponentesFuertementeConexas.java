package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.swing.table.TableModel;

import org.jgrapht.alg.StrongConnectivityInspector;
import org.jgrapht.graph.ListenableDirectedGraph;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Selectable;
import ar.com.taller2.papers.model.Vertice;

public class ComponentesFuertementeConexas extends GraphAlgorithm {

	private ListenableDirectedGraph<Vertice, Arista> graph;
	private int indiceSiguientePaso = 0;
	private List<Set<Vertice>> componentesConexas;
	
	public ComponentesFuertementeConexas(ListenableDirectedGraph<Vertice, Arista> graph){
		this.graph = graph;
	}
	
	public void iniciar() {
		StrongConnectivityInspector<Vertice, Arista> sCI = new StrongConnectivityInspector<Vertice, Arista>(graph);
		componentesConexas = sCI.stronglyConnectedSets();
		Logger.getLogger("ComponentesFuertementeConexas").info("Inicié el algoritmo");
	}
	
	private void deseleccionar(int indiceComponente) {
		if (indiceComponente >= 0 && indiceComponente < componentesConexas.size()) {
			Set<Vertice> vS = componentesConexas.get(indiceComponente);
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(false);
			}			
		}
	}

	private void seleccionar(int indiceComponente) {
		if (indiceComponente >= 0 && indiceComponente < componentesConexas.size()) {
			Set<Vertice> vS = componentesConexas.get(indiceComponente);
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(true);
			}			
		}
	}
	
	public String siguiente() throws NextStepNotExistsException {
		Logger.getLogger("ComponentesFuertementeConexas").info("Siguiente");

		if(this.indiceSiguientePaso < this.componentesConexas.size()) {
			deseleccionar(this.indiceSiguientePaso - 1);
			seleccionar(this.indiceSiguientePaso++);
		}
		else {
			throw new NextStepNotExistsException("No hay más pasos");
		}
		return "";
	}

	public String anterior() {
		Logger.getLogger("ComponentesFuertementeConexas").info("Anterior");

		if(this.indiceSiguientePaso - 1 >= 0) {
			deseleccionar(--this.indiceSiguientePaso);
			seleccionar(this.indiceSiguientePaso - 1);
			return "";
		}
		
		return "";
	}

	public void terminar() {
		for(Set<Vertice> vS : componentesConexas){
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(false); 
			}
		}
		this.indiceSiguientePaso = 0;
		Logger.getLogger(this.getClass().getName()).info("Algoritmo finalizado");
	}

	public String principio() {
		Logger.getLogger("ComponentesFuertementeConexas").info("Principio");
		
		while(--this.indiceSiguientePaso >= 0) {
			Set<Vertice> vS = this.componentesConexas.get(this.indiceSiguientePaso);
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(false); 
			}
		}
		
		this.indiceSiguientePaso = 0;
		return "";

	}

	public String fin() {
		Logger.getLogger("ComponentesFuertementeConexas").info("Fin");
		
		while(this.indiceSiguientePaso < this.componentesConexas.size()) {
			Set<Vertice> vS = this.componentesConexas.get(this.indiceSiguientePaso++);
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(true); //TODO No hay que seleccionar, hay que poner un color especifico
			}
		}
		return "";
	}

	public boolean cumpleCondicionesIniciales() {
		return true;
	}

	public String getCondicionesIniciales() {
		return "No tiene condiciones Iniciales";
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.componentesConexas.size());
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/componentes-fuertemente-conexas-pseudocode.txt");
	}

	public String getTitulo() {
		return "Obtención de Componentes Fuertemente Conexas";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/componentes-fuertemente-conexas-info.html");
	}

	public Boolean isSourceDest() {
		return Boolean.FALSE;
	}

	public void setSource(Vertice v) {
		// TODO Auto-generated method stub
		
	}

	public void setDest(Vertice v) {
		// TODO Auto-generated method stub
		
	}

	public Boolean isCorrect(Resultado r) {
		Logger.getLogger(this.getClass().getName()).info("Siguiente Evaluación");
		List<Vertice> res = r.getVertices();
		Set<Vertice> ver = new HashSet<Vertice>(res);
		if(componentesConexas.contains(ver)){
			for(Vertice v2 : ver){	
				v2.select(true);
			}
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public Selectable getCurrentItem() {
		return null;
	}

	public boolean needMatrix() {
		// TODO Auto-generated method stub
		return false;
	}

	public TableModel getMatrixData() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] getMatrixColumns() {
		// TODO Auto-generated method stub
		return null;
	}

}
