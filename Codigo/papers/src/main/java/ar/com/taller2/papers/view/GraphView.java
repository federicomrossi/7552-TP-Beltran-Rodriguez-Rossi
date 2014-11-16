package ar.com.taller2.papers.view;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import org.jgrapht.graph.DefaultEdge;

import ar.com.taller2.papers.adapters.JGraphXAdapter;
import ar.com.taller2.papers.model.Vertice;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;

public class GraphView extends mxGraphComponent{

	private JGraphXAdapter<Vertice, DefaultEdge> graph ;
	
	public GraphView(JGraphXAdapter<Vertice, DefaultEdge> graph ) {
		super(graph);
		this.graph = graph;
		
		// Propiedades
		this.graph.setDisconnectOnMove(false);
        this.graph.setAllowDanglingEdges(false);
        this.graph.setCellsSelectable(true);
        
        // Temp?
        this.ordernarVertices();
	}
	
	public void ordernarVertices() {

        this.graph.getModel().beginUpdate();
        double x = 50, y = 50;
        for (mxCell cell : this.graph.getVertexToCellMap().values()) {
        	this.graph.getModel().setGeometry(cell, new mxGeometry(x, y, 50, 50));
        	this.graph.getModel().setStyle(cell, "shape=ellipse;fillColor=green");
            x += 150;
            if (x > 400) {
                x = 50;
                y += 150;
            }
        }
        //https://www.youtube.com/watch?v=CNMCNvoEyRI
		Map<String, Object> edge = new HashMap<String, Object>();
//	    edge.put(mxConstants.STYLE_ROUNDED, true);
//	    edge.put(mxConstants.STYLE_ORTHOGONAL, false);
	    edge.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ENTITY_RELATION);
	    edge.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CURVE);
	    edge.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
//	    edge.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
//	    edge.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_RIGHT);
	    edge.put(mxConstants.STYLE_STROKECOLOR, "#000000"); // default is #6482B9
	    edge.put(mxConstants.STYLE_FONTCOLOR, "#446299");

	    mxStylesheet edgeStyle = new mxStylesheet();
	    edgeStyle.setDefaultEdgeStyle(edge);
	    graph.setStylesheet(edgeStyle);
	    
        this.graph.getModel().endUpdate();
	}
	
	public void actualizar(){
		this.graph.getModel().beginUpdate();
        for (Entry<Vertice,mxCell> cell : this.graph.getVertexToCellMap().entrySet()) {
        	this.graph.getModel().setStyle(cell.getValue(),cell.getKey().isSelected() ? "shape=ellipse;fillColor=blue": "shape=ellipse;fillColor=green" );
        }
        this.graph.getModel().endUpdate();
        this.graph.refresh();
	}

	public JGraphXAdapter<Vertice, DefaultEdge> getGraphAdapter() {
		return graph;
	}
}
