package ar.com.taller2.papers.view;

import org.jgrapht.graph.DefaultEdge;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;

import ar.com.taller2.papers.adapters.JGraphXAdapter;
import ar.com.taller2.papers.model.GraphModel;

public class GraphView {

	private JGraphXAdapter<String, DefaultEdge> graph;
	
	public GraphView(GraphModel graph) {
		this.graph = new JGraphXAdapter<String, DefaultEdge>(graph.getGraph());
		
		// Propiedades
		this.graph.setDisconnectOnMove(false);
        this.graph.setAllowDanglingEdges(false);
        
        // Temp?
        this.ordernarVertices();
	}
	
	public void ordernarVertices() {

        this.graph.getModel().beginUpdate();
        double x = 50, y = 50;
        for (mxCell cell : this.graph.getVertexToCellMap().values()) {
        	this.graph.getModel().setGeometry(cell, new mxGeometry(x, y, 50, 50));
            x += 70;
            if (x > 200) {
                x = 50;
                y += 70;
            }
        }
        this.graph.getModel().endUpdate();
	}

	public JGraphXAdapter<String, DefaultEdge> getGraphAdapter() {
		return graph;
	}
}
