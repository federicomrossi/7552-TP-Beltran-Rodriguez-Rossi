package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.com.taller2.papers.exceptions.CondicionInicialExcepcion;

public class PlayActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public PlayActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		if ( !app.getTutor().esModoAprendizaje() ) {
			// Agregar listener al grafo para esperar respuesta del usuario 
		}
		try {
			app.getModelo().startAlgorithm();
				
		}
		catch (CondicionInicialExcepcion ex) {
			// Se debería mostrar el msj de las condiciones iniciales en un aviso o algo del estilo
		}
		finally {
			
		}
	}

}
