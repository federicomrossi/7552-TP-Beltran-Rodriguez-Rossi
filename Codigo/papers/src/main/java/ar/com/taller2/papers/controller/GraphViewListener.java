package ar.com.taller2.papers.controller;

import java.util.logging.Logger;

import ar.com.taller2.papers.adapters.JGraphXAdapter;
import ar.com.taller2.papers.model.Vertice;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

public class GraphViewListener implements mxIEventListener{
	
	private JGraphXAdapter adapter;
	private String seleccion = "";
	
	public GraphViewListener(JGraphXAdapter adapter) {
		this.adapter = adapter;
	}

	public void invoke(Object sender, mxEventObject evt) {
		this.seleccion = ((Vertice)((mxCell)adapter.getSelectionCell()).getValue()).toString();
		Logger.getLogger(this.getClass().getName()).info("Seleccione el vertice: " + this.seleccion);
	}
	
	public String getSeleccion() {
		return this.seleccion;
	}
	
}
