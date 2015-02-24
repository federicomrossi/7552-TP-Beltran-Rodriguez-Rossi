package ar.com.taller2.papers.adapters;

import java.util.HashMap;
import java.util.logging.Logger;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.event.GraphEdgeChangeEvent;
import org.jgrapht.event.GraphListener;
import org.jgrapht.event.GraphVertexChangeEvent;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.controller.jgraphx.GraphViewListener;
import ar.com.taller2.papers.controller.jgraphx.RemovedCellListener;
import ar.com.taller2.papers.controller.jgraphx.SourceDestSelectionListener;
import ar.com.taller2.papers.controller.jgraphx.SourceSelectionListener;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.util.mxEvent;
import com.mxgraph.view.mxGraph;

public class JGraphXAdapter<V,E> extends mxGraph implements GraphListener<V, E> {
	private ListenableGraph<V, E> graphT;

    private HashMap<V, mxICell>  vertexToCellMap     = new HashMap<V, mxICell>();

    private HashMap<E, mxICell>  edgeToCellMap       = new HashMap<E, mxICell>();

    private HashMap<mxICell, V>  cellToVertexMap     = new HashMap<mxICell, V>();

    private HashMap<mxICell, E>  cellToEdgeMap       = new HashMap<mxICell, E>();
    
    private GraphViewListener graphListener = null;
    private SourceDestSelectionListener sourceDestSelListener = null;
    private SourceSelectionListener sourceSelListener = null;

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

    public HashMap<V, mxICell> getVertexToCellMap()
    {
        return vertexToCellMap;
}

    public HashMap<E, mxICell> getEdgeToCellMap()
    {
        return edgeToCellMap;
    }

    public HashMap<mxICell, E> getCellToEdgeMap()
    {
        return cellToEdgeMap;
    }

    public HashMap<mxICell, V> getCellToVertexMap()
{
    return cellToVertexMap;
    }

    /*
     * GRAPH LISTENER
     */

    public void vertexAdded(GraphVertexChangeEvent<V> e) {
        addJGraphTVertex(e.getVertex());
        Logger.getLogger(this.getClass().getName()).info("Evento de agregado de vertice");
    }

    public void vertexRemoved(GraphVertexChangeEvent<V> e) {
        mxICell cell = vertexToCellMap.remove(e.getVertex());
        removeCells(new Object[] { cell } );
        Logger.getLogger(this.getClass().getName()).info("Evento de quitado de vertice");
    }

    public void edgeAdded(GraphEdgeChangeEvent<V, E> e) {
        Logger.getLogger(this.getClass().getName()).info("Evento de agregado de flecha");
        addJGraphTEdge(e.getEdge());
    }

    public void edgeRemoved(GraphEdgeChangeEvent<V, E> e) {
        mxICell cell = edgeToCellMap.remove(e.getEdge());
        Logger.getLogger(this.getClass().getName()).info("Evento de quitado de flecha");
        removeCells(new Object[] { cell } );
    }
    
    public void addVertexListener(AprendiendoGrafos app) {
    	this.graphListener = new GraphViewListener(app);
    	this.getSelectionModel().addListener(mxEvent.CHANGE, this.graphListener);
	}
    
    public void removeVertexListener() {
    	if (this.graphListener != null) {
	    	this.getSelectionModel().removeListener(this.graphListener, mxEvent.CHANGE);
	    	this.graphListener = null;
    	}
	}
    
    public void addSourceDestSelectionListener(AprendiendoGrafos app) {
    	if(sourceDestSelListener == null){
    		this.sourceDestSelListener = new SourceDestSelectionListener(app);
    		this.getSelectionModel().addListener(mxEvent.CHANGE, this.sourceDestSelListener);
    	}
	}
    
    
    public void addSourceSelectionListener(AprendiendoGrafos app) {
    	if(sourceSelListener == null){
    		this.sourceSelListener = new SourceSelectionListener(app);
    		this.getSelectionModel().addListener(mxEvent.CHANGE, this.sourceSelListener);
    	}
	}
    
    public void removeSourceDestSelectionListener() {
    	if (this.sourceDestSelListener != null) {
	    	this.getSelectionModel().removeListener(this.sourceDestSelListener, mxEvent.CHANGE);
	    	this.sourceDestSelListener = null;
    	}
	}
    
    public void removeSourceSelectionListener() {
    	if (this.sourceSelListener != null) {
	    	this.getSelectionModel().removeListener(this.sourceSelListener, mxEvent.CHANGE);
	    	this.sourceSelListener = null;
    	}
	}
    
    public void addCellRemovedListener(AprendiendoGrafos app){
    	this.addListener(mxEvent.CELLS_REMOVED, new RemovedCellListener(app));
    }
    
    
    public void activarSourceDestListeners(boolean b){
    	if(sourceDestSelListener != null){
    		sourceDestSelListener.activar(b);
    	}
    	if(sourceSelListener != null){
    		sourceSelListener.activar(b);
    	}
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
    
    public ListenableGraph<V, E> getJGraphT(){
    	return this.graphT;
    }

    
	@Override
    public boolean isCellEditable(Object cell){
		if(cell instanceof mxCell){
			mxCell cell2 = (mxCell) cell;
			if(cell2.isEdge() && cellsEditable){
				return true;
			}
		}
		return false;
	}
    
}
