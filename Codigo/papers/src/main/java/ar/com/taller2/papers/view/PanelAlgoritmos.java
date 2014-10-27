package ar.com.taller2.papers.view;

import java.awt.Font;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class PanelAlgoritmos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1332494097029056510L;

	JLabel lblAlgoritmos = new JLabel("Algoritmos:");
    
    JRadioButton radioButtonAlgoritmoPruebaAciclidad = new JRadioButton("Prueba de Aciclidad");
    JRadioButton radioButtonAlgoritmoRecorridoTopologicoAnchura = new JRadioButton("Recorrido topológico en Anchura");
    JRadioButton radioButtonAlgoritmoCerraduraTransitiva = new JRadioButton("Cerradura Transitiva");
    JRadioButton radioButtonAlgoritmoComponentesFuertementeConexas = new JRadioButton("Componentes Fuertemente Conexas");
    JRadioButton radioButtonAlgoritmoDijkstra = new JRadioButton("Algoritmo de Dijkstra");
    JRadioButton radioButtonAlgoritmoFloyd = new JRadioButton("Algoritmo de Floyd");
    JRadioButton radioButtonAlgoritmoFordFulkerson = new JRadioButton("Algoritmo de Ford-Fulkerson");
    JRadioButton radioButtonAlgoritmoRecorridoProfundidad = new JRadioButton("Recorrido en Profundidad");
    JRadioButton radioButtonAlgoritmoRecorridoAnchura = new JRadioButton("Recorrido en Anchura");
    JRadioButton radioButtonAlgoritmoRecorridoTopologicoProfundidad = new JRadioButton("Recorrido topológico en Profundidad");
    JRadioButton radioButtonAlgoritmoArbolExpansionCosteMinimo = new JRadioButton("Árbol de Expansión de Coste Mínimo");
    final ButtonGroup groupAlgoritmos = new ButtonGroup();
	
	
	public PanelAlgoritmos(){
		lblAlgoritmos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlgoritmos.setBorder(new EmptyBorder(15, 0, 20, 0));
		this.add(lblAlgoritmos);
		

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
