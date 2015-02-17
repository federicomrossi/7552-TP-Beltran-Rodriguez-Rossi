package ar.com.taller2.papers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import ar.com.taller2.papers.About;

public class PapersMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7103349238902057815L;

	JMenu mnArchivo = new JMenu("Archivo");
	JMenu mnNuevo = new JMenu("Nuevo");
	JMenu mntmGrafoOrientado = new JMenu("Grafo Orientado");
	JMenu mntmGrafoNoOrientado = new JMenu("Grafo No Orientado");
	JMenuItem mntmGrafoOrientadoAleatorio = new JMenuItem("Grafo Aleatorio");
	JMenuItem mntmGrafoOrientadoVacio = new JMenuItem("Grafo Vacío");
	
	JMenuItem mntmGrafoNoOrientadoAleatorio = new JMenuItem("Grafo Aleatorio");
	JMenuItem mntmGrafoNoOrientadoVacio = new JMenuItem("Grafo Vacío");
	
	JSeparator separator_1 = new JSeparator();
	JMenuItem mntmAbrir = new JMenuItem("Abrir...");
	JSeparator separator = new JSeparator();
	JMenuItem mntmGuardar = new JMenuItem("Guardar");
	JMenuItem mntmGuardarComo = new JMenuItem("Guardar Como...");
	JMenu mnEditar = new JMenu("Editar");
	JMenuItem mntmNuevoVertice = new JMenuItem("Nuevo Vértice");
	JMenuItem mntmNuevoArista = new JMenuItem("Nueva Arista");
	JMenu mnHelp = new JMenu("Ayuda");
	JMenuItem mntmHelpContents = new JMenuItem("Guía de Usuario");
	JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
	
	public PapersMenu(){
		
		this.add(mnArchivo);		
		mnArchivo.add(mnNuevo);
		mnNuevo.add(mntmGrafoOrientado);
		mnNuevo.add(mntmGrafoNoOrientado);
		mntmGrafoOrientado.add(mntmGrafoOrientadoAleatorio);
		mntmGrafoOrientado.add(mntmGrafoOrientadoVacio);
		mntmGrafoNoOrientado.add(mntmGrafoNoOrientadoAleatorio);
		mntmGrafoNoOrientado.add(mntmGrafoNoOrientadoVacio);
		mnArchivo.add(separator_1);
		mnArchivo.add(mntmAbrir);
		mnArchivo.add(separator);
		mnArchivo.add(mntmGuardar);
		mnArchivo.add(mntmGuardarComo);
		mnEditar.add(mntmNuevoVertice);
		mnEditar.add(mntmNuevoArista);
		this.add(mnEditar);
		this.add(mnHelp);
		
		mnHelp.add(mntmHelpContents);
		
		JSeparator separator_2 = new JSeparator();
		mnHelp.add(separator_2);
		
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setLocationRelativeTo(null);
				about.setVisible(true);
			}
		});
		mnHelp.add(mntmAcercaDe);
		
		bloquearMenuGuardar();
		
	}
	
	public void bloquearMenuGuardar() {	
		mntmGuardar.setEnabled(false);
		mntmGuardarComo.setEnabled(false);
	}
	
	public void desbloquearMenuGuardar() {	
		mntmGuardar.setEnabled(true);
		mntmGuardarComo.setEnabled(true);
	}
	
	public void archivoNuevoGrafoOrientadoVacioTriggerAction(){
		mntmGrafoOrientadoVacio.doClick();
	}
	
	public void archivoNuevoGrafoNoOrientadoVacioTriggerAction(){
		mntmGrafoNoOrientadoVacio.doClick();
	}
	
	public void addArchivoNuevoGrafoOrientadoAleatorioActionListener(ActionListener a){
		mntmGrafoOrientadoAleatorio.addActionListener(a);
	}
	
	public void addArchivoNuevoGrafoOrientadoVacioActionListener(ActionListener a){
		mntmGrafoOrientadoVacio.addActionListener(a);
	}
	
	public void addArchivoNuevoGrafoNoOrientadoAleatorioActionListener(ActionListener a){
		mntmGrafoNoOrientadoAleatorio.addActionListener(a);
	}
	
	public void addArchivoNuevoGrafoNoOrientadoVacioActionListener(ActionListener a){
		mntmGrafoNoOrientadoVacio.addActionListener(a);
	}
	
	public void addArchivoAbrirActionListener(ActionListener a){
		mntmAbrir.addActionListener(a);
	}
	
	public void addArchivoGuardarActionListener(ActionListener a){
		mntmGuardar.addActionListener(a);
	}
	
	public void addArchivoGuardarComoActionListener(ActionListener a){
		mntmGuardarComo.addActionListener(a);
	}

	public void addEditarNuevoVerticeActionListener(ActionListener a) {
		mntmNuevoVertice.addActionListener(a);
	}
	
	public void addEditarNuevaAristaActionListener(ActionListener a) {
		mntmNuevoArista.addActionListener(a);
	}
	
	public void addAyudaAcercaDeActionListener(ActionListener a){
		mntmAcercaDe.addActionListener(a);
	}
	
	public void addAyudaGuiaUsuarioActionListener(ActionListener a){
		mntmHelpContents.addActionListener(a);
	}
	
	public void removeArchivoNuevoGrafoOrientadoActionListener(ActionListener a){
		mntmGrafoOrientado.removeActionListener(a);
	}
	
	public void removeArchivoNuevoGrafoNoOrientadoActionListener(ActionListener a){
		mntmGrafoNoOrientado.removeActionListener(a);
	}
	
	public void removeArchivoAbrirActionListener(ActionListener a){
		mntmAbrir.removeActionListener(a);
	}
	
	public void removeArchivoGuardarActionListener(ActionListener a){
		mntmGuardar.removeActionListener(a);
	}
	
	public void removeArchivoGuardarComoActionListener(ActionListener a){
		mntmGuardarComo.removeActionListener(a);
	}
	
	public void removeAyudaAcercaDeActionListener(ActionListener a){
		mntmAcercaDe.removeActionListener(a);
	}
	
	public void removeAyudaGuiaUsuarioActionListener(ActionListener a){
		mntmHelpContents.removeActionListener(a);
	}

	public void removeEditarNuevoVerticeActionListener(ActionListener a) {
		mntmNuevoVertice.removeActionListener(a);
	}

	public void setModoEdicion(boolean b) {
		mnEditar.setEnabled(b);
	}
	
}
