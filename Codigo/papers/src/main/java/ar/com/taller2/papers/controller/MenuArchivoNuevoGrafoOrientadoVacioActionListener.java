package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import ar.com.taller2.papers.controller.jgraphx.ChangeWeightListener;
import ar.com.taller2.papers.controller.jgraphx.MoveCellListener;
import ar.com.taller2.papers.controller.jgraphx.NewEdgeListener;
import ar.com.taller2.papers.controller.jgraphx.NewVertexListener;
import ar.com.taller2.papers.model.graphs.PruebaAciclidad;

public class MenuArchivoNuevoGrafoOrientadoVacioActionListener implements ActionListener {
	
	AprendiendoGrafos app;
	
	public MenuArchivoNuevoGrafoOrientadoVacioActionListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void actionPerformed(ActionEvent e) {
		this.app.getModelo().nuevoGrafoDirigido();
		this.app.getVista().setGraph(app.getModelo().getGraph(),true);
		this.app.getVista().addNewEdgeListener(new NewEdgeListener(app));
		this.app.getVista().addChangeWeightListener(new ChangeWeightListener(app));
		this.app.getVista().addMoveCellListener(new MoveCellListener(app));
		this.app.getVista().addNewVertexListener(new NewVertexListener(app));
		this.app.getVista().installKeyboardListener();
		this.app.getVista().actualizar();
		this.app.getVista().desbloquearPanel();
		app.getVista().bloquearOrientado();
	}

}
