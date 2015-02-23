package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import org.jgrapht.graph.ListenableDirectedWeightedGraph;

import ar.com.taller2.papers.exceptions.CondicionInicialExcepcion;
import ar.com.taller2.papers.model.TIPO_GRAFO;
import ar.com.taller2.papers.model.graphs.CerraduraTransitiva;

public class PlayActionListener implements ActionListener {

	AprendiendoGrafos app;
	private boolean on = false;
	
	public PlayActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		/*if ( app.getTutor().esModoEvaluacion() ) {
			// Agregar listener al grafo para esperar respuesta del usuario 
			app.getVista().addAdapterVertexListener();
		    Logger.getLogger(this.getClass().getName()).info("Modo Evaluacion: Agregué el vertex listener");
		}
		else {
		    app.getVista().removeAdapterVertexListener();
			Logger.getLogger(this.getClass().getName()).info("Modo Aprendizaje: Ejecuto el algoritmo normalmente");
		}*/
		
		try {
			if (on) {
				app.getModelo().stopAlgorithm();
				if(app.getTutor().esModoEvaluacion()){
					app.getVista().removeAdapterVertexListener();
					Logger.getLogger(this.getClass().getName()).info("Modo Aprendizaje: Ejecuto el algoritmo normalmente");
					app.getVista().activarSourceDestListeners(true);
				}
				on = false;
				app.getVista().bloquearToolbar();
				app.getVista().desbloquearPanel();
				if(app.getModelo().getTipo().equals(TIPO_GRAFO.ORIENTADO)){
					app.getVista().bloquearOrientado();
				}else{
					app.getVista().bloquearNoOrientado();
				}
				app.getVista().borrarSalida();
			}
			else {
				if(app.getModelo().getAlgorithm() instanceof CerraduraTransitiva){
					((CerraduraTransitiva) app.getModelo().getAlgorithm()).setGraph((ListenableDirectedWeightedGraph)app.getModelo().getGraph());
				}
				app.getModelo().startAlgorithm();
				on = true;
				if(app.getTutor().esModoEvaluacion()){
					app.getVista().activarSourceDestListeners(false);
					app.getVista().addAdapterVertexListener();
				    Logger.getLogger(this.getClass().getName()).info("Modo Evaluacion: Agregué el vertex listener");
				}
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
