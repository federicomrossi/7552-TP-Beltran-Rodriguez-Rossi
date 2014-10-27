package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreviousActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public PreviousActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		app.getModelo().previousStepAlgorithm();
		app.getVista().actualizar();
	}

}
