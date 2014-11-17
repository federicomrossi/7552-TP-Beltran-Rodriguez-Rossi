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

	JLabel lblModoDeEjecucion = new JLabel("MODO DE EJECUCIÓN");
    JRadioButton radioAprendizaje = new JRadioButton("Aprendizaje");
    JRadioButton radioEvaluacion = new JRadioButton("Autoevaluación");
    final ButtonGroup groupModoEjecucion = new ButtonGroup();
	
    private void setLookAndFeel() {
    	
    	this.setOpaque(false);
   
		radioAprendizaje.setContentAreaFilled(false);
		radioAprendizaje.setIcon(new ImageIcon(Main.class.getResource("/images/UnselectedAzul.png")));
		radioAprendizaje.setSelectedIcon(new ImageIcon(Main.class.getResource("/images/SelectedAzul.png")));
		radioAprendizaje.setPressedIcon(new ImageIcon(Main.class.getResource("/images/SelectedAzul.png")));
		radioAprendizaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		radioAprendizaje.setForeground(new Color(255,255,255));

		radioEvaluacion.setContentAreaFilled(false);
		radioEvaluacion.setIcon(new ImageIcon(Main.class.getResource("/images/UnselectedAzul.png")));
		radioEvaluacion.setSelectedIcon(new ImageIcon(Main.class.getResource("/images/SelectedAzul.png")));
		radioEvaluacion.setPressedIcon(new ImageIcon(Main.class.getResource("/images/SelectedAzul.png")));
		radioEvaluacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		radioEvaluacion.setForeground(new Color(255,255,255));
		
		lblModoDeEjecucion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblModoDeEjecucion.setBackground(new Color(100,100,100));
		lblModoDeEjecucion.setForeground(new Color(255,255,255));
		lblModoDeEjecucion.setBorder(new EmptyBorder(5, 70, 5, 70) );
		lblModoDeEjecucion.setOpaque(true);
		lblModoDeEjecucion.setToolTipText("Seleccione un Modo...");
    }
	
	public PanelModo(){
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
