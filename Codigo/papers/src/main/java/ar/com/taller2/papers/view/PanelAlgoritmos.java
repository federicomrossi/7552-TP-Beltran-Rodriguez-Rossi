package ar.com.taller2.papers.view;

import java.awt.Font;

import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import ar.com.taller2.papers.Main;

public class PanelAlgoritmos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1332494097029056510L;

	JLabel lblAlgoritmos = new JLabel("Algoritmos:");
	
	HashMap<String,JRadioButton> botones = new HashMap<String,JRadioButton>();
    
    JRadioButton radioButtonAlgoritmoPruebaAciclidad;
    JRadioButton radioButtonAlgoritmoRecorridoTopologicoAnchura;
    JRadioButton radioButtonAlgoritmoCerraduraTransitiva;
    JRadioButton radioButtonAlgoritmoComponentesFuertementeConexas;
    JRadioButton radioButtonAlgoritmoDijkstra;
    JRadioButton radioButtonAlgoritmoFloyd;
    JRadioButton radioButtonAlgoritmoFordFulkerson;
    JRadioButton radioButtonAlgoritmoRecorridoProfundidad;
    JRadioButton radioButtonAlgoritmoRecorridoAnchura;
    JRadioButton radioButtonAlgoritmoRecorridoTopologicoProfundidad;
    JRadioButton radioButtonAlgoritmoArbolExpansionCosteMinimo;
    final ButtonGroup groupAlgoritmos = new ButtonGroup();
	
    private void crearBotones() {

        radioButtonAlgoritmoPruebaAciclidad = new JRadioButton("Prueba de Aciclidad");
        radioButtonAlgoritmoRecorridoTopologicoAnchura = new JRadioButton("Recorrido topológico en Anchura");
        radioButtonAlgoritmoCerraduraTransitiva = new JRadioButton("Cerradura Transitiva");
        radioButtonAlgoritmoComponentesFuertementeConexas = new JRadioButton("Componentes Fuertemente Conexas");
        radioButtonAlgoritmoDijkstra = new JRadioButton("Algoritmo de Dijkstra");
        radioButtonAlgoritmoFloyd = new JRadioButton("Algoritmo de Floyd");
        radioButtonAlgoritmoFordFulkerson = new JRadioButton("Algoritmo de Ford-Fulkerson");
        radioButtonAlgoritmoRecorridoProfundidad = new JRadioButton("Recorrido en Profundidad");
        radioButtonAlgoritmoRecorridoAnchura = new JRadioButton("Recorrido en Anchura");
        radioButtonAlgoritmoRecorridoTopologicoProfundidad = new JRadioButton("Recorrido topológico en Profundidad");
        radioButtonAlgoritmoArbolExpansionCosteMinimo = new JRadioButton("Árbol de Expansión de Coste Mínimo");
    	    	
        radioButtonAlgoritmoPruebaAciclidad.setName("Prueba de Aciclidad");
        radioButtonAlgoritmoRecorridoTopologicoAnchura.setName("Recorrido topológico en Anchura");
        radioButtonAlgoritmoCerraduraTransitiva.setName("Cerradura Transitiva");
        radioButtonAlgoritmoComponentesFuertementeConexas.setName("Componentes Fuertemente Conexas");
        radioButtonAlgoritmoDijkstra.setName("Algoritmo de Dijkstra");
        radioButtonAlgoritmoFloyd.setName("Algoritmo de Floyd");
        radioButtonAlgoritmoFordFulkerson.setName("Algoritmo de Ford-Fulkerson");
        radioButtonAlgoritmoRecorridoProfundidad.setName("Recorrido en Profundidad");
        radioButtonAlgoritmoRecorridoAnchura.setName("Recorrido en Anchura");
        radioButtonAlgoritmoRecorridoTopologicoProfundidad.setName("Recorrido topológico en Profundidad");
        radioButtonAlgoritmoArbolExpansionCosteMinimo.setName("Árbol de Expansión de Coste Mínimo");
    	
    	
		botones.put(radioButtonAlgoritmoPruebaAciclidad.getName(), radioButtonAlgoritmoPruebaAciclidad);    	
	    botones.put(radioButtonAlgoritmoRecorridoTopologicoAnchura.getName(), radioButtonAlgoritmoRecorridoTopologicoAnchura);
	    botones.put(radioButtonAlgoritmoCerraduraTransitiva.getName(), radioButtonAlgoritmoCerraduraTransitiva);
	    botones.put(radioButtonAlgoritmoComponentesFuertementeConexas.getName(), radioButtonAlgoritmoComponentesFuertementeConexas);
	    botones.put(radioButtonAlgoritmoDijkstra.getName(), radioButtonAlgoritmoDijkstra);
	    botones.put(radioButtonAlgoritmoFloyd.getName(), radioButtonAlgoritmoFloyd);
	    botones.put(radioButtonAlgoritmoFordFulkerson.getName(), radioButtonAlgoritmoFordFulkerson);
	    botones.put(radioButtonAlgoritmoRecorridoProfundidad.getName(), radioButtonAlgoritmoRecorridoProfundidad);
	    botones.put(radioButtonAlgoritmoRecorridoAnchura.getName(), radioButtonAlgoritmoRecorridoAnchura);
	    botones.put(radioButtonAlgoritmoRecorridoTopologicoProfundidad.getName(), radioButtonAlgoritmoRecorridoTopologicoProfundidad);
	    botones.put(radioButtonAlgoritmoArbolExpansionCosteMinimo.getName(), radioButtonAlgoritmoArbolExpansionCosteMinimo);
	    
    }
    
    private void setLookAndFeel() {
    	
    	this.setOpaque(false);
     
		for (Entry<String,JRadioButton> e : botones.entrySet()) {
			e.getValue().setContentAreaFilled(false);
			e.getValue().setIcon(new ImageIcon(Main.class.getResource("/images/Unselected.png")));
			e.getValue().setSelectedIcon(new ImageIcon(Main.class.getResource("/images/Selected.png")));
			e.getValue().setDisabledIcon(new ImageIcon(Main.class.getResource("/images/Disabled.png")));
			e.getValue().setPressedIcon(new ImageIcon(Main.class.getResource("/images/Selected.png")));
		}
    }
	
	public PanelAlgoritmos(){
		lblAlgoritmos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlgoritmos.setBorder(new EmptyBorder(15, 0, 20, 0));
		this.add(lblAlgoritmos);
		
		crearBotones();

		// Selección de algoritmos
		this.add(radioButtonAlgoritmoPruebaAciclidad);
		this.add(radioButtonAlgoritmoRecorridoTopologicoAnchura);
		this.add(radioButtonAlgoritmoCerraduraTransitiva);
		this.add(radioButtonAlgoritmoComponentesFuertementeConexas);
		this.add(radioButtonAlgoritmoDijkstra);
		this.add(radioButtonAlgoritmoFloyd);
		this.add(radioButtonAlgoritmoFordFulkerson);
		this.add(radioButtonAlgoritmoRecorridoProfundidad);
		this.add(radioButtonAlgoritmoRecorridoAnchura);
		this.add(radioButtonAlgoritmoRecorridoTopologicoProfundidad);
		this.add(radioButtonAlgoritmoArbolExpansionCosteMinimo);
	
		radioButtonAlgoritmoPruebaAciclidad.setSelected(true);
		
		bloquearTodo();
		
		groupAlgoritmos.add(radioButtonAlgoritmoPruebaAciclidad);
		groupAlgoritmos.add(radioButtonAlgoritmoRecorridoTopologicoAnchura);
		groupAlgoritmos.add(radioButtonAlgoritmoCerraduraTransitiva);
		groupAlgoritmos.add(radioButtonAlgoritmoComponentesFuertementeConexas);
		groupAlgoritmos.add(radioButtonAlgoritmoDijkstra);
		groupAlgoritmos.add(radioButtonAlgoritmoFloyd);
		groupAlgoritmos.add(radioButtonAlgoritmoFordFulkerson);
		groupAlgoritmos.add(radioButtonAlgoritmoRecorridoProfundidad);
		groupAlgoritmos.add(radioButtonAlgoritmoRecorridoAnchura);
		groupAlgoritmos.add(radioButtonAlgoritmoRecorridoTopologicoProfundidad);
		groupAlgoritmos.add(radioButtonAlgoritmoArbolExpansionCosteMinimo);

	    setLookAndFeel();
	}
	
	public void bloquearTodo() {
		
		for (Entry<String,JRadioButton> e : botones.entrySet()) {
			e.getValue().setEnabled(false);
		}
		
	}
	
	public void desbloquearTodo() {
		
		for (Entry<String,JRadioButton> e : botones.entrySet()) {
			e.getValue().setEnabled(true);
		}
		
	}
	
	public void bloquearTodoExcepto(JRadioButton seleccion) {
		
	}
	
	public void addPruebaAciclidadItemListener(ItemListener il){
		radioButtonAlgoritmoPruebaAciclidad.addItemListener(il);
	}
	
	public void addRecorridoTopologicoAnchuraItemListener(ItemListener l){
		radioButtonAlgoritmoRecorridoTopologicoAnchura.addItemListener(l);
	}
	
	public void addCerraduraTransitivaItemListener(ItemListener l){
		radioButtonAlgoritmoCerraduraTransitiva.addItemListener(l);
	}
	
	public void addComponentesFuertementeConexasItemListener(ItemListener l){
		radioButtonAlgoritmoComponentesFuertementeConexas.addItemListener(l);
	}
	
	public void addDijkstraItemListener(ItemListener l){
		radioButtonAlgoritmoDijkstra.addItemListener(l);
	}
	
	public void addFloydItemListener(ItemListener l){
		radioButtonAlgoritmoFloyd.addItemListener(l);
	}
	
	public void addFordFulkersonItemListener(ItemListener l){
		radioButtonAlgoritmoFordFulkerson.addItemListener(l);
	}
	
	public void addRecorridoProfundidadItemListener(ItemListener l){
		radioButtonAlgoritmoRecorridoProfundidad.addItemListener(l);
	}
	
	public void addRecorridoAnchuraItemListener(ItemListener l){
		radioButtonAlgoritmoRecorridoAnchura.addItemListener(l);
	}
	
	public void addRecorridoTopologicoProfundidadItemListener(ItemListener l){
		radioButtonAlgoritmoRecorridoTopologicoProfundidad.addItemListener(l);
	}
	
	public void addArbolExpansionCosteMinimoItemListener(ItemListener l){
		radioButtonAlgoritmoArbolExpansionCosteMinimo.addItemListener(l);
	}
	
	public void removePruebaAciclidadItemListener(ItemListener il){
		radioButtonAlgoritmoPruebaAciclidad.removeItemListener(il);
	}
	
	public void removeRecorridoTopologicoAnchuraItemListener(ItemListener l){
		radioButtonAlgoritmoRecorridoTopologicoAnchura.removeItemListener(l);
	}
	
	public void removeCerraduraTransitivaItemListener(ItemListener l){
		radioButtonAlgoritmoCerraduraTransitiva.removeItemListener(l);
	}
	
	public void removeComponentesFuertementeConexasItemListener(ItemListener l){
		radioButtonAlgoritmoComponentesFuertementeConexas.removeItemListener(l);
	}
	
	public void removeDijkstraItemListener(ItemListener l){
		radioButtonAlgoritmoDijkstra.removeItemListener(l);
	}
	
	public void removeFloydItemListener(ItemListener l){
		radioButtonAlgoritmoFloyd.removeItemListener(l);
	}
	
	public void removeFordFulkersonItemListener(ItemListener l){
		radioButtonAlgoritmoFordFulkerson.removeItemListener(l);
	}
	
	public void removeRecorridoProfundidadItemListener(ItemListener l){
		radioButtonAlgoritmoRecorridoProfundidad.removeItemListener(l);
	}
	
	public void removeRecorridoAnchuraItemListener(ItemListener l){
		radioButtonAlgoritmoRecorridoAnchura.removeItemListener(l);
	}
	
	public void removeRecorridoTopologicoProfundidadItemListener(ItemListener l){
		radioButtonAlgoritmoRecorridoTopologicoProfundidad.removeItemListener(l);
	}
	
	public void removeArbolExpansionCosteMinimoItemListener(ItemListener l){
		radioButtonAlgoritmoArbolExpansionCosteMinimo.removeItemListener(l);
	}
	
}
