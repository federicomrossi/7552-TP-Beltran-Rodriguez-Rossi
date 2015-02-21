package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuArchivoAbrirActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public MenuArchivoAbrirActionListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void actionPerformed(ActionEvent e) {
		
		app.getVista().getFileManager().open();
		app.getVista().desbloquearMenuGuardar();
		
	}
}
