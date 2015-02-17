package ar.com.taller2.papers.libraries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import ar.com.taller2.papers.controller.AprendiendoGrafos;

public class FileManager {
	
	private BufferedReader in;
	private String buffer;
	AprendiendoGrafos app;

	public FileManager(AprendiendoGrafos app){
		this.app=app;
	}

	public void open(String path) {
		try {
			in = new BufferedReader(new FileReader(path));
			parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parse() throws IOException {
		String tipoGrafo = getGraphType();
		if (tipoGrafo.contains("digraph")) {
			this.app.getVista().MenuArchivoNuevoGrafoOrientadoVacioTriggerAction();
			Logger.getLogger(this.getClass().getName()).info("Nuevo Grafo Orientado vacio");
		}
		else if (tipoGrafo.contains("graph")) {
			this.app.getVista().MenuArchivoNuevoGrafoNoOrientadoVacioTriggerAction();		
			Logger.getLogger(this.getClass().getName()).info("Nuevo Grafo No Orientado vacio");	
		}
		else {
			//Formato archivo incorrecto
			Logger.getLogger(this.getClass().getName()).info("Formato incorrecto");	
		}
		while (nextVertexExists()) {
			this.app.getModelo().agregarVertice(getNextVertex());
		}
		if (!buffer.isEmpty() && !buffer.contains("}")) {
			this.app.getModelo().agregarArista(getNextEdgeSource(), getNextEdgeDest(), getWeight());			
		}
		while (nextEdgeExists()) {
			this.app.getModelo().agregarArista(getNextEdgeSource(), getNextEdgeDest(), getWeight());			
		}
		this.app.getVista().rerenderGrafo();
		this.app.getVista().actualizar();
	}
	
	private String getGraphType() throws IOException {
		String tipoGrafo = in.readLine();
		if (tipoGrafo != "") {
			tipoGrafo = tipoGrafo.split(" ")[0];
		}
		return tipoGrafo;
	}
	
	private String getNextVertex() {
		return buffer.trim().replace(";", "");
	}
	
	private String getNextEdgeSource() {
		return buffer.trim().replace(";", "").split(" ")[0];
	}
	
	private String getNextEdgeDest() {
		return buffer.trim().replace(";", "").split(" ")[2];
	}
	
	private double getWeight() { 
		String weight = buffer.trim().replace(";", "").split('"' + "")[1];
		return Double.parseDouble(weight);
	}
	
	private boolean nextVertexExists() throws IOException {
		buffer = in.readLine();
		return !(buffer.isEmpty() || buffer.contains("--") || buffer.contains("->") || buffer.contains("}"));
	}
	
	private boolean nextEdgeExists() throws IOException {
		buffer = in.readLine();
		return !(buffer.isEmpty() || (!buffer.contains("--") && !buffer.contains("->")) || buffer.contains("}"));
	}
}
