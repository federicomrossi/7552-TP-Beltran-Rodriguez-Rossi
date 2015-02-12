package ar.com.taller2.papers.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Logger;

public class ModoEdicionItemListener implements ItemListener {

	AprendiendoGrafos app;
	
	public ModoEdicionItemListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED){
			Logger.getLogger(this.getClass().getName()).info("Modo Edicion");
			app.getVista().setModoEdicion(true);
		}else{
			app.getVista().setModoEdicion(false);
		}
	}

}
