package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.com.taller2.papers.controller.jgraphx.ChangeWeightListener;
import ar.com.taller2.papers.controller.jgraphx.MoveCellListener;
import ar.com.taller2.papers.controller.jgraphx.NewEdgeListener;

public class MenuArchivoNuevoNoOrientadoAleatorioActionListener implements
		ActionListener {

AprendiendoGrafos app;
	
	public MenuArchivoNuevoNoOrientadoAleatorioActionListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void actionPerformed(ActionEvent e) {
		int vertices = 5;
		int aristas = 10;
		this.app.getModelo().nuevoGrafoNoDirigidoAleatorio(vertices, aristas);
		this.app.getVista().setGraph(app.getModelo().getGraph());
		this.app.getVista().addNewEdgeListener(new NewEdgeListener(app));
		this.app.getVista().addChangeWeightListener(new ChangeWeightListener(app));
		this.app.getVista().addMoveCellListener(new MoveCellListener(app));
		this.app.getVista().actualizar();
		this.app.getVista().desbloquearPanel();
	}

}
