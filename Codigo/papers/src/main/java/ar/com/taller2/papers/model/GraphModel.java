package ar.com.taller2.papers.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;

import org.jgrapht.ListenableGraph;
import org.jgrapht.VertexFactory;
import org.jgrapht.WeightedGraph;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.StringNameProvider;
import org.jgrapht.generate.RandomGraphGenerator;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;

import ar.com.taller2.papers.exceptions.CondicionInicialExcepcion;
import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.libraries.VertexAttributeProvider;

public class GraphModel {
	
	private ListenableGraph<Vertice, Arista> graph;
	private int vertice_contador = 0;
	Executable algoritmo;
	Resultado resEvaluacion;
	
	Vertice source;
	Vertice dest;
	
	TIPO_GRAFO tipo;
	
	public GraphModel() {
		resEvaluacion = new Resultado();
	}
	
	public void nuevoGrafoDirigido() {
		this.vertice_contador=0;
		this.graph = new ListenableDirectedWeightedGraph<Vertice, Arista>(Arista.class);
		tipo = TIPO_GRAFO.ORIENTADO;
		//this.agregarVertices();
	}
	
	public void nuevoGrafoDirigidoAleatorio(int vertices, int aristas){
		this.vertice_contador=0;
		tipo = TIPO_GRAFO.ORIENTADO;
		this.graph = new ListenableDirectedWeightedGraph<Vertice, Arista>(Arista.class);
		RandomGraphGenerator<Vertice, Arista> gen = new RandomGraphGenerator<Vertice, Arista>(vertices, aristas);
		gen.generateGraph(this.graph, new VertexFactory<Vertice>() {
			
			public Vertice createVertex() {
				Vertice v = new Vertice("v" + (++vertice_contador), false);
				return v;
			}
		}, null);
			
	}
	
	public void nuevoGrafoNoDirigido() {
		this.vertice_contador=0;
		this.graph = new ListenableUndirectedWeightedGraph<Vertice, Arista>(Arista.class);
		tipo = TIPO_GRAFO.NO_ORIENTADO;
		
	}
	
	public void nuevoGrafoNoDirigidoAleatorio(int vertices, int aristas){
		this.vertice_contador=0;
		this.graph = new ListenableUndirectedWeightedGraph<Vertice, Arista>(Arista.class);
		RandomGraphGenerator<Vertice, Arista> gen = new RandomGraphGenerator<Vertice, Arista>(vertices, aristas);
		gen.generateGraph(this.graph, new VertexFactory<Vertice>() {
			
			public Vertice createVertex() {
				Vertice v = new Vertice("v" + (++vertice_contador), false);
				return v;
			}
		}, null);
		tipo = TIPO_GRAFO.NO_ORIENTADO;
	}
	
	public void guardarGrafo(String path, String filename) {
		
		DOTExporter<Vertice, Arista> export= new DOTExporter<Vertice, Arista>(new StringNameProvider<Vertice>(), null, null, null, new VertexAttributeProvider());

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
		for(Vertice v : graph.vertexSet()){
			v.select(false);
		}
		for(Arista v : graph.edgeSet()){
			v.select(false);
		}
		source=null;
		dest=null;
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
	
	public void removeVerticeFromResultado(Vertice v){
		resEvaluacion.removeVertice(v);
	}
	
	public void addAristaToResultado(Arista a){
		resEvaluacion.addArista(a);
	}
	
	public void removeAristaFromResultado(Arista a){
		resEvaluacion.removeArista(a);
	}
	
	public String previousStepAlgorithm(){
		return algoritmo.anterior();
	}
	
	public String initAlgorithm() {
		return algoritmo.principio();
	}
	
	public String endAlgorithm() {
		return algoritmo.fin();
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
	
	public Arista agregarArista(String source, String dest, Double peso){
		Vertice v1 = new Vertice(source, false);
		Vertice v2 = new Vertice(dest, false);
		Arista a = this.graph.addEdge(v1, v2);
		setWeight(a, peso);
		return a;
	}
	
	public Arista agregarEdge(String source, String dest){
		Vertice v1 = new Vertice(source, false);
		Vertice v2 = new Vertice(dest, false);
		return this.graph.addEdge(v1, v2);
	}
	
	public Arista agregarEdge(Vertice source, Vertice dest){
		return this.graph.addEdge(source, dest);
	}
	
	public Arista agregarEdge(Vertice source, Vertice dest, Double peso){
		Arista a = this.graph.addEdge(source, dest);
		setWeight(a, peso);
		return a;
	}
	
	public void removerVertice(Vertice v){
		this.graph.removeVertex(v);
	}
	
	public void removerArista(Arista v){
		this.graph.removeEdge(v);
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
		source =v;
		algoritmo.setSource(v);
	}
	
	public Vertice getSourceVertex(){
		return source;
	}
	
	public void setDestVertex(Vertice v){
		dest =v;
		algoritmo.setDest(v);
	}
	
	public Vertice getDestVertex(){
		return dest;
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

	public TIPO_GRAFO getTipo() {
		return tipo;
	}
}
