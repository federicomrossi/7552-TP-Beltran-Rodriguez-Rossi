package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		Vertice v;
		v = app.getModelo().nextStepAlgorithm();
		if ( !app.getTutor().esModoAprendizaje() ) {
			// leer respuesta dada por usuario
			
			// comparar respuesta del usuario con la real
			while (!app.getTutor().esIgual(v)) {
				//Mostrar msj de equivocacion
			}
		}
		v.select(true);
		app.getVista().actualizar();
	}

}
