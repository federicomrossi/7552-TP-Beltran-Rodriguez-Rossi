package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import ar.com.taller2.papers.model.Vertice;

public class MenuEditarNuevoVerticeActionListener implements ActionListener{

	AprendiendoGrafos app;
	
	public MenuEditarNuevoVerticeActionListener(AprendiendoGrafos app){
		this.app = app;
	}

	public void actionPerformed(ActionEvent e) {
		Vertice v = app.getModelo().agregarVertice();
		Logger.getLogger(this.getClass().getName()).info("Agregué un vértice: " + v.toString());
		app.getVista().actualizar();
		app.getVista().rerenderGrafo();
	}
	
}
