package ar.com.taller2.papers.controller.jgraphx;

import java.util.logging.Logger;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Vertice;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

public class RemovedCellListener implements mxIEventListener {


	AprendiendoGrafos app;
	
	public RemovedCellListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void invoke(Object sender, mxEventObject evt) {
		mxCell cell = (mxCell) app.getVista().getGraph().getSelectionCell();
		if(cell != null){
			if(cell.isVertex()){
				Vertice v = app.getVista().getGraph().getCellToVertexMap().get(cell);
				app.getModelo().removerVertice(v);
				Logger.getLogger(this.getClass().getName()).info("Vertice Removido");
			}else{
				Arista a = app.getVista().getGraph().getCellToEdgeMap().get(cell);
				app.getModelo().removerArista(a);
				Logger.getLogger(this.getClass().getName()).info("Arista Removida");
			}
		}
	}

}
