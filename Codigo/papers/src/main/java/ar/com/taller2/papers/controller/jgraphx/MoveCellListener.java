package ar.com.taller2.papers.controller.jgraphx;

import java.util.logging.Logger;

import ar.com.taller2.papers.controller.AprendiendoGrafos;

import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

public class MoveCellListener implements mxIEventListener {

	AprendiendoGrafos app;
	
	public MoveCellListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void invoke(Object sender, mxEventObject evt) {
		mxParallelEdgeLayout second = new mxParallelEdgeLayout(app.getVista().getGraph());
		second.execute(app.getVista().getGraph().getDefaultParent());
	}

}
