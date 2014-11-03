package ar.com.taller2.papers.controller;

import ar.com.taller2.papers.Main;
import ar.com.taller2.papers.model.GraphModel;

public class AprendiendoGrafos {
	Main vista;
	GraphModel modelo = new GraphModel();
	
	
	public AprendiendoGrafos(Main main){
		this.vista=main;
		vista.setGraph(modelo.getGraph());
		vista.addToolbarNextActionListener(new NextActionListener(this));
		vista.addToolbarPreviousActionListener(new PreviousActionListener(this));
		vista.addToolBarPlayActionListener(new PlayActionListener(this));
		vista.addToolbarInitActionListener(new InitActionListener(this));
		vista.addToolbarEndActionListener(new EndActionListener(this));
		vista.addAlgoritmosRecorridoProfundidadItemListener(new AlgoritmosRecorridoProfundidadItemListener(this));
		vista.addAlgoritmosRecorridoAnchuraItemListener(new AlgoritmosRecorridoAnchuraItemListener(this));
	}
	
	public Main getVista(){
		return vista;
	}
	
	public GraphModel getModelo(){
		return modelo;
	}
}
