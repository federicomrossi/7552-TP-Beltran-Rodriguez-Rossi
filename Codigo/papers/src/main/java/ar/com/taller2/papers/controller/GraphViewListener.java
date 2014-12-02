package ar.com.taller2.papers.controller;

import java.util.logging.Logger;

import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Vertice;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

public class GraphViewListener implements mxIEventListener{
	
	AprendiendoGrafos app;
	private Vertice vertice;
	private Arista arista;
	
	public GraphViewListener(AprendiendoGrafos app) {
		this.app=app;
	}

	public void invoke(Object sender, mxEventObject evt) {
		mxCell cell = (mxCell) app.getVista().getGraph().getSelectionCell();
		if(cell != null){
			if(cell.isVertex()){
				vertice = app.getVista().getGraph().getCellToVertexMap().get(cell);
				app.getModelo().addVerticeToResultado(vertice);
				Logger.getLogger(this.getClass().getName()).info("Seleccione el vertice: " + this.vertice);
			}else if(cell.isEdge()){
				arista = app.getVista().getGraph().getCellToEdgeMap().get(cell);
				app.getModelo().addAristaToResultado(arista);
				Logger.getLogger(this.getClass().getName()).info("Seleccione la arista: " + this.arista);
			}
		}
			
		
	}
	
	public String getSeleccion() {
		return this.vertice.toString();
	}
	
}
