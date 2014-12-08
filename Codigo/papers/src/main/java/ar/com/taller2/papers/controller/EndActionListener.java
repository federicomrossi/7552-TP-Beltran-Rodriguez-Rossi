package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Vertice;

public class EndActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public EndActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		Vertice vCorrecto;

		while (app.getModelo().hasNextStepAlgorithm()) {
			try {
				app.getModelo().nextStepAlgorithm();
				app.getVista().setPseudocodeCurrent(app.getModelo().getAlgorithm().getCurrentItem());
				//app.getVista().agregarASalida(vCorrecto.toString());
			} catch (NextStepNotExistsException e1) {
				app.getVista().mostrarMensajeEquivocacion(e1.getMessage());
			}
			
		}
		app.getVista().actualizar();
	}

}