package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.com.taller2.papers.controller.jgraphx.ChangeWeightListener;
import ar.com.taller2.papers.controller.jgraphx.MoveCellListener;
import ar.com.taller2.papers.controller.jgraphx.NewEdgeListener;
import ar.com.taller2.papers.controller.jgraphx.NewVertexListener;

public class MenuArchivoNuevoGrafoNoOrientadoVacioActionListener implements ActionListener {
	
	AprendiendoGrafos app;
	
	public MenuArchivoNuevoGrafoNoOrientadoVacioActionListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void actionPerformed(ActionEvent e) {
		this.app.getModelo().nuevoGrafoNoDirigido();
		this.app.getVista().setGraph(app.getModelo().getGraph(),false);
		this.app.getVista().addNewEdgeListener(new NewEdgeListener(app));
		this.app.getVista().addChangeWeightListener(new ChangeWeightListener(app));
		this.app.getVista().addMoveCellListener(new MoveCellListener(app));
		this.app.getVista().addNewVertexListener(new NewVertexListener(app));
		this.app.getVista().installKeyboardListener();
		this.app.getVista().actualizar();
		this.app.getVista().desbloquearPanel();
		app.getVista().bloquearNoOrientado();
		app.getVista().desbloquearMenuGuardar();
	}

}
