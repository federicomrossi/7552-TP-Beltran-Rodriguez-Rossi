package ar.com.taller2.papers.controller;

import java.util.logging.Logger;

import ar.com.taller2.papers.model.Vertice;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

public class SourceDestSelectionListener implements mxIEventListener {

	AprendiendoGrafos app;
	Vertice source;
	Vertice dest;
	
	public SourceDestSelectionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void invoke(Object sender, mxEventObject evt) {
		mxCell cell = (mxCell) app.getVista().getGraph().getSelectionCell();
		if(cell != null && cell.isVertex()){
			if(source == null){
				source = app.getVista().getGraph().getCellToVertexMap().get(cell);
				app.getModelo().setSourceVertex(source);
				Logger.getLogger(this.getClass().getName()).info("Seleccione el Source: " + source.toString());
			}else {
				dest = app.getVista().getGraph().getCellToVertexMap().get(cell);
				Logger.getLogger(this.getClass().getName()).info("Seleccione el Destino: " + dest.toString());
				app.getModelo().setDestVertex(dest);
			}
		}else{
			Logger.getLogger(this.getClass().getName()).info("Seleccione, pero no era vertice!");
		}
	}

}
