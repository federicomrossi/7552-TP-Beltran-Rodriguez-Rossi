package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class MenuEditarNuevoVerticeActionListener implements ActionListener{

	AprendiendoGrafos app;
	private static int i = 1;
	
	public MenuEditarNuevoVerticeActionListener(AprendiendoGrafos app){
		this.app = app;
	}

	public void actionPerformed(ActionEvent e) {
		app.getModelo().agregarVertice("Nuevo" + i++);
		Logger.getLogger(this.getClass().getName()).info("Agregué un vértice");
		app.getVista().actualizar();
		app.getVista().rerenderGrafo();
	}
	
}
