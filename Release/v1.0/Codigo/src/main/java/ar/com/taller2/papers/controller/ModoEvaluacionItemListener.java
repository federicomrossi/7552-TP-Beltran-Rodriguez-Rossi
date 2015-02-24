package ar.com.taller2.papers.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Logger;

public class ModoEvaluacionItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public ModoEvaluacionItemListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			if (!app.getTutor().esModoEvaluacion()) {
				app.getTutor().setModoEvaluacion();
				Logger.getLogger(this.getClass().getName()).info("Modo Evaluacion");
			}
		}
	}
}
