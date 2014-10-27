package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public NextActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		app.getModelo().nextStepAlgorithm();
		app.getVista().actualizar();
	}

}
