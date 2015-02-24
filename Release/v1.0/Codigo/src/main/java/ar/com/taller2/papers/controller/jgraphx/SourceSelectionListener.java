package ar.com.taller2.papers.controller.jgraphx;

import java.util.logging.Logger;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.Vertice;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

public class SourceSelectionListener implements mxIEventListener {

	AprendiendoGrafos app;
	Vertice source;
	boolean activo =true;
	public SourceSelectionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void invoke(Object sender, mxEventObject evt) {
		if(!app.getVista().isModoEdicion() && activo){
		mxCell cell = (mxCell) app.getVista().getGraph().getSelectionCell();
		if(cell != null && cell.isVertex()){
			source = app.getVista().getGraph().getCellToVertexMap().get(cell);
			app.getModelo().setSourceVertex(source);
			app.getVista().mostrarMensajeEquivocacion("Seleccionado el Origen.Puede iniciar el algoritmo.");
			Logger.getLogger(this.getClass().getName()).info("Seleccione el Source: " + source.toString());
		}else{
			Logger.getLogger(this.getClass().getName()).info("Seleccione, pero no era vertice!");
		}
		app.getVista().getGraph().getSelectionModel().clear();
		}
	}

	public void activar(boolean b) {
		activo=b;
		
	}

}
