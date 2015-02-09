package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public InitActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		String result = app.getModelo().initAlgorithm();
		app.getVista().setPseudocodeCurrent(app.getModelo().getAlgorithm().getCurrentItem());
		app.getVista().borrarSalida();
		app.getVista().agregarASalida(result);
		app.getVista().actualizar();
	}

}