package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreviousActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public PreviousActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		String result = app.getModelo().previousStepAlgorithm();

		try {
			app.getVista().setPseudocodeCurrent(app.getModelo().getAlgorithm().getCurrentItem());
			
		}
		catch (Exception e1) { }
		
		app.getVista().borrarSalida();
		app.getVista().agregarASalida(result);
		app.getVista().actualizar();
	}

}
