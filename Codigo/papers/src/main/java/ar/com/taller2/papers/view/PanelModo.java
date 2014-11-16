package ar.com.taller2.papers.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemListener;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import ar.com.taller2.papers.Main;

public class PanelModo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7355328580258119324L;

	JLabel lblModoDeEjecucion = new JLabel("Modo de ejecución:");
    JRadioButton radioAprendizaje = new JRadioButton("Aprendizaje");
    JRadioButton radioEvaluacion = new JRadioButton("Autoevaluación");
    final ButtonGroup groupModoEjecucion = new ButtonGroup();
	
    private void setLookAndFeel() {
    	
    	this.setOpaque(false);
   
		radioAprendizaje.setContentAreaFilled(false);
		radioAprendizaje.setIcon(new ImageIcon(Main.class.getResource("/images/Unselected.png")));
		radioAprendizaje.setSelectedIcon(new ImageIcon(Main.class.getResource("/images/Selected.png")));
		radioAprendizaje.setDisabledIcon(new ImageIcon(Main.class.getResource("/images/Disabled.png")));
		radioAprendizaje.setPressedIcon(new ImageIcon(Main.class.getResource("/images/Selected.png")));

		radioEvaluacion.setContentAreaFilled(false);
		radioEvaluacion.setIcon(new ImageIcon(Main.class.getResource("/images/Unselected.png")));
		radioEvaluacion.setSelectedIcon(new ImageIcon(Main.class.getResource("/images/Selected.png")));
		radioEvaluacion.setDisabledIcon(new ImageIcon(Main.class.getResource("/images/Disabled.png")));
		radioEvaluacion.setPressedIcon(new ImageIcon(Main.class.getResource("/images/Selected.png")));
    }
	
	public PanelModo(){
		lblModoDeEjecucion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModoDeEjecucion.setBorder(new EmptyBorder(0, 0, 10, 0) );
		this.add(lblModoDeEjecucion);
				
		
		radioAprendizaje.setSelected(true);
		this.add(radioAprendizaje);
		this.add(radioEvaluacion);
		
		
		groupModoEjecucion.add(radioAprendizaje);
		groupModoEjecucion.add(radioEvaluacion);
		
		bloquearTodo();
		
		setLookAndFeel();
	}
	
	public void bloquearTodo() {
		radioAprendizaje.setEnabled(false);
		radioEvaluacion.setEnabled(false);
	}
	
	public void desbloquearTodo() {
		radioAprendizaje.setEnabled(true);
		radioEvaluacion.setEnabled(true);
	}
	
	public void addAprendizajeItemListener(ItemListener l){
		radioAprendizaje.addItemListener(l);
	}
	
	public void addEvaluacionItemListener(ItemListener l){
		radioEvaluacion.addItemListener(l);
	}
	
	public void removeAprendizajeItemListener(ItemListener l){
		radioAprendizaje.removeItemListener(l);
	}
	
	public void removeEvaluacionItemListener(ItemListener l){
		radioEvaluacion.removeItemListener(l);
	}
	
}
