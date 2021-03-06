package ar.com.taller2.papers.libraries;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import com.mxgraph.model.mxICell;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.controller.jgraphx.ChangeWeightListener;
import ar.com.taller2.papers.controller.jgraphx.MoveCellListener;
import ar.com.taller2.papers.controller.jgraphx.NewEdgeListener;
import ar.com.taller2.papers.controller.jgraphx.NewVertexListener;
import ar.com.taller2.papers.model.Vertice;

public class FileManager {
	
	private BufferedReader in;
	private String buffer;
	private String directory = "";
	private String filename = "";
	AprendiendoGrafos app;

	public FileManager(AprendiendoGrafos app){
		this.app=app;
	}

	public void open() {
		Frame yourJFrame = new Frame();
		FileDialog fd = new FileDialog(yourJFrame , "Abrir...", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setVisible(true);
		String directory = fd.getDirectory();
		String filename = fd.getFile();
		
		if (filename == null || directory == null)
			System.out.println("You cancelled the choice");
		else {		
			if (!filename.contains(".gpht")) {
				System.out.println("ERROR!");				
			}
			else {
				try {
					in = new BufferedReader(new FileReader(directory + filename));
					parse();
					this.directory = directory;
					this.filename = filename;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void save() {
		if (!filename.isEmpty()) {
			this.app.getModelo().guardarGrafo(directory, filename);
		}
		else {
			saveAs();
		}
	}
	
	public void saveAs() {
		Frame yourJFrame = new Frame();
		FileDialog fd = new FileDialog(yourJFrame , "Guardar archivo...", FileDialog.SAVE);
		fd.setDirectory("C:\\");
		fd.setFile("Nuevo_Grafo.gpht");
		fd.setVisible(true);
		String directory = fd.getDirectory();
		String filename = fd.getFile();
		
		if (filename == null || directory == null)
			System.out.println("You cancelled the choice");
		else {
			this.directory = directory;
			this.filename = filename;
			this.app.getModelo().guardarGrafo(directory, filename);
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
			throw new IOException("Formato de archivo incorrecto");
		}
		while (nextVertexExists()) {
			this.app.getModelo().agregarVertice(getNextVertex());
			//this.app.getModelo().agregarVertice();
		}
		if (!buffer.isEmpty() && !buffer.contains("}")) {
			Vertice v = new Vertice(getNextEdgeSource(),false);
			Vertice v2 = new Vertice(getNextEdgeDest(),false);
			mxICell source = app.getVista().getGraph().getVertexToCellMap().get(v);
			mxICell dest = app.getVista().getGraph().getVertexToCellMap().get(v2);
			Vertice v3 = app.getVista().getGraph().getCellToVertexMap().get(source);
			Vertice v4 = app.getVista().getGraph().getCellToVertexMap().get(dest);
			app.getModelo().agregarEdge(v3,v4,getWeight());
			//this.app.getModelo().agregarArista(getNextEdgeSource(), getNextEdgeDest(), getWeight());			
		}
		while (nextEdgeExists()) {
			Vertice v = new Vertice(getNextEdgeSource(),false);
			Vertice v2 = new Vertice(getNextEdgeDest(),false);
			mxICell source = app.getVista().getGraph().getVertexToCellMap().get(v);
			mxICell dest = app.getVista().getGraph().getVertexToCellMap().get(v2);
			Vertice v3 = app.getVista().getGraph().getCellToVertexMap().get(source);
			Vertice v4 = app.getVista().getGraph().getCellToVertexMap().get(dest);
			app.getModelo().agregarEdge(v3,v4,getWeight());
			//this.app.getModelo().agregarArista(getNextEdgeSource(), getNextEdgeDest(), getWeight());			
		}
		this.app.getVista().rerenderGrafo();
		this.app.getVista().actualizar();
		this.app.getVista().desbloquearPanelEdicion();
		//app.getVista().bloquearOrientado();
		app.getVista().desbloquearMenuGuardar();
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
