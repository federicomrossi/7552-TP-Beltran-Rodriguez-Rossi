package ar.com.taller2.papers.controller.jgraphx;

import java.util.logging.Logger;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
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
		if(!app.getVista().isModoEdicion()){
		mxCell cell = (mxCell) app.getVista().getGraph().getSelectionCell();
		if(cell != null && cell.isVertex()){
			if(app.getModelo().getSourceVertex() == null){
				source = app.getVista().getGraph().getCellToVertexMap().get(cell);
				app.getModelo().setSourceVertex(source);
				app.getVista().mostrarMensajeEquivocacion("Seleccionado el Origen");
				Logger.getLogger(this.getClass().getName()).info("Seleccione el Source: " + source.toString());
			}else {
				dest = app.getVista().getGraph().getCellToVertexMap().get(cell);
				Logger.getLogger(this.getClass().getName()).info("Seleccione el Destino: " + dest.toString());
				app.getModelo().setDestVertex(dest);
				app.getVista().mostrarMensajeEquivocacion("Seleccionado el Destino. Puede iniciar el algoritmo.");
			}
		}else{
			Logger.getLogger(this.getClass().getName()).info("Seleccione, pero no era vertice!");
		}
		}
	}

}
