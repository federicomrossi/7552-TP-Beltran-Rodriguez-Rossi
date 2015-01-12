package ar.com.taller2.papers.controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuArchivoGuardarActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public MenuArchivoGuardarActionListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void actionPerformed(ActionEvent e) {
		
		Frame yourJFrame = new Frame();
		FileDialog fd = new FileDialog(yourJFrame , "Guardar archivo", FileDialog.SAVE);
		fd.setDirectory("C:\\");
		fd.setFile("Nuevo grafo.gpht");
		fd.setVisible(true);
		String directory = fd.getDirectory();
		String filename = fd.getFile();
		
		if (filename == null || directory == null)
			System.out.println("You cancelled the choice");
		else {		
			this.app.getModelo().guardarGrafo(directory, filename);
		}
		
		//this.app.getVista().setGraph(app.getModelo().getGraph());
	}
}
