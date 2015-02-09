package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.logging.Logger;

import org.jgrapht.graph.ListenableDirectedWeightedGraph;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.LineCode;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Selectable;
import ar.com.taller2.papers.model.Vertice;

public class Dijkstra extends GraphAlgorithm {

	private ListenableDirectedWeightedGraph<Vertice, Arista> graph;
	private Vertice inicio;
	private Vertice fin;
	private int indiceSiguientePaso;
	private List<Arista> camino = new ArrayList<Arista>();
	private List<Selectable> items = new ArrayList<Selectable>();
	
	private List<String> recorrido;
	private Map<Vertice,Double> dist;
	private Map<Vertice,Vertice> pred;

	
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
	
	public Dijkstra(ListenableDirectedWeightedGraph<Vertice, Arista> graph){
		this.graph = graph;
	}
	
	public void setSource(Vertice v){
		this.inicio=v;
	}
	
	public void setDest(Vertice v){
		this.fin=v;
	}
	
	public void iniciar() {
		Logger.getLogger("Dijkstra").info("Inicie el algoritmo");
		recorrido = new ArrayList<String>();
		createItemList();
		dijsktra(inicio);
	}
	
	public String siguiente() throws NextStepNotExistsException {
		Logger.getLogger("Dijkstra").info("Siguiente");
		if (this.indiceSiguientePaso < this.recorrido.size()) {
			return recorrido.get(indiceSiguientePaso++);
		}
		else {
			throw new NextStepNotExistsException("Algoritmo finalizado");
		}
	}

	public String anterior() {
		Logger.getLogger(getClass().getSimpleName()).info("Anterior");
		if(this.indiceSiguientePaso - 1 >= 0) {
			return recorrido.get(--indiceSiguientePaso);
		}
		return "";
	}
	
	public String principio() {
		Logger.getLogger(getClass().getSimpleName()).info("Principio");
		
		this.indiceSiguientePaso = 0;
		return recorrido.get(indiceSiguientePaso);
	}
	
	public String fin() {
		Logger.getLogger(getClass().getSimpleName()).info("Fin");
		this.indiceSiguientePaso = recorrido.size()-1;
		return recorrido.get(indiceSiguientePaso);
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.recorrido.size());
//		return (this.indiceSiguientePaso < this.camino.size());
	}

	public boolean cumpleCondicionesIniciales() {
		if(inicio == null){
			return false; 
		}
		Set<Arista> edges = graph.edgeSet();
		for(Arista edge : edges){
			if(graph.getEdgeWeight(edge) < 0){
				return false;
			}
		}
		return true;
	}

	public String getCondicionesIniciales() {
		if(inicio == null){
			return "Debe seleccionar un vértice de Origen";
		}
		return "Las ponderaciones deben ser no negativas";
	}

	public void terminar() {
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
		/*List<Arista> res = r.getAristas();
		if(this.indiceSiguientePaso < this.camino.size()) {
			Arista v = this.camino.get(this.indiceSiguientePaso++);
			if(res.size() > 0){
				Arista ver = res.get(res.size()-1);
				if(v.equals(ver)){
					v.select(true);
					return Boolean.TRUE;
				}
			}
		}*/
		return Boolean.FALSE;
	}

	
	public Selectable getCurrentItem() {
		if (this.indiceSiguientePaso - 1 >= 0) 
			return this.items.get(this.indiceSiguientePaso - 1);
		return this.items.get(this.indiceSiguientePaso);
	}
	
	private void dijsktra(Vertice source){
		dist = new HashMap<Vertice,Double>();
		pred =  new HashMap<Vertice,Vertice>();
		Set<Vertice> vertices = graph.vertexSet();
		for(Vertice v: vertices){
			dist.put(v,Double.POSITIVE_INFINITY);
		}
		dist.put(source,0d);
		
		recorrido.add(printMatrixPath(source));
		
        PriorityQueue<Vertice> vertexQueue = new PriorityQueue<Vertice>();
      	vertexQueue.add(source);
      	source.setPriority(0d);
      	while (!vertexQueue.isEmpty()) {
      		Vertice u = vertexQueue.poll();
            // Visit each edge exiting u
            for (Arista e : graph.outgoingEdgesOf(u)){
                Vertice v = graph.getEdgeTarget(e);
                double weight = graph.getEdgeWeight(e);
                double distanceThroughU = dist.get(u) + weight;
                if (distanceThroughU < dist.get(v)) {
                	vertexQueue.remove(v);
                	dist.put(v,distanceThroughU);
                	pred.put(v,u);
                	v.setPriority(distanceThroughU);
                	vertexQueue.add(v);
                }
            }
            
            recorrido.add(printMatrixPath(source));
        }
	}
	
	private String printMatrixPath(Vertice source){
    	StringBuilder sB =  new StringBuilder();
    	sB.append("Source: ").append(source.toString()).append("\n");
    	sB.append("\t");
    	for(Vertice v : graph.vertexSet()){
    		sB.append(v.toString()).append("\t");
    	}
    	sB.append("\n");
    	sB.append("Distancia").append("\t");
    	for(Vertice v : graph.vertexSet()){
    		sB.append(dist.get(v)).append("\t");
    	}
    	sB.append("\n");
    	sB.append("Predecesor").append("\t");
    	for(Vertice v : graph.vertexSet()){
    		if(pred.get(v)!= null){
    			sB.append(pred.get(v)).append("\t");
    		}else{
    			sB.append("null").append("\t");
    		}
    	}
    	sB.append("\n");
    	return sB.toString();
	}
}
