package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import ar.com.taller2.papers.exceptions.CondicionInicialExcepcion;

public class PlayActionListener implements ActionListener {

	AprendiendoGrafos app;
	private boolean on = false;
	
	public PlayActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		if ( app.getTutor().esModoEvaluacion() ) {
			// Agregar listener al grafo para esperar respuesta del usuario 
		    app.getVista().addAdapterVertexListener();
		    Logger.getLogger(this.getClass().getName()).info("Modo Evaluacion: Agregué el vertex listener");
		}
		else {
		    app.getVista().removeAdapterVertexListener();
			Logger.getLogger(this.getClass().getName()).info("Modo Aprendizaje: Ejecuto el algoritmo normalmente");
		}
		
		try {
			if (on) {
				app.getModelo().stopAlgorithm();
				on = false;
				app.getVista().bloquearToolbar();
				app.getVista().desbloquearPanel();
				app.getVista().borrarSalida();
			}
			else {
				app.getModelo().startAlgorithm();
				on = true;
				app.getVista().desbloquearToolbar();
				app.getVista().bloquearPanel();
			}
			app.getVista().actualizar();
		}
		catch (CondicionInicialExcepcion ex) {
			app.getVista().mostrarMensajeEquivocacion(ex.getMessage());
			Logger.getLogger(this.getClass().getName()).warning("No cumple las condiciones iniciales");
		}
		finally {
			
		}
	}

}
