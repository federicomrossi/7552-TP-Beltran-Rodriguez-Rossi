package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public EndActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		app.getModelo().endAlgorithm();
		app.getVista().actualizar();
	}

}