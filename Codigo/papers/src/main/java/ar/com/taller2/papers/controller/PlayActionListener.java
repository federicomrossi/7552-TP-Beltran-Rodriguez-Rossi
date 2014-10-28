package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayActionListener implements ActionListener {

	AprendiendoGrafos app;
	
	public PlayActionListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	public void actionPerformed(ActionEvent e) {
		app.getModelo().initAlgorithm();
	}

}
