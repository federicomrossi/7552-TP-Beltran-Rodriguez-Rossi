package ar.com.taller2.papers.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import ar.com.taller2.papers.Main;
import ar.com.taller2.papers.MainBackup;

public class PapersToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4832443237086219062L;

	final JButton buttonInit = new JButton("");
    final JButton buttonPrevious = new JButton("");
    final JButton buttonNext = new JButton("");
    final JButton buttonEnd = new JButton("");
    final JButton buttonPlay = new JButton("");
    
    //Temp
    private boolean tempPlay = false;
    
    private void setLookAndFeel() {
    	
    	this.setOpaque(false);
    	
		buttonInit.setIcon(new ImageIcon(Main.class.getResource("/images/icon-arrow-ini-24.png")));
		buttonInit.setBorderPainted(false);
		buttonInit.setContentAreaFilled(false);
    	
		buttonPrevious.setIcon(new ImageIcon(Main.class.getResource("/images/icon-arrow-reverse-24.png")));
		buttonPrevious.setBorderPainted(false);
		buttonPrevious.setContentAreaFilled(false);
		
		buttonNext.setIcon(new ImageIcon(Main.class.getResource("/images/icon-arrow-forward-24.png")));
		buttonNext.setBorderPainted(false);
		buttonNext.setContentAreaFilled(false);
		
		buttonEnd.setIcon(new ImageIcon(Main.class.getResource("/images/icon-arrow-end-24.png")));
		buttonEnd.setBorderPainted(false);
		buttonEnd.setContentAreaFilled(false);
		
		buttonPlay.setIcon(new ImageIcon(Main.class.getResource("/images/icon-play-24.png")));
		buttonPlay.setBorderPainted(false);
		buttonPlay.setContentAreaFilled(false);
		
    }
	
	public PapersToolbar(){
		
		this.add(buttonInit);
		this.add(buttonPrevious);
		this.add(buttonPlay);
		this.add(buttonNext);
		this.add(buttonEnd);
		
		bloquearTodo();
		setLookAndFeel();
	}
	
	public void bloquearTodo() {
		buttonInit.setEnabled(false);
		buttonPrevious.setEnabled(false);
		buttonNext.setEnabled(false);
		buttonEnd.setEnabled(false);
		buttonPlay.setEnabled(false);
	}
	
	public void desbloquearTodo() {
		buttonPlay.setEnabled(true);
	}
	
	public void addInitActionListener(ActionListener l){
		buttonInit.addActionListener(l);
	}
	
	public void addPreviousActionListener(ActionListener l){
		buttonPrevious.addActionListener(l);
	}
	
	public void addNextActionListener(ActionListener l){
		buttonNext.addActionListener(l);
	}
	
	public void addEndActionListener(ActionListener l){
		buttonEnd.addActionListener(l);
	}
	
	public void addPlayActionListener(ActionListener l){
		buttonPlay.addActionListener(l);
		buttonPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TEMP
				if(tempPlay) {
					tempPlay = false;
					buttonPlay.setIcon(new ImageIcon(MainBackup.class.getResource("/images/icon-play-24.png")));
					
					// deshabilitamos botones de ejecución
					buttonNext.setEnabled(false);
					buttonEnd.setEnabled(false);
					buttonInit.setEnabled(false);
					buttonPrevious.setEnabled(false);
					
//					// Habilitamos botones del grupo de algoritmos
//					Enumeration<AbstractButton> groupAlgoritmosBotones = groupAlgoritmos.getElements();
//					while (groupAlgoritmosBotones.hasMoreElements()) {
//						JRadioButton element = (JRadioButton) groupAlgoritmosBotones.nextElement();
//						element.setEnabled(true);
//					}
//					
//					// Habilitamos botones del grupo de modode ejecución
//					Enumeration<AbstractButton> groupModoBotones = groupModoEjecucion.getElements();
//					while (groupModoBotones.hasMoreElements()) {
//						JRadioButton element = (JRadioButton) groupModoBotones.nextElement();
//						element.setEnabled(true);
//					}
				}
				else {
					tempPlay = true;
					buttonPlay.setIcon(new ImageIcon(MainBackup.class.getResource("/images/icon-stop-24.png")));
					
					// Habilitamos botones de ejecución
					buttonNext.setEnabled(true);
					buttonEnd.setEnabled(true);
					buttonInit.setEnabled(true);
					buttonPrevious.setEnabled(true);
					
//					// Deshabilitamos botones del grupo de algoritmos
//					
//					Enumeration<AbstractButton> enume = groupAlgoritmos.getElements();
//					while (enume.hasMoreElements()) {
//						JRadioButton element = (JRadioButton) enume.nextElement();
//						element.setEnabled(false);
//					}
//					
//					// Deshabilitamos botones del grupo de modode ejecución
//					Enumeration<AbstractButton> groupModoBotones = groupModoEjecucion.getElements();
//					while (groupModoBotones.hasMoreElements()) {
//						JRadioButton element = (JRadioButton) groupModoBotones.nextElement();
//						element.setEnabled(false);
//					}			
				}
				// END TEMP
			}
		});
	}
	
	public void removeInitActionListener(ActionListener l){
		buttonInit.removeActionListener(l);
	}
	
	public void removePreviousActionListener(ActionListener l){
		buttonPrevious.removeActionListener(l);
	}
	
	public void removeNextActionListener(ActionListener l){
		buttonNext.removeActionListener(l);
	}
	
	public void removeEndActionListener(ActionListener l){
		buttonEnd.removeActionListener(l);
	}
	
	public void removePlayActionListener(ActionListener l){
		buttonPlay.removeActionListener(l);
	}
	
}
