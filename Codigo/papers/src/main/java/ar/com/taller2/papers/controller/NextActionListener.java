package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Vertice;

public class NextActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public NextActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		Vertice vCorrecto, vRespuesta;
		try {
			if ( app.getTutor().esModoEvaluacion() ) {
				if (!app.getModelo().nextStepEvaluacion()) {
					//Mostrar msj de equivocacion
					Logger.getLogger(this.getClass().getName()).info("Elegi otro man!");
					app.getModelo().previousStepAlgorithm();
					app.getVista().mostrarMensajeEquivocacion("El vértice seleccionado no es el correcto");
					return;
				}
			}
			else {
				app.getModelo().nextStepAlgorithm();
				app.getVista().setPseudocodeCurrent(app.getModelo().getAlgorithm().getCurrentItem());
				//app.getVista().agregarASalida(vCorrecto.toString());
			}
			app.getVista().actualizar();
		} catch (NextStepNotExistsException e1) {
			app.getVista().mostrarMensajeEquivocacion(e1.getMessage());
		}
	}

}
