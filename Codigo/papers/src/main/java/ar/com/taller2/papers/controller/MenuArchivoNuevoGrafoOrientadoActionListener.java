package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class MenuArchivoNuevoGrafoOrientadoActionListener implements ActionListener {
	
	AprendiendoGrafos app;
	
	public MenuArchivoNuevoGrafoOrientadoActionListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void actionPerformed(ActionEvent e) {
		this.app.getModelo().nuevoGrafoDirigido();
		this.app.getVista().setGraph(app.getModelo().getGraph());
		this.app.getVista().actualizar();
		this.app.getVista().desbloquearPanel();
	}

}
