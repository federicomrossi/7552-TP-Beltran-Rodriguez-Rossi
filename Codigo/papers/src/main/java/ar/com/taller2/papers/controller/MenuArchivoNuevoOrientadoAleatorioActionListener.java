package ar.com.taller2.papers.controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import com.mxgraph.swing.util.mxGraphActions;

import ar.com.taller2.papers.AleatoryDialog;
import ar.com.taller2.papers.controller.jgraphx.ChangeWeightListener;
import ar.com.taller2.papers.controller.jgraphx.MoveCellListener;
import ar.com.taller2.papers.controller.jgraphx.NewEdgeListener;
import ar.com.taller2.papers.controller.jgraphx.NewVertexListener;

public class MenuArchivoNuevoOrientadoAleatorioActionListener implements
		ActionListener {

AprendiendoGrafos app;
	
	public MenuArchivoNuevoOrientadoAleatorioActionListener(AprendiendoGrafos app){
		this.app=app;
	}

	public void actionPerformed(ActionEvent e) {
		AleatoryDialog dialog = new AleatoryDialog((Frame)SwingUtilities.windowForComponent(app.getVista()));
		int vertices = Integer.parseInt(dialog.getCantVertices());
		int aristas = Integer.parseInt(dialog.getCantAristas());
		this.app.getModelo().nuevoGrafoDirigidoAleatorio(vertices, aristas);
		this.app.getVista().setGraph(app.getModelo().getGraph(),true);
		this.app.getVista().addNewEdgeListener(new NewEdgeListener(app));
		this.app.getVista().addChangeWeightListener(new ChangeWeightListener(app));
		this.app.getVista().addMoveCellListener(new MoveCellListener(app));
		this.app.getVista().addNewVertexListener(new NewVertexListener(app));
		this.app.getVista().installKeyboardListener();
		this.app.getVista().actualizar();
		this.app.getVista().desbloquearPanel();
		app.getVista().bloquearOrientado();
		app.getVista().desbloquearMenuGuardar();
	}

}
