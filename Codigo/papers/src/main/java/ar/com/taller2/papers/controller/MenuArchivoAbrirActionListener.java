package ar.com.taller2.papers.controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import ar.com.taller2.papers.libraries.FileManager;

public class MenuArchivoAbrirActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public MenuArchivoAbrirActionListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void actionPerformed(ActionEvent e) {
		
		Frame yourJFrame = new Frame();
		FileDialog fd = new FileDialog(yourJFrame , "Abrir...", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setVisible(true);
		String directory = fd.getDirectory();
		String filename = fd.getFile();
		
		if (filename == null || directory == null)
			System.out.println("You cancelled the choice");
		else {		
			if (!filename.contains(".gpht")) {
				System.out.println("ERROR!");				
			}
			else {
				FileManager f = new FileManager(app);
				f.open(directory + filename);
				app.getVista().desbloquearMenuGuardar();
			}
		}
		
	}
}
