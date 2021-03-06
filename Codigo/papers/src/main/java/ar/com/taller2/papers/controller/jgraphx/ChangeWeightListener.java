package ar.com.taller2.papers.controller.jgraphx;

import java.util.Map;
import java.util.logging.Logger;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Vertice;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

public class ChangeWeightListener implements mxIEventListener {

	private AprendiendoGrafos app;

	public ChangeWeightListener(AprendiendoGrafos app) {
		this.app = app;
	}

	public void invoke(Object sender, mxEventObject evt) {
		Logger.getLogger(getClass().getSimpleName()).info(
				"Cambiando el peso de la Arista");
		Map<String, Object> map = evt.getProperties();
		mxCell cell = (mxCell) map.get("cell");
		if (cell.isEdge()) {
			Logger.getLogger(getClass().getSimpleName()).info(
					cell.getValue().toString());
			Arista a = app.getVista().getGraph().getCellToEdgeMap().get(cell);
			app.getModelo().setWeight(a,
					Double.valueOf(cell.getValue().toString()));
		}else if(cell.isVertex()){
			Logger.getLogger(getClass().getSimpleName()).info(
					cell.getValue().toString());
			Vertice v = app.getVista().getGraph().getCellToVertexMap().get(cell);
			v.setName(cell.getValue().toString());
		}
	}

}
