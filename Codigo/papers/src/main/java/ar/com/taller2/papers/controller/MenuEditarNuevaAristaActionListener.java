package ar.com.taller2.papers.controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import ar.com.taller2.papers.AristaDialog;

public class MenuEditarNuevaAristaActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public MenuEditarNuevaAristaActionListener(AprendiendoGrafos app){
		this.app = app;
	}

	public void actionPerformed(ActionEvent e) {
		//Vertice v = app.getModelo().agregarVertice();
		AristaDialog dialog = new AristaDialog((Frame)SwingUtilities.windowForComponent(app.getVista()));
		app.getModelo().agregarArista(dialog.getOrigen(),dialog.getDestino(),dialog.getPeso());
		Logger.getLogger(this.getClass().getName()).info("Agregué ua arista");
		app.getVista().actualizar();
		app.getVista().rerenderGrafo();
	}
}
