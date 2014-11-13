package ar.com.taller2.papers.adapters;

import java.util.HashMap;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.event.GraphEdgeChangeEvent;
import org.jgrapht.event.GraphListener;
import org.jgrapht.event.GraphVertexChangeEvent;

import ar.com.taller2.papers.controller.GraphViewListener;
import ar.com.taller2.papers.model.Vertice;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;

public class JGraphXAdapter<V,E> extends mxGraph implements GraphListener<V, E> {
	private ListenableGraph<V, E> graphT;

    private HashMap<V, mxCell>  vertexToCellMap     = new HashMap<V, mxCell>();

    private HashMap<E, mxCell>  edgeToCellMap       = new HashMap<E, mxCell>();

    private HashMap<mxCell, V>  cellToVertexMap     = new HashMap<mxCell, V>();

    private HashMap<mxCell, E>  cellToEdgeMap       = new HashMap<mxCell, E>();
    
    private GraphViewListener graphListener = null;

    /*
     * CONSTRUCTOR
     */

    public JGraphXAdapter(final ListenableGraph<V, E> graphT)
    {
        super();
    this.graphT = graphT;
        graphT.addGraphListener(this);
        insertJGraphT(graphT);
    }

    /*
     * METHODS
     */

    public void addJGraphTVertex(V vertex)
    {

        getModel().beginUpdate();

        try
        {
            mxCell cell = new mxCell(vertex);
            cell.setVertex(true);
            cell.setId(null);
            addCell(cell, defaultParent);
            vertexToCellMap.put(vertex, cell);
            cellToVertexMap.put(cell, vertex);
        } 
    finally
    {
            getModel().endUpdate();
        }
    }

    public void addJGraphTEdge(E edge)
    {

        getModel().beginUpdate();

        try
        {
            V source = graphT.getEdgeSource(edge);
            V target = graphT.getEdgeTarget(edge);              
            mxCell cell = new mxCell(edge);
            cell.setEdge(true);
            cell.setId(null);
            cell.setGeometry(new mxGeometry());
            cell.getGeometry().setRelative(true);
            addEdge(cell, defaultParent, vertexToCellMap.get(source),  vertexToCellMap.get(target), null);
            edgeToCellMap.put(edge, cell);
            cellToEdgeMap.put(cell, edge);
        }
        finally
        {
            getModel().endUpdate();
        }
    }

    public HashMap<V, mxCell> getVertexToCellMap()
    {
        return vertexToCellMap;
}

    public HashMap<E, mxCell> getEdgeToCellMap()
    {
        return edgeToCellMap;
    }

    public HashMap<mxCell, E> getCellToEdgeMap()
    {
        return cellToEdgeMap;
    }

    public HashMap<mxCell, V> getCellToVertexMap()
{
    return cellToVertexMap;
    }

    /*
     * GRAPH LISTENER
     */

    public void vertexAdded(GraphVertexChangeEvent<V> e) {
        addJGraphTVertex(e.getVertex());
    }

    public void vertexRemoved(GraphVertexChangeEvent<V> e) {
        mxCell cell = vertexToCellMap.remove(e.getVertex());
        removeCells(new Object[] { cell } );
    }

    public void edgeAdded(GraphEdgeChangeEvent<V, E> e) {
        addJGraphTEdge(e.getEdge());
    }

    public void edgeRemoved(GraphEdgeChangeEvent<V, E> e) {
        mxCell cell = edgeToCellMap.remove(e.getEdge());
        removeCells(new Object[] { cell } );
    }
    
    public void addVertexListener() {
    	this.graphListener = new GraphViewListener(this);
    	this.getSelectionModel().addListener(mxEvent.CHANGE, this.graphListener);    
	}
    
    public void removeVertexListener() {
    	this.getSelectionModel().removeListener(this.graphListener, mxEvent.CHANGE);
    	this.graphListener = null;
	}
    
    /*
     * GETTERS
     */
    
    public String getVerticeSeleccionado() {
    	return this.graphListener.getSeleccion();
    }

    /*
     * PRIVATE METHODS
     */

    private void insertJGraphT(Graph<V, E> graphT) {        
        getModel().beginUpdate();
        try {
            for (V vertex : graphT.vertexSet())
                addJGraphTVertex(vertex);
            for (E edge : graphT.edgeSet())
                addJGraphTEdge(edge);
        } finally {
            getModel().endUpdate();
        }

    }

	
}
