package ar.com.taller2.papers.model;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import ar.com.taller2.papers.view.GraphView;

public class GraphModel {

	private ListenableGraph<String, DefaultEdge> graph = new ListenableDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
	
	public GraphModel() {
		
		// TEMP
		this.agregarVertices();
		// END TEMP
	}

	public ListenableGraph<String, DefaultEdge> getGraph() {
		return graph;
	}
	
	
	// TEMP
	public void agregarVertices() {
		
		this.graph.addVertex( "v1" );
		this.graph.addVertex( "v2" );
		this.graph.addVertex( "v3" );
		this.graph.addVertex( "v4" );
		this.graph.addVertex( "v5" );
		this.graph.addVertex( "v6" );
		this.graph.addVertex( "v7" );
		this.graph.addVertex( "v8" );
		
		this.graph.addEdge( "v1", "v2" );
		this.graph.addEdge( "v1", "v4" );
		this.graph.addEdge( "v2", "v6" );
		this.graph.addEdge( "v4", "v5" );
		this.graph.addEdge( "v5", "v6" );
        this.graph.addEdge( "v6", "v7" );
        this.graph.addEdge( "v6", "v8" );
        this.graph.addEdge( "v7", "v3" );
        this.graph.addEdge( "v8", "v3" );
	}
	// END TEMP
}
