package ar.com.taller2.papers.model;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;








import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.*;
import org.jgrapht.graph.ListenableDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;



import org.jgrapht.ListenableGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;








import ar.com.taller2.papers.exceptions.CondicionInicialExcepcion;
import ar.com.taller2.papers.exceptions.NextStepNotExistsException;

public class GraphModel {

	private ListenableGraph<Vertice, Arista> graph;
	private int vertice_contador = 0;
	Executable algoritmo;
	Resultado resEvaluacion;
	
	public GraphModel() {
		resEvaluacion = new Resultado();
	}
	
	public void nuevoGrafoDirigido() {
		this.graph = new ListenableDirectedWeightedGraph<Vertice, Arista>(Arista.class);
		this.vertice_contador=0;
		this.agregarVertices();
	}
	
	public void nuevoGrafoNoDirigido() {
		this.graph = new ListenableUndirectedWeightedGraph<Vertice, Arista>(Arista.class);
		this.vertice_contador=0;
	}
	
	public void guardarGrafo(String path, String filename) {
		
		DOTExporter<Vertice, Arista> export= new DOTExporter<Vertice, Arista>();

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(path + filename));
			export.export(out, this.graph);
		}
		catch(IOException e) {
			System.err.println("Class GraphModel - Error: no se pudo escribir el archivo. " + e.getMessage());
		}
	}

	public ListenableGraph<Vertice, Arista> getGraph() {
		return graph;
	}
	
	public void setAlgorithm(Executable algoritmo) {
		Logger.getLogger("GraphModel").info("Cambie de algoritmo");
		this.algoritmo = algoritmo;
	}
	
	public Executable getAlgorithm() {
		return this.algoritmo;
	}
	
	public Vertice getVertex(String name) {
		Iterator<Vertice> it = this.graph.vertexSet().iterator();
		while (it.hasNext()) {
			Vertice v = it.next();
			if (v.toString() == name) {
				return v;
			}
		}
		return null;
	}
	
	public void startAlgorithm() throws CondicionInicialExcepcion {
		if (algoritmo.cumpleCondicionesIniciales()) {
			resEvaluacion = new Resultado();
			algoritmo.iniciar();
		}
		else {
			throw new CondicionInicialExcepcion(algoritmo.getCondicionesIniciales());
		}
	}

	public void stopAlgorithm() {
		resEvaluacion = new Resultado();
		algoritmo.terminar();		
	}
	
	public String nextStepAlgorithm() throws NextStepNotExistsException {
		return algoritmo.siguiente();
	}
	
	public Boolean nextStepEvaluacion() throws NextStepNotExistsException{
		Boolean r = algoritmo.isCorrect(resEvaluacion);
		resEvaluacion = new Resultado();
		return r;
	}
	
	public void addVerticeToResultado(Vertice v){
		resEvaluacion.addVertice(v);
	}
	
	public void addAristaToResultado(Arista a){
		resEvaluacion.addArista(a);
	}
	
	public String previousStepAlgorithm(){
		return algoritmo.anterior();
	}
	
	public void initAlgorithm() {
		algoritmo.principio();
	}
	
	public void endAlgorithm() {
		algoritmo.fin();
	}
	
	public boolean hasNextStepAlgorithm() {
		return algoritmo.tieneSiguiente();
	}
	
	public Vertice agregarVertice(String nombre) {
		Vertice v = new Vertice(nombre, false);
		this.graph.addVertex(v);
		return v;
	}
	
	public Vertice agregarVertice() {
		Vertice v = new Vertice("v" + (++vertice_contador), false);
		this.graph.addVertex(v);
		return v;
	}
	
	public Arista agregarEdge(String source, String dest){
		Vertice v1 = new Vertice(source, false);
		Vertice v2 = new Vertice(dest, false);
		return this.graph.addEdge(v1, v2);
	}
	
	public Arista agregarEdge(Vertice source, Vertice dest){
		return this.graph.addEdge(source, dest);
	}
	
	@SuppressWarnings("unchecked")
	public void setWeight(Arista a,Double d){
		WeightedGraph<Vertice,Arista> g = (WeightedGraph<Vertice,Arista>) graph;
		g.setEdgeWeight(a, d);
	}
	
	public Boolean isSourceDestAlgorithm(){
		return (algoritmo != null && algoritmo.isSourceDest());
	}
	
	public void setSourceVertex(Vertice v){
		algoritmo.setSource(v);
	}
	
	public void setDestVertex(Vertice v){
		algoritmo.setDest(v);
	}
	
	
	// TEMP
	public void agregarVertices() {
		
		Vertice v1 = this.agregarVertice();
		Vertice v2 = this.agregarVertice();
		Vertice v3 = this.agregarVertice();
//		Vertice v4 = this.agregarVertice();
//		Vertice v5 = this.agregarVertice();
//		Vertice v6 = this.agregarVertice();
//		Vertice v7 = this.agregarVertice();
//		Vertice v8 = this.agregarVertice();
		
		this.graph.addEdge( v1, v2 );
		this.graph.addEdge( v2, v3);
		//this.graph.addEdge(v3, v1);
//		this.graph.addEdge( v1, v4);
//		this.graph.addEdge( v2, v6 );
//		this.graph.addEdge( v4, v5 );
//		this.graph.addEdge( v5, v6);
//        this.graph.addEdge( v6, v7 );
//        this.graph.addEdge( v6, v8 );
//        this.graph.addEdge( v7, v3 );
//        this.graph.addEdge( v8, v3 );
		
		//this.guardarGrafo("F:/", "grafo.dot");
		
		// Guardar grafo
//		Frame yourJFrame = new Frame();
//		FileDialog fd = new FileDialog(yourJFrame , "Guardar archivo", FileDialog.SAVE);
//		fd.setDirectory("C:\\");
//		fd.setFile("Nuevo grafo.gpht");
//		fd.setVisible(true);
//		String directory = fd.getDirectory();
//		String filename = fd.getFile();
//		
//		if (filename == null || directory == null)
//			System.out.println("You cancelled the choice");
//		else {
//			this.guardarGrafo(directory, filename);
//		}
		// Fin guardar grafo
		
	}
	// END TEMP
}
