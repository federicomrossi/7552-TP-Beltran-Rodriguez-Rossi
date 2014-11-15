package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;

import ar.com.taller2.papers.model.Vertice;

public class NextActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public NextActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		Vertice vCorrecto, vRespuesta;
		vCorrecto = app.getModelo().nextStepAlgorithm();
		if ( app.getTutor().esModoEvaluacion() ) {
			// leer respuesta dada por usuario
			vRespuesta = app.getModelo().getVertex(app.getVista().getVerticeSeleccionado());
			// comparar respuesta del usuario con la real
			if (!app.getTutor().esIgual(vCorrecto, vRespuesta)) {
				//Mostrar msj de equivocacion
				Logger.getLogger(this.getClass().getName()).info("Elegi otro man!");
				app.getModelo().previousStepAlgorithm();
				return;
			}
		}
		vCorrecto.select(true);
		app.getVista().actualizar();
	}

}
