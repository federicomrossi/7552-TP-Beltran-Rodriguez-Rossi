package ar.com.taller2.papers.controller.jgraphx;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.Vertice;

public class NewVertexListener implements MouseListener {

	private AprendiendoGrafos app;
	boolean isAlreadyOneClick;
	
	public NewVertexListener(AprendiendoGrafos app){
		this.app=app;
	}
	
	
	public void mouseClicked(MouseEvent e) {
		if(app.getVista().isModoEdicion()){
		if (isAlreadyOneClick) {
			Logger.getLogger(getClass().getSimpleName()).info("Nuevo Vertice");
	        isAlreadyOneClick = false;
	        Vertice v = app.getModelo().agregarVertice();
	        app.getVista().actualizar();
			app.getVista().rerenderGrafo();
	    } else {
	        isAlreadyOneClick = true;
	        Timer t = new Timer("doubleclickTimer", false);
	        t.schedule(new TimerTask() {

	            @Override
	            public void run() {
	                isAlreadyOneClick = false;
	            }
	        },  (Integer)Toolkit.getDefaultToolkit().getDesktopProperty("awt.multiClickInterval"));
	    }
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
