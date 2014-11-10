package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuArchivoNuevoGrafoNoOrientadoActionListener implements ActionListener {
	
	AprendiendoGrafos app;
	
	public MenuArchivoNuevoGrafoNoOrientadoActionListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void actionPerformed(ActionEvent e) {
		this.app.getModelo().nuevoGrafoNoDirigido();
		this.app.getVista().setGraph(app.getModelo().getGraph());
		this.app.getVista().actualizar();
	}

}
