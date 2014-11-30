package ar.com.taller2.papers.controller;

import java.util.Map;
import java.util.logging.Logger;

import org.jgrapht.graph.DefaultEdge;

import ar.com.taller2.papers.adapters.JGraphXAdapter;
import ar.com.taller2.papers.model.Vertice;

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
		Logger.getLogger("ASAS").info("akkaka");
		Map<String, Object> map = evt.getProperties();
		mxCell cell = (mxCell) map.get("cell");
		mxICell source = cell.getSource();
		mxICell dest = cell.getTarget(); 
		Vertice v = app.getVista().getGraph().getCellToVertexMap().get(source);
		Vertice v2 = app.getVista().getGraph().getCellToVertexMap().get(dest);
		app.getModelo().agregarEdge(v,v2);
	}

}
