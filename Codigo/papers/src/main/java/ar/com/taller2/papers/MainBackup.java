package ar.com.taller2.papers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;

import ar.com.taller2.papers.adapters.JGraphXAdapter;
import ar.com.taller2.papers.model.graphs.Dijkstra_old;

public class MainBackup extends JApplet implements ActionListener {

	private static final long serialVersionUID = 5320477892293342036L;
	private static final Logger logger = LogManager.getLogger();
	
	private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 320);
    
    private static ListenableGraph<String, DefaultEdge> g = new ListenableDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
    private static JGraphXAdapter<String, DefaultEdge> graph = new JGraphXAdapter<String, DefaultEdge>(g);
    
    private JGraphModelAdapter m_jgAdapter;
    
    // TEMP
    private static boolean tempPlay = false;
    private JLabel lblTituloInformacion;
    // END TEMP
	
	
	/**
	 * Create the applet.
	 * @throws IOException 
	 */
	public MainBackup() throws IOException {
		
		try {
            // Set System L&F
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (Exception e) {
	       // handle exception
	    }
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenu mnNuevo = new JMenu("Nuevo");
		mnArchivo.add(mnNuevo);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Grafo Orientado");
		mnNuevo.add(mntmNewMenuItem);
		
		JMenuItem mntmGrafoNoOrientado = new JMenuItem("Grafo No Orientado");
		mnNuevo.add(mntmGrafoNoOrientado);
		
		JSeparator separator_1 = new JSeparator();
		mnArchivo.add(separator_1);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir...");
		mnArchivo.add(mntmAbrir);
		
		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar Como...");
		mnArchivo.add(mntmGuardarComo);
		
		JMenu mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);
		
		JMenu mnHelp = new JMenu("Ayuda");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelpContents = new JMenuItem("Guía de Usuario");
		mnHelp.add(mntmHelpContents);
		
		JSeparator separator_2 = new JSeparator();
		mnHelp.add(separator_2);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setLocationRelativeTo(null);
				about.setVisible(true);
			}
		});
		mnHelp.add(mntmAcercaDe);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		getContentPane().add(splitPane_1);
		
		JPanel panelIzquierda = new JPanel();
		splitPane_1.setLeftComponent(panelIzquierda);
		panelIzquierda.setLayout(new GridLayout(0, 1, 0, 0));
		
		final JPanel panelAlgorimos = new JPanel();
		panelAlgorimos.setBorder(new EmptyBorder(10, 10, 10, 10) );
		panelIzquierda.add(panelAlgorimos);
		panelAlgorimos.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblAlgoritmos = new JLabel("Algoritmos:");
		lblAlgoritmos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlgoritmos.setBorder(new EmptyBorder(15, 0, 20, 0));
		panelAlgorimos.add(lblAlgoritmos);
		

		// Selección de algoritmos
		JRadioButton radioButtonAlgoritmoPruebaAciclidad = new JRadioButton("Prueba de Aciclidad");
		panelAlgorimos.add(radioButtonAlgoritmoPruebaAciclidad);
		JRadioButton radioButtonAlgoritmoRecorridoTopologicoAnchura = new JRadioButton("Recorrido topológico en Anchura");
		panelAlgorimos.add(radioButtonAlgoritmoRecorridoTopologicoAnchura);
		JRadioButton radioButtonAlgoritmoCerraduraTransitiva = new JRadioButton("Cerradura Transitiva");
		panelAlgorimos.add(radioButtonAlgoritmoCerraduraTransitiva);
		JRadioButton radioButtonAlgoritmoComponentesFuertementeConexas = new JRadioButton("Componentes Fuertemente Conexas");
		panelAlgorimos.add(radioButtonAlgoritmoComponentesFuertementeConexas);
		JRadioButton radioButtonAlgoritmoDijkstra = new JRadioButton("Algoritmo de Dijkstra");
		panelAlgorimos.add(radioButtonAlgoritmoDijkstra);
		JRadioButton radioButtonAlgoritmoFloyd = new JRadioButton("Algoritmo de Floyd");
		panelAlgorimos.add(radioButtonAlgoritmoFloyd);
		JRadioButton radioButtonAlgoritmoFordFulkerson = new JRadioButton("Algoritmo de Ford-Fulkerson");
		panelAlgorimos.add(radioButtonAlgoritmoFordFulkerson);
		JRadioButton radioButtonAlgoritmoRecorridoProfundidad = new JRadioButton("Recorrido en Profundidad");
		panelAlgorimos.add(radioButtonAlgoritmoRecorridoProfundidad);
		JRadioButton radioButtonAlgoritmoRecorridoAnchura = new JRadioButton("Recorrido en Anchura");
		panelAlgorimos.add(radioButtonAlgoritmoRecorridoAnchura);
		JRadioButton radioButtonAlgoritmoRecorridoTopologicoProfundidad = new JRadioButton("Recorrido topológico en Profundidad");
		panelAlgorimos.add(radioButtonAlgoritmoRecorridoTopologicoProfundidad);
		JRadioButton radioButtonAlgoritmoArbolExpansionCosteMinimo = new JRadioButton("Árbol de Expansión de Coste Mínimo");
		panelAlgorimos.add(radioButtonAlgoritmoArbolExpansionCosteMinimo);
		
		radioButtonAlgoritmoPruebaAciclidad.setSelected(true);
		
		final ButtonGroup groupAlgoritmos = new ButtonGroup();
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
		
		
//		JButton button_4 = new JButton("Prueba de Aciclidad");
//		panelAlgorimos.add(button_4);
//		
//		JButton button_5 = new JButton("Recorrido topológico en Anchura");
//		panelAlgorimos.add(button_5);
//		
//		JButton button_6 = new JButton("Cerradura Transitiva");
//		panelAlgorimos.add(button_6);
//		
//		JButton button_7 = new JButton("Componentes Fuertemente Conexas");
//		panelAlgorimos.add(button_7);
//		
//		JButton button_8 = new JButton("Algoritmo de Dijkstra");
//		panelAlgorimos.add(button_8);
//		
//		JButton button_9 = new JButton("Algoritmo de Floyd");
//		panelAlgorimos.add(button_9);
//		
//		JButton button_10 = new JButton("Algoritmo de Ford-Fulkerson");
//		panelAlgorimos.add(button_10);
//		
//		JButton button_11 = new JButton("Recorrido en Profundidad");
//		panelAlgorimos.add(button_11);
//		
//		JButton button_12 = new JButton("Recorrido en Anchura");
//		panelAlgorimos.add(button_12);
//		
//		JButton button_13 = new JButton("Recorrido topológico en Profundidad");
//		panelAlgorimos.add(button_13);
		
		JPanel panelModo = new JPanel();
		panelModo.setBorder(new EmptyBorder(10, 10, 10, 10) );
		panelIzquierda.add(panelModo);
		panelModo.setLayout(new BoxLayout(panelModo, BoxLayout.Y_AXIS));
		
		JLabel lblModoDeEjecucion = new JLabel("Modo de ejecución:");
		lblModoDeEjecucion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblModoDeEjecucion.setBorder(new EmptyBorder(0, 0, 10, 0) );
		panelModo.add(lblModoDeEjecucion);
				
		JRadioButton radioButton = new JRadioButton("Aprendizaje");
		radioButton.setSelected(true);
		panelModo.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Autoevaluación");
		panelModo.add(radioButton_1);
		
		final ButtonGroup groupModoEjecucion = new ButtonGroup();
		groupModoEjecucion.add(radioButton);
		groupModoEjecucion.add(radioButton_1);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_1.setRightComponent(splitPane_2);
		
		JPanel panelCentro = new JPanel();
		splitPane_2.setLeftComponent(panelCentro);
		panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panelCentro.add(toolBar);
		
		final JButton buttonInit = new JButton("");
		buttonInit.setEnabled(false);
		buttonInit.setIcon(new ImageIcon(MainBackup.class.getResource("/images/icon-arrow-ini-24.png")));
		toolBar.add(buttonInit);
		
		final JButton buttonPrevious = new JButton("");
		buttonPrevious.setEnabled(false);
		buttonPrevious.setIcon(new ImageIcon(MainBackup.class.getResource("/images/icon-arrow-reverse-24.png")));
		toolBar.add(buttonPrevious);
		
		final JButton buttonPlay = new JButton("");
		buttonPlay.setIcon(new ImageIcon(MainBackup.class.getResource("/images/icon-play-24.png")));
		toolBar.add(buttonPlay);
		
		final JButton buttonNext = new JButton("");
		buttonNext.setEnabled(false);
		buttonNext.setIcon(new ImageIcon(MainBackup.class.getResource("/images/icon-arrow-forward-24.png")));
		toolBar.add(buttonNext);
		
		final JButton buttonEnd = new JButton("");
		buttonEnd.setEnabled(false);
		buttonEnd.setIcon(new ImageIcon(MainBackup.class.getResource("/images/icon-arrow-end-24.png")));
		toolBar.add(buttonEnd);
		
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
					
					// Habilitamos botones del grupo de algoritmos
					Enumeration<AbstractButton> groupAlgoritmosBotones = groupAlgoritmos.getElements();
					while (groupAlgoritmosBotones.hasMoreElements()) {
						JRadioButton element = (JRadioButton) groupAlgoritmosBotones.nextElement();
						element.setEnabled(true);
					}
					
					// Habilitamos botones del grupo de modode ejecución
					Enumeration<AbstractButton> groupModoBotones = groupModoEjecucion.getElements();
					while (groupModoBotones.hasMoreElements()) {
						JRadioButton element = (JRadioButton) groupModoBotones.nextElement();
						element.setEnabled(true);
					}
				}
				else {
					tempPlay = true;
					buttonPlay.setIcon(new ImageIcon(MainBackup.class.getResource("/images/icon-stop-24.png")));
					
					// Habilitamos botones de ejecución
					buttonNext.setEnabled(true);
					buttonEnd.setEnabled(true);
					buttonInit.setEnabled(true);
					buttonPrevious.setEnabled(true);
					
					// Deshabilitamos botones del grupo de algoritmos
					Enumeration<AbstractButton> enume = groupAlgoritmos.getElements();
					while (enume.hasMoreElements()) {
						JRadioButton element = (JRadioButton) enume.nextElement();
						element.setEnabled(false);
					}
					
					// Deshabilitamos botones del grupo de modode ejecución
					Enumeration<AbstractButton> groupModoBotones = groupModoEjecucion.getElements();
					while (groupModoBotones.hasMoreElements()) {
						JRadioButton element = (JRadioButton) groupModoBotones.nextElement();
						element.setEnabled(false);
					}
					
					

				
				}
				// END TEMP
			}		
		});
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setAlignmentY(0.5f);
		splitPane_3.setAlignmentX(0.5f);
		panelCentro.add(splitPane_3);
		
		JTextPane txtSalida = new JTextPane();
		txtSalida.setText("Salida:");
		splitPane_3.setRightComponent(txtSalida);
		
		JTabbedPane tbdPaneDerecha = new JTabbedPane(JTabbedPane.TOP);
		splitPane_2.setRightComponent(tbdPaneDerecha);
		
		JPanel panelInformacion = new JPanel();
		tbdPaneDerecha.addTab("Información", null, panelInformacion, null);
		panelInformacion.setLayout(new BoxLayout(panelInformacion, BoxLayout.Y_AXIS));
		
		lblTituloInformacion = new JLabel("Algoritmo de Dijkstra");
		lblTituloInformacion.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblTituloInformacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInformacion.add(lblTituloInformacion);
		
		JTextPane textPaneContenidoInformacion = new JTextPane();
		textPaneContenidoInformacion.setContentType("text/html");
		textPaneContenidoInformacion.setAlignmentX(Component.LEFT_ALIGNMENT);
		textPaneContenidoInformacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		textPaneContenidoInformacion.setText("<html>\r\n<body>\r\n\t<div id=\"mw-content-text\" lang=\"es\" dir=\"ltr\" class=\"mw-content-ltr\">\r\n\t<p>El <b>algoritmo de Dijkstra</b>, también llamado <b>algoritmo de caminos mínimos</b>, es un <a href=\"http://es.wikipedia.org/wiki/Algoritmo\" title=\"Algoritmo\">algoritmo</a> para la determinación del <a href=\"http://es.wikipedia.org/wiki/Problema_de_los_caminos_m%C3%A1s_cortos\" title=\"Problema de los caminos más cortos\" class=\"mw-redirect\">camino más corto</a> dado un <a href=\"http://es.wikipedia.org/wiki/V%C3%A9rtice_(Teor%C3%ADa_de_grafos)\" title=\"Vértice (Teoría de grafos)\" class=\"mw-redirect\">vértice</a> origen al resto de vértices en un <a href=\"http://es.wikipedia.org/wiki/Grafo\" title=\"Grafo\">grafo</a> con pesos en cada <a href=\"http://es.wikipedia.org/wiki/Arista_(Teor%C3%ADa_de_grafos)\" title=\"Arista (Teoría de grafos)\" class=\"mw-redirect\">arista</a>. Su nombre se refiere a <a href=\"http://es.wikipedia.org/wiki/Edsger_Dijkstra\" title=\"Edsger Dijkstra\">Edsger Dijkstra</a>, quien lo describió por primera vez en 1959.</p>\r\n\t<p>La idea subyacente en este algoritmo consiste en ir explorando todos los caminos más cortos que parten del vértice origen y que llevan a todos los demás vértices; cuando se obtiene el camino más corto desde el vértice origen, al resto de vértices que componen el grafo, el algoritmo se detiene. El algoritmo es una especialización de la búsqueda de costo uniforme, y como tal, no funciona en grafos con aristas de coste negativo (al elegir siempre el nodo con distancia menor, pueden quedar excluidos de la búsqueda nodos que en próximas iteraciones bajarían el costo general del camino al pasar por una arista con costo negativo).</p>\r\n\t<p></p>\r\n\t<div id=\"toc\" class=\"toc\">\r\n\t<div id=\"toctitle\">\r\n\t\r\n\t<h2><span class=\"mw-headline\" id=\"Algoritmo\">Algoritmo</span></h2>\r\n\t<p>Teniendo un grafo dirigido ponderado de N nodos no aislados, sea x el nodo inicial, un vector D de tamaño N guardará al final del algoritmo las distancias desde x al resto de los nodos.</p>\r\n\t<ol>\r\n\t<li>Inicializar todas las distancias en D con un valor infinito relativo ya que son desconocidas al principio, exceptuando la de x que se debe colocar en 0 debido a que la distancia de x a x sería 0.</li>\r\n\t<li>Sea a = x (tomamos a como nodo actual).</li>\r\n\t<li>Recorremos todos los nodos adyacentes de <i>a</i>, excepto los nodos marcados, llamaremos a estos nodos no marcados v<sub>i</sub>.</li>\r\n\t<li>Para el nodo actual, calculamos la distancia tentativa desde dicho nodo a sus vecinos con la siguiente fórmula: dt(v<sub>i</sub>) = D<sub>a</sub> + d(a,v<sub>i</sub>). Es decir, la distancia tentativa del nodo ‘v<sub>i</sub>’ es la distancia que actualmente tiene el nodo en el vector D más la distancia desde dicho el nodo ‘a’ (el actual) al nodo v<sub>i</sub>. Si la distancia tentativa es menor que la distancia almacenada en el vector, actualizamos el vector con esta distancia tentativa. Es decir: Si dt(v<sub>i</sub>) &lt; D<sub>vi</sub> → D<sub>vi</sub> = dt(v<sub>i</sub>)</li>\r\n\t<li>Marcamos como completo el nodo a.</li>\r\n\t<li>Tomamos como próximo nodo actual el de menor valor en D (puede hacerse almacenando los valores en una cola de prioridad) y volvemos al paso 3 mientras existan nodos no marcados.</li>\r\n\t</ol>\r\n\t<p>Una vez terminado al algoritmo, D estará completamente lleno.</p>\r\n\r\n\t<h2><span class=\"mw-headline\" id=\"Complejidad\">Complejidad</span></h2>\r\n\t<p>Orden de complejidad del algoritmo: <i>O</i>(|<i>V</i>|<sup>2</sup>+|E|) = <i>O</i>(|<i>V</i>|<sup>2</sup>) sin utilizar cola de prioridad, <i>O</i>((|<i>E</i>|+|<i>V</i>|) log |<i>V</i>|) utilizando cola de prioridad (por ejemplo un montículo).</p>\r\n\t<p>Podemos estimar la complejidad computacional del algoritmo de Dijkstra (en términos de sumas y comparaciones). El <a href=\"http://es.wikipedia.org/wiki/Algoritmo\" title=\"Algoritmo\">algoritmo</a> realiza a lo más n-1 iteraciones, ya que en cada iteración se añade un vértice al conjunto distinguido. Para estimar el número total de operaciones basta estimar el número de operaciones que se llevan a cabo en cada iteración. Podemos identificar el vértice con la menor etiqueta entre los que no están en S<sub>k</sub> realizando n-1 comparaciones o menos. Después hacemos una suma y una comparación para actualizar la etiqueta de cada uno de los vértices que no están en S<sub>k</sub>. Por tanto, en cada iteración se realizan a lo sumo 2(n-1) operaciones, ya que no puede haber más de n-1 etiquetas por actualizar en cada iteración. Como no se realizan más de n-1 iteraciones, cada una de las cuales supone a lo más 2(n-1) operaciones, llegamos al siguiente teorema.</p>\r\n\t<p><i><b>TEOREMA:</b></i> El Algoritmo de Dijkstra realiza O(n<sup>2</sup>) operaciones (sumas y comparaciones) para determinar la longitud del camino más corto entre dos vértices de un grafo ponderado simple, conexo y no dirigido con n vértices.</p>\r\n\r\n</body>\r\n</html>");
		textPaneContenidoInformacion.setEditable(false);
		textPaneContenidoInformacion.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelInformacion.add(textPaneContenidoInformacion);
		
		JScrollPane scrollPaneInformacion = new JScrollPane(textPaneContenidoInformacion);
		scrollPaneInformacion.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelInformacion.add(scrollPaneInformacion);
		
		
		JPanel panelPseudocodigo = new JPanel();
		tbdPaneDerecha.addTab("Algoritmo", null, panelPseudocodigo, null);
		panelPseudocodigo.setLayout(new BoxLayout(panelPseudocodigo, BoxLayout.Y_AXIS));
		
		JLabel lblTituloAlgoritmo = new JLabel("Algoritmo de Dijkstra");
		lblTituloAlgoritmo.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblTituloAlgoritmo.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelPseudocodigo.add(lblTituloAlgoritmo);
		
		JTextPane textPaneContenidoAlgoritmo = new JTextPane();
		textPaneContenidoAlgoritmo.setContentType("text/html");
//		textPaneContenidoAlgoritmo.setText("<html>\r\n<body>\r\n\r\n<p>Estructura de datos auxiliar: Q = Estructura de datos <a href=\"http://es.wikipedia.org/wiki/Cola_de_prioridad_(estructura_de_datos)\" title=\"Cola de prioridad (estructura de datos)\" class=\"mw-redirect\">Cola de prioridad</a> (se puede implementar con un <a href=\"http://es.wikipedia.org/wiki/Mont%C3%ADculo_(programaci%C3%B3n)\" title=\"Montículo (programación)\" class=\"mw-redirect\">montículo</a>)</p>\r\n<br/>\r\n<pre>\r\n<b>DIJKSTRA</b> (Grafo <i>G</i>, nodo_fuente <i>s</i>)\r\n  <b>para</b> <i>u</i> ∈ <i>V[G]</i> <b>hacer</b>\r\n    distancia[<i>u</i>] = INFINITO\r\n    padre[<i>u</i>] = NULL\r\n  distancia[<i>s</i>] = 0\r\n  adicionar (cola, (s, distancia[<i>s</i>]))\r\n  <b>mientras que</b> cola no es vacía <b>hacer</b>\r\n    <i>u</i> = extraer_mínimo(cola)\r\n    <b>para</b> todos <i>v</i> ∈ adyacencia[<i>u</i>] <b>hacer</b>\r\n      <b>si</b> distancia[<i>v</i>] &gt; distancia[<i>u</i>] + peso (u, v) <b>hacer</b>\r\n        distancia[<i>v</i>] = distancia[<i>u</i>] + peso (u, v)\r\n        padre[<i>v</i>] = <i>u</i>\r\n        adicionar(cola,(v, distancia[<i>v</i>]))\r\n</pre>\r\n\r\n</body>\r\n</html>");
		textPaneContenidoAlgoritmo.setAlignmentX(Component.LEFT_ALIGNMENT);
		textPaneContenidoAlgoritmo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textPaneContenidoAlgoritmo.setEditable(false);
		textPaneContenidoAlgoritmo.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelPseudocodigo.add(textPaneContenidoAlgoritmo);
		
		JScrollPane scrollPaneAlgoritmo = new JScrollPane(textPaneContenidoAlgoritmo, 
														  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
														  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneAlgoritmo.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelPseudocodigo.add(scrollPaneAlgoritmo);
		
		
		this.setSize(1100, 900);
		splitPane_1.setResizeWeight(0.03d);
		splitPane_2.setResizeWeight(0.8d);
		splitPane_3.setResizeWeight(0.90d);
		
		JPanel panel_grafo = new JPanel();
		//splitPane_3.setLeftComponent(panel_grafo);
		panelAlgorimos.setSize(panelAlgorimos.getSize().width, 200);
		
		
        // create a JGraphT graph
//		this.g = new ListenableDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

		// add some sample data (graph manipulated via JGraphT)
        g.addVertex( "v1" );
        g.addVertex( "v2" );
        g.addVertex( "v3" );
        g.addVertex( "v4" );
        g.addVertex( "v5" );
        g.addVertex( "v6" );
        g.addVertex( "v7" );
        g.addVertex( "v8" );
        
        
        
		
        // create a visualization using JGraph, via an adapter
//		JGraphXAdapter<String, DefaultEdge> graph = new JGraphXAdapter<String, DefaultEdge>(g);
		
		g.addEdge( "v1", "v2" );
        g.addEdge( "v1", "v4" );
        g.addEdge( "v2", "v6" );
        g.addEdge( "v4", "v5" );
        g.addEdge( "v5", "v6" );
        g.addEdge( "v6", "v7" );
        g.addEdge( "v6", "v8" );
        g.addEdge( "v7", "v3" );
        g.addEdge( "v8", "v3" );
		
        
		graph.setDisconnectOnMove(false);
        graph.setAllowDanglingEdges(false);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        splitPane_3.setLeftComponent(graphComponent);
        this.setSize(400, 320);
        this.setVisible(true);

//        try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        graph.getModel().beginUpdate();
        double x = 50, y = 50;
        for (mxCell cell : graph.getVertexToCellMap().values()) {
            graph.getModel().setGeometry(cell, new mxGeometry(x, y, 50, 50));
            x += 70;
            if (x > 200) {
                x = 50;
                y += 70;
            }
        }
        graph.getModel().endUpdate();

    
        
        //
        // TEMP: Carga inicial de la info y el pseudocodigo. En el futuro, cada clase tendrá su resource almacenada y se le pedira el contenido
        //
        Dijkstra_old dijkstra = new Dijkstra_old();
        lblTituloInformacion.setText(dijkstra.getTitulo());
        lblTituloAlgoritmo.setText(dijkstra.getTitulo());
        textPaneContenidoInformacion.setPage(dijkstra.getDescripcion());
        textPaneContenidoAlgoritmo.setPage(dijkstra.getAlgoritmo());
        // END TEMP
        
        // TODO Auto-generated method stub
//		DijkstraShortestPath<String, DefaultEdge> d = new DijkstraShortestPath<String, DefaultEdge>(g, "v1", "v3");
//        List<DefaultEdge> recorrido = d.getPathEdgeList();
//        for( int i = 0; i < d.getPathLength(); i++)
//        	txtSalida.setText(txtSalida.getText() + "\n" + recorrido.get(i));
        
        buttonPlay.addActionListener(this);
	}	

	
    @Override
    public void init(  ) {


        
    }
    
    
    public void actionPerformed(ActionEvent e) {
    	
    	if(this.tempPlay) return;
    	
    	DijkstraShortestPath<String, DefaultEdge> d = new DijkstraShortestPath<String, DefaultEdge>(g, "v1", "v3");
        List<DefaultEdge> recorrido = d.getPathEdgeList();
        for( int i = 0; i < d.getPathLength(); i++)
//        	txtSalida.setText(txtSalida.getText() + "\n" + recorrido.get(i));
        	logger.info(recorrido.get(i));
    }
    

}
