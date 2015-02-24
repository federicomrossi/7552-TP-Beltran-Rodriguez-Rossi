package ar.com.taller2.papers.controller.jgraphx;

import java.util.Map;
import java.util.logging.Logger;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.Vertice;

import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

public class NewEdgeListener implements mxIEventListener{

	private AprendiendoGrafos app;
	
	public NewEdgeListener(AprendiendoGrafos app){
		this.app = app;
	}
	
	public void invoke(Object sender, mxEventObject evt) {
		Logger.getLogger(getClass().getSimpleName()).info("Nueva Arista");
		Map<String, Object> map = evt.getProperties();
		mxCell cell = (mxCell) map.get("cell");
		mxICell source = cell.getSource();
		mxICell dest = cell.getTarget(); 
		Vertice v = app.getVista().getGraph().getCellToVertexMap().get(source);
		Vertice v2 = app.getVista().getGraph().getCellToVertexMap().get(dest);
		app.getModelo().agregarEdge(v,v2);
		app.getVista().getGraph().removeCells(new mxCell[]{cell});
		mxParallelEdgeLayout second = new mxParallelEdgeLayout(app.getVista().getGraph());
		second.execute(app.getVista().getGraph().getDefaultParent());
	}

}
