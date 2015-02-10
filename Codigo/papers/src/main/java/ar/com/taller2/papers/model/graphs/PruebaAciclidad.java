package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.swing.table.TableModel;

import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.cycle.JohnsonSimpleCycles;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Selectable;
import ar.com.taller2.papers.model.Vertice;

public class PruebaAciclidad extends GraphAlgorithm {
	
	ListenableGraph<Vertice, Arista> graph;
	List<List<Vertice>> ciclos = null;
	private int indiceSiguientePaso;

	
	private void deseleccionar(int indiceComponente) {
		if (indiceComponente >= 0 && indiceComponente < ciclos.size()) {
			List<Vertice> vS = ciclos.get(indiceComponente);
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(false);
			}			
		}
	}

	private void seleccionar(int indiceComponente) {
		if (indiceComponente >= 0 && indiceComponente < ciclos.size()) {
			List<Vertice> vS = ciclos.get(indiceComponente);
			Iterator<Vertice> it = vS.iterator();
			while(it.hasNext()){
				Vertice v = it.next();
				v.select(true);
			}			
		}
	}
	
	public PruebaAciclidad(ListenableGraph<Vertice, Arista> graph){
		this.graph = graph;
	}

	public String siguiente() throws NextStepNotExistsException {
		if (indiceSiguientePaso >= 0) {
			if(this.indiceSiguientePaso < this.ciclos.size()) {
				deseleccionar(this.indiceSiguientePaso - 1);
				seleccionar(this.indiceSiguientePaso++);
			}
			else {
				throw new NextStepNotExistsException("No hay más ciclos");
			}
		}
		else {
			throw new NextStepNotExistsException("No tiene ciclos");
		}
		return "";
	}

	public String anterior() {
		if(this.indiceSiguientePaso - 1 >= 0) {
			deseleccionar(--this.indiceSiguientePaso);
			seleccionar(this.indiceSiguientePaso - 1);
			return "";
		}
		
		return "";
	}

	public void iniciar() {
		
		JohnsonSimpleCycles<Vertice,Arista> detector = new JohnsonSimpleCycles<Vertice, Arista>((DirectedGraph<Vertice, Arista>) this.graph);
		Logger.getLogger(this.getClass().getName()).info("Cree el detector de ciclos");
		this.ciclos = detector.findSimpleCycles();
		if (this.ciclos != null) {
			this.indiceSiguientePaso = 0;
			Logger.getLogger(this.getClass().getName()).info("Tiene ciclos");
		}
		else {
			this.indiceSiguientePaso = -1;
		}
	}

	public void terminar() {
		for(List<Vertice> vS : ciclos){
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
		while(--this.indiceSiguientePaso >= 0) {
			List<Vertice> vS = this.ciclos.get(this.indiceSiguientePaso);
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
		while(this.indiceSiguientePaso < this.ciclos.size()) {
			List<Vertice> vS = this.ciclos.get(this.indiceSiguientePaso++);
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
		return "Este algoritmo no posee condiciones iniciales";
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.ciclos.size());
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/prueba-aciclidad-pseudocode.html");
	}

	public String getTitulo() {
		return "Prueba Aciclidad";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/prueba-aciclidad-info.html");
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
		List<HashSet<Vertice>> results = new ArrayList<HashSet<Vertice>>();
		for( List<Vertice> cic : ciclos){
			results.add(new HashSet<Vertice>(cic));
		}
		if(results.contains(ver)){
			for(Vertice v2 : res){	
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
