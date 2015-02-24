package ar.com.taller2.papers.controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import ar.com.taller2.papers.AristaDialog;
import ar.com.taller2.papers.model.Vertice;

import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;

public class MenuEditarNuevaAristaActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public MenuEditarNuevaAristaActionListener(AprendiendoGrafos app){
		this.app = app;
	}

	public void actionPerformed(ActionEvent e) {
		//Vertice v = app.getModelo().agregarVertice();
		AristaDialog dialog = new AristaDialog((Frame)SwingUtilities.windowForComponent(app.getVista()));
		Vertice v = new Vertice(dialog.getOrigen(),false);
		Vertice v2 = new Vertice(dialog.getDestino(),false);
		mxICell source = app.getVista().getGraph().getVertexToCellMap().get(v);
		mxICell dest = app.getVista().getGraph().getVertexToCellMap().get(v2);
		Vertice v3 = app.getVista().getGraph().getCellToVertexMap().get(source);
		Vertice v4 = app.getVista().getGraph().getCellToVertexMap().get(dest);
		//app.getModelo().agregarArista(dialog.getOrigen(),dialog.getDestino(),dialog.getPeso());
		app.getModelo().agregarEdge(v3,v4,dialog.getPeso());
		//app.getVista().getGraph().removeCells(new mxCell[]{cell});
		mxParallelEdgeLayout second = new mxParallelEdgeLayout(app.getVista().getGraph());
		second.execute(app.getVista().getGraph().getDefaultParent());
		Logger.getLogger(this.getClass().getName()).info("Agregué ua arista");
		//app.getVista().actualizar();
		//app.getVista().runLayout();
	}
}
