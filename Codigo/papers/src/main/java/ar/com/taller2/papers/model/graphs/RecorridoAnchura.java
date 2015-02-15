package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.table.TableModel;

import org.jgrapht.ListenableGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Executable;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.LineCode;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Selectable;
import ar.com.taller2.papers.model.Vertice;

public class RecorridoAnchura extends GraphAlgorithm implements Executable {

	private ListenableGraph<Vertice, Arista> graph;
	private Vertice inicio;
	private int indiceSiguientePaso;
	private Vector<Vertice> camino = new Vector<Vertice>();
	private List<Selectable> items = new ArrayList<Selectable>();

	
	private void createItemList() {
		this.items.add(new LineCode(2));
		this.items.add(new LineCode(3));
		for (int i = 0; i < camino.size(); i++) {
			this.items.add(new LineCode(4));
			this.items.add(camino.get(i));
			this.items.add(new LineCode(6));
			this.items.add(new LineCode(7));
		}
	}
	
	
	public RecorridoAnchura(ListenableGraph<Vertice, Arista> graph, Vertice inicio){
		this.graph = graph;
		this.inicio = inicio;
	}
	
	public void iniciar() {
		camino = new Vector<Vertice>();
		BreadthFirstIterator<Vertice,Arista> dfit = new BreadthFirstIterator<Vertice,Arista>(this.graph, this.inicio);
		while (dfit.hasNext()) {
			this.camino.add(dfit.next());
		}
		this.indiceSiguientePaso = 0;
		Logger.getLogger("RecorridoAnchura").info("Inicie el algoritmo");
		createItemList();
	}
	
	public String siguiente() throws NextStepNotExistsException {
		Logger.getLogger("RecorridoAnchura").info("Siguiente");

		if(this.indiceSiguientePaso < this.items.size()) {
			Selectable v = this.items.get(this.indiceSiguientePaso);
			v.select(true);
			return getSalida(indiceSiguientePaso++);
		}else {
			throw new NextStepNotExistsException("Algoritmo finalizado");
		}
	}

	public String anterior() {
		Logger.getLogger("RecorridoAnchura").info("Anterior");

		if(this.indiceSiguientePaso - 1 >= 0) {
			Selectable v = this.items.get(--this.indiceSiguientePaso);
			v.select(false);
			return getSalida(indiceSiguientePaso);
		}
		return "";
	}
	
	public String principio() {
		Logger.getLogger("RecorridoAnchura").info("Principio");
				
		while(--this.indiceSiguientePaso >= 0) {
			Selectable v = this.items.get(this.indiceSiguientePaso);
			v.select(false);
		}

//		while(--this.indiceSiguientePaso >= 0) {
//			Vertice v = this.camino.get(this.indiceSiguientePaso);
//			v.select(false);
//		}
		this.indiceSiguientePaso = 0;
		return "";
	}

	public void terminar() {
		
		while(--this.indiceSiguientePaso >= 0) {
			Selectable v = this.items.get(this.indiceSiguientePaso);
			v.select(false);
		}
//		while(--this.indiceSiguientePaso >= 0) {
//			Vertice v = this.camino.get(this.indiceSiguientePaso);
//			v.select(false);
//		}
		this.indiceSiguientePaso = 0;
		Logger.getLogger(this.getClass().getName()).info("Algoritmo finalizado");
		
	}
	
	public String fin() {
		Logger.getLogger("RecorridoAnchura").info("Fin");
		
		while(this.indiceSiguientePaso < this.camino.size()) {
			Selectable v = this.items.get(this.indiceSiguientePaso++);
			v.select(true);
		}
//		while(this.indiceSiguientePaso < this.camino.size()) {
//			Vertice v = this.camino.get(this.indiceSiguientePaso++);
//			v.select(true);
//		}
		return "";
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.items.size());
//		return (this.indiceSiguientePaso < this.camino.size());
	}

	public boolean cumpleCondicionesIniciales() {
		return (inicio != null);
	}

	public String getCondicionesIniciales() {
		return "Debe seleccionar el vértice de origen";
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/recorrido-anchura-pseudocode.txt");
	}

	public String getTitulo() {
		return "Recorrido en Anchura";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/recorrido-anchura-info.html");
	}

	public Boolean isSourceDest() {
		return Boolean.FALSE;
	}

	public void setSource(Vertice v) {
		this.inicio = v;
	}

	public void setDest(Vertice v) {
		// TODO Auto-generated method stub
		
	}

	public Boolean isCorrect(Resultado r) {
		Logger.getLogger(this.getClass().getName()).info("Siguiente Evaluación");
		List<Vertice> res = r.getVertices();
		if(this.indiceSiguientePaso < this.camino.size()) {
			Vertice v = this.camino.get(this.indiceSiguientePaso++);
			if(res.size() > 0){
				Vertice ver = res.get(res.size()-1);
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


	public String getSalida(int indice){
		StringBuilder sB = new StringBuilder();
		for(int i=0;i<(indice+1) / 4;i++){
			sB.append(camino.get(i)).append("-");
		}
		return sB.toString();
	}
	
}
