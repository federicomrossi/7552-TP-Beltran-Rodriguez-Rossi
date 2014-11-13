package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Logger;

public class ModoAprendizajeItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public ModoAprendizajeItemListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void itemStateChanged(ItemEvent e) {
		if (!app.getTutor().esModoAprendizaje()) {
			app.getTutor().setModoAprendizaje();
			Logger.getLogger(this.getClass().getName()).info("Modo Aprendizaje");
		}
	}

}
