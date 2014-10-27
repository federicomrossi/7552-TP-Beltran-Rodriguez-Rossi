package ar.com.taller2.papers.view;

import java.awt.Font;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class PanelModo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7355328580258119324L;

	JLabel lblModoDeEjecucion = new JLabel("Modo de ejecución:");
    JRadioButton radioAprendizaje = new JRadioButton("Aprendizaje");
    JRadioButton radioEvaluacion = new JRadioButton("Autoevaluación");
    final ButtonGroup groupModoEjecucion = new ButtonGroup();
	
	
	public PanelModo(){
		lblModoDeEjecucion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModoDeEjecucion.setBorder(new EmptyBorder(0, 0, 10, 0) );
		this.add(lblModoDeEjecucion);
				
		
		radioAprendizaje.setSelected(true);
		this.add(radioAprendizaje);
		this.add(radioEvaluacion);
		
		
		groupModoEjecucion.add(radioAprendizaje);
		groupModoEjecucion.add(radioEvaluacion);
		
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
