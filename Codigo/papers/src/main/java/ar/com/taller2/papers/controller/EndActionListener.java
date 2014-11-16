package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jgrapht.alg.VertexCovers;

import ar.com.taller2.papers.model.Vertice;

public class EndActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public EndActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		Vertice vCorrecto;
		while (app.getModelo().hasNextStepAlgorithm()) {
			vCorrecto = app.getModelo().nextStepAlgorithm();
			vCorrecto.select(true);
			app.getVista().agregarASalida(vCorrecto.toString());
		}
		app.getVista().actualizar();
	}

}