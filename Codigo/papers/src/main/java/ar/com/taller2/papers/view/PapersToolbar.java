package ar.com.taller2.papers.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import ar.com.taller2.papers.Main;

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
    	
		buttonInit.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-arrow-ini-24.png")));
		buttonInit.setBorderPainted(false);
		buttonInit.setContentAreaFilled(false);
		buttonInit.setToolTipText("Inicio");
		buttonInit.setRolloverEnabled(true);
		buttonInit.setRolloverIcon(new ImageIcon(this.getClass().getResource("/images/icon-arrow-ini-24-rollover.png")));
    	
		buttonPrevious.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-arrow-reverse-24.png")));
		buttonPrevious.setBorderPainted(false);
		buttonPrevious.setContentAreaFilled(false);
		buttonPrevious.setToolTipText("Atrás");
		buttonPrevious.setRolloverEnabled(true);
		buttonPrevious.setRolloverIcon(new ImageIcon(this.getClass().getResource("/images/icon-arrow-reverse-24-rollover.png")));
		
		buttonNext.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-arrow-forward-24.png")));
		buttonNext.setBorderPainted(false);
		buttonNext.setContentAreaFilled(false);
		buttonNext.setToolTipText("Adelante");
		buttonNext.setRolloverEnabled(true);
		buttonNext.setRolloverIcon(new ImageIcon(this.getClass().getResource("/images/icon-arrow-forward-24-rollover.png")));
		
		buttonEnd.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-arrow-end-24.png")));
		buttonEnd.setBorderPainted(false);
		buttonEnd.setContentAreaFilled(false);
		buttonEnd.setToolTipText("Fin");
		buttonEnd.setRolloverEnabled(true);
		buttonEnd.setRolloverIcon(new ImageIcon(this.getClass().getResource("/images/icon-arrow-end-24-rollover.png")));
		
		buttonPlay.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-stop-24.png")));
		buttonPlay.setBorderPainted(false);
		buttonPlay.setContentAreaFilled(false);
		buttonPlay.setToolTipText("Comenzar/Finalizar");
		buttonPlay.setRolloverEnabled(true);
		buttonPlay.setRolloverIcon(new ImageIcon(this.getClass().getResource("/images/icon-play-25.png")));
		
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
	
	public void bloquearFin() {
		buttonEnd.setEnabled(false);
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
	}
	
	public void desbloquearToolbar(){
		buttonPlay.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-play-25.png")));
		buttonPlay.setRolloverIcon(new ImageIcon(this.getClass().getResource("/images/icon-stop-24.png")));
		
		// Habilitamos botones de ejecución
		buttonNext.setEnabled(true);
		buttonEnd.setEnabled(true);
		buttonInit.setEnabled(true);
		buttonPrevious.setEnabled(true);
	}
	
	public void bloquearToolbar(){
		buttonPlay.setIcon(new ImageIcon(this.getClass().getResource("/images/icon-stop-24.png")));
		buttonPlay.setRolloverIcon(new ImageIcon(this.getClass().getResource("/images/icon-play-25.png")));
		
		// deshabilitamos botones de ejecución
		buttonNext.setEnabled(false);
		buttonEnd.setEnabled(false);
		buttonInit.setEnabled(false);
		buttonPrevious.setEnabled(false);
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

	public void setModoEvaluacion(boolean b) {
		buttonPlay.setEnabled(!b);
		buttonEnd.setEnabled(!b);
		buttonNext.setEnabled(!b);
		buttonPrevious.setEnabled(!b);
		buttonInit.setEnabled(!b);
	}
	
}
