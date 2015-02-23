package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.table.TableModel;

import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.ListenableUndirectedGraph;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.LineCode;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Selectable;
import ar.com.taller2.papers.model.Vertice;

public class SpanningTree extends GraphAlgorithm {

	private ListenableUndirectedGraph<Vertice, Arista> graph;
	private int indiceSiguientePaso = 0;
	private Vertice inicio;
	private List<Arista> spanningTree= null;
	private Set<Arista> sp;
	private Vector<Arista> camino = new Vector<Arista>();
	private List<Selectable> items = new ArrayList<Selectable>();
	
	private void createItemList() {
		this.items.add(new LineCode(2));
		this.items.add(new LineCode(4));
		this.items.add(new LineCode(5));
		this.items.add(new LineCode(8));
		
		for (int i = 0; i < camino.size(); i++) {
			this.items.add(new LineCode(9));
			this.items.add(new LineCode(14));
			this.items.add(camino.get(i));
		}
		
		this.items.add(new LineCode(18));
	}
	
	public SpanningTree(ListenableUndirectedGraph<Vertice, Arista> graph, Vertice inicio){
		this.graph=graph;
		this.inicio=inicio;
	}
	
	public void iniciar() {
		KruskalMinimumSpanningTree<Vertice,Arista> sP = new KruskalMinimumSpanningTree<Vertice,Arista>(graph);
		sp=sP.getMinimumSpanningTreeEdgeSet();
		spanningTree = new ArrayList<Arista>(sp);
		Logger.getLogger(this.getClass().getSimpleName()).info("Inicié el algoritmo");
		createItemList();
	}
	
	public String siguiente() throws NextStepNotExistsException {
		Logger.getLogger(this.getClass().getSimpleName()).info("Siguiente");

		if(this.indiceSiguientePaso < this.spanningTree.size()) {
			Arista v = this.spanningTree.get(this.indiceSiguientePaso++);
			v.select(true);
		}
		return "";
		
		
//		if(this.indiceSiguientePaso < this.items.size()) {
//			Selectable v = this.items.get(this.indiceSiguientePaso);
//			v.select(true);
//			return getSalida(indiceSiguientePaso++ - 1);
//		}
//		else {
//			throw new NextStepNotExistsException("No hay paso siguiente");
//		}
	}
	
	public String getSalida(int indice){
		StringBuilder sB = new StringBuilder();
		for(int i=0;i<(indice+1) / 4;i++){
			sB.append(camino.get(i)).append("-");
		}
		return sB.toString();
	}

	public String anterior() {
		Logger.getLogger(this.getClass().getSimpleName()).info("Anterior");

		if(this.indiceSiguientePaso - 1 >= 0) {
			Arista v = this.spanningTree.get(--this.indiceSiguientePaso);
			v.select(false);
			return "";
		}
		
		return "";
	}

	

	public void terminar() {
		for(Arista v : spanningTree){
			v.select(false);
		}
		this.indiceSiguientePaso = 0;
		Logger.getLogger(this.getClass().getSimpleName()).info("Algoritmo finalizado");
	}

	public String principio() {
		Logger.getLogger(this.getClass().getSimpleName()).info("Principio");
		
		while(--this.indiceSiguientePaso >= 0) {
			Arista v = this.spanningTree.get(this.indiceSiguientePaso);
			v.select(false);
		}
		
		this.indiceSiguientePaso = 0;
		return "";
	}

	public String fin() {
		Logger.getLogger(this.getClass().getSimpleName()).info("Fin");
		
		while(this.indiceSiguientePaso < this.spanningTree.size()) {
			Arista v = this.spanningTree.get(this.indiceSiguientePaso++);
			v.select(true);
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
		return (this.indiceSiguientePaso < this.spanningTree.size());
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/arbol-expansion-coste-minimo-pseudocode.txt");
	}

	public String getTitulo() {
		return "Arból de expansión de coste mínimo";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/arbol-expansion-coste-minimo-info.html");
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
		List<Arista> res = r.getAristas();
		if(res.size() > 0){
			Arista ver = res.get(res.size()-1);
			if(sp.contains(ver)){
				ver.select(true);
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	public Selectable getCurrentItem() {
		// TODO Auto-generated method stub
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
