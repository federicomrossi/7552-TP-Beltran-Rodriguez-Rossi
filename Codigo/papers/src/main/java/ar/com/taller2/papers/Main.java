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
import ar.com.taller2.papers.model.GraphModel;
import ar.com.taller2.papers.model.graphs.Dijkstra;
import ar.com.taller2.papers.view.GraphView;

public class Main extends JApplet {

	private static final long serialVersionUID = 5320477892293342036L;
	private HashMap<String, Component> componentMap = new HashMap<String, Component>();
	private static final Logger logger = LogManager.getLogger();
	
	private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 320);
    
    // TEMP
    private static boolean tempPlay = false;
    private JLabel lblTituloInformacion;
    // END TEMP
	
    /**
	 * Create the applet.
	 * @throws IOException 
	 */
	public Main() throws IOException {
		
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
		this.componentMap.put(menuBar.getName(), menuBar);
		
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
		buttonInit.setIcon(new ImageIcon(Main.class.getResource("/images/icon-arrow-ini-24.png")));
		toolBar.add(buttonInit);
		
		final JButton buttonPrevious = new JButton("");
		buttonPrevious.setEnabled(false);
		buttonPrevious.setIcon(new ImageIcon(Main.class.getResource("/images/icon-arrow-reverse-24.png")));
		toolBar.add(buttonPrevious);
		
		final JButton buttonNext = new JButton("");
		buttonNext.setEnabled(false);
		buttonNext.setIcon(new ImageIcon(Main.class.getResource("/images/icon-arrow-forward-24.png")));
	
		final JButton buttonEnd = new JButton("");
		buttonEnd.setEnabled(false);
		buttonEnd.setIcon(new ImageIcon(Main.class.getResource("/images/icon-arrow-end-24.png")));
		
		final JButton buttonPlay = new JButton("");
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
		buttonPlay.setIcon(new ImageIcon(Main.class.getResource("/images/icon-play-24.png")));
		toolBar.add(buttonPlay);
		
		toolBar.add(buttonNext);
		toolBar.add(buttonEnd);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setName("splitPane_3");
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setAlignmentY(0.5f);
		splitPane_3.setAlignmentX(0.5f);
		panelCentro.add(splitPane_3);
		this.componentMap.put(splitPane_3.getName(), splitPane_3);
		
		JTextPane txtSalida = new JTextPane();
		txtSalida.setText("Salida:");
		splitPane_3.setRightComponent(txtSalida);
		
		JTabbedPane tbdPaneDerecha = new JTabbedPane(JTabbedPane.TOP);
		splitPane_2.setRightComponent(tbdPaneDerecha);
		
		JPanel panelInformacion = new JPanel();
		tbdPaneDerecha.addTab("Información", null, panelInformacion, null);
		panelInformacion.setLayout(new BoxLayout(panelInformacion, BoxLayout.Y_AXIS));
		
		lblTituloInformacion = new JLabel("");
		lblTituloInformacion.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblTituloInformacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInformacion.add(lblTituloInformacion);
		
		JTextPane textPaneContenidoInformacion = new JTextPane();
		textPaneContenidoInformacion.setContentType("text/html");
		textPaneContenidoInformacion.setAlignmentX(Component.LEFT_ALIGNMENT);
		textPaneContenidoInformacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
				
		// Creamos nuevo documento
		GraphModel graphModel = new GraphModel();
		GraphView graphView = new GraphView(graphModel);
		this.iniciarNuevoDocumento(graphView.getGraphAdapter());
		
		
		//
        // TEMP: Carga inicial de la info y el pseudocodigo. En el futuro, cada clase tendrá su resource almacenada y se le pedira el contenido
        //
        Dijkstra dijkstra = new Dijkstra();
        lblTituloInformacion.setText(dijkstra.getTitulo());
        lblTituloAlgoritmo.setText(dijkstra.getTitulo());
        textPaneContenidoInformacion.setPage(dijkstra.getDescripcion());
        textPaneContenidoAlgoritmo.setPage(dijkstra.getAlgoritmo());
        // END TEMP
	}	

	
    @Override
    public void init(  ) {
    	// TODO Para utilizar al ensamblar con sitio web.
    }
    
    
    public void iniciarNuevoDocumento(JGraphXAdapter<String, DefaultEdge> graph) {
    	mxGraphComponent graphComponent = new mxGraphComponent(graph);
    	try {
    		this.logger.info(this.componentMap.get("splitPane_3"));
    	}
    	catch(Exception e) {
    		this.logger.info("PINCHO!");
    	}
    	
        ((JSplitPane) this.getComponentByName("splitPane_3")).setLeftComponent(graphComponent);
        
//        this.setSize(400, 320);
//        this.setVisible(true);
    }
    
    private void createComponentMap() {
        componentMap = new HashMap<String,Component>();
        Component[] components = this.getContentPane().getComponents();
        
        for (int i=0; i < components.length; i++) {
                componentMap.put(components[i].getName(), components[i]);
                this.logger.info(components[i].getName());
        }
	}
	
	public Component getComponentByName(String name) {
		if (componentMap.containsKey(name))
	    	return (Component) componentMap.get(name);
	   	else
	   		return null;
	}
}
