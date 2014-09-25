package ar.com.taller2.papers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

public class AprendiendoGrafos2 extends JApplet {

	private static final long serialVersionUID = 5320477892293342036L;
	private static final Logger logger = LogManager.getLogger();
	
	private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
	
    private JScrollPane scrollPane;
    
    
    private JGraphModelAdapter m_jgAdapter;
	
	
	/**
	 * Create the applet.
	 */
	public AprendiendoGrafos2() {
		
		logger.info("BELEN GILA");
		
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
		mnHelp.add(mntmAcercaDe);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		getContentPane().add(splitPane_1);
		
		JPanel panelIzquierda = new JPanel();
		splitPane_1.setLeftComponent(panelIzquierda);
		panelIzquierda.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelAlgorimos = new JPanel();
		panelIzquierda.add(panelAlgorimos);
		panelAlgorimos.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton button_4 = new JButton("Prueba de Aciclidad");
		panelAlgorimos.add(button_4);
		
		JButton button_5 = new JButton("Recorrido topológico en Anchura");
		panelAlgorimos.add(button_5);
		
		JButton button_6 = new JButton("Cerradura Transitiva");
		panelAlgorimos.add(button_6);
		
		JButton button_7 = new JButton("Componentes Fuertemente Conexas");
		panelAlgorimos.add(button_7);
		
		JButton button_8 = new JButton("Algoritmo de Dijkstra");
		panelAlgorimos.add(button_8);
		
		JButton button_9 = new JButton("Algoritmo de Floyd");
		panelAlgorimos.add(button_9);
		
		JButton button_10 = new JButton("Algoritmo de Ford-Fulkerson");
		panelAlgorimos.add(button_10);
		
		JButton button_11 = new JButton("Recorrido en Profundidad");
		panelAlgorimos.add(button_11);
		
		JButton button_12 = new JButton("Recorrido en Anchura");
		panelAlgorimos.add(button_12);
		
		JButton button_13 = new JButton("Recorrido topológico en Profundidad");
		panelAlgorimos.add(button_13);
		
		JPanel panelModo = new JPanel();
		panelIzquierda.add(panelModo);
		panelModo.setLayout(new BoxLayout(panelModo, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("Modo");
		panelModo.add(label);
		
		JRadioButton radioButton = new JRadioButton("Aprendizaje");
		panelModo.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Autoevaluación");
		panelModo.add(radioButton_1);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_1.setRightComponent(splitPane_2);
		
		JPanel panelCentro = new JPanel();
		splitPane_2.setLeftComponent(panelCentro);
		panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
		
		JToolBar toolBar = new JToolBar();
		panelCentro.add(toolBar);
		
		JButton button = new JButton("<");
		toolBar.add(button);
		
		JButton button_1 = new JButton("<<");
		toolBar.add(button_1);
		
		JButton btnPlay = new JButton("PLAY");
		toolBar.add(btnPlay);
		
		JButton button_2 = new JButton(">");
		toolBar.add(button_2);
		
		JButton button_3 = new JButton(">>");
		toolBar.add(button_3);
		
		JSplitPane splitPane_3 = new JSplitPane();
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setAlignmentY(0.5f);
		splitPane_3.setAlignmentX(0.5f);
		panelCentro.add(splitPane_3);
		
		scrollPane = new JScrollPane();
		splitPane_3.setLeftComponent(scrollPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Salida:");
		splitPane_3.setRightComponent(textPane_1);
		
		JTabbedPane tbdPaneDerecha = new JTabbedPane(JTabbedPane.TOP);
		splitPane_2.setRightComponent(tbdPaneDerecha);
		
		JPanel panel = new JPanel();
		tbdPaneDerecha.addTab("Información", null, panel, null);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel label_1 = new JLabel("Título");
		panel.add(label_1);
		
		JTextPane textPane = new JTextPane();
		panel.add(textPane);
		
		JPanel panel_1 = new JPanel();
		tbdPaneDerecha.addTab("Algoritmo", null, panel_1, null);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel label_2 = new JLabel("Pseudocódigo");
		panel_1.add(label_2);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("lala");
		panel_1.add(textPane_2);
		
		this.setSize(900, 900);
		splitPane_1.setResizeWeight(0.03d);
		splitPane_2.setResizeWeight(0.8d);
		splitPane_3.setResizeWeight(0.90d);
		panelAlgorimos.setSize(panelAlgorimos.getSize().width, 200);
		
        // create a JGraphT graph
        ListenableGraph g = new ListenableDirectedGraph( DefaultEdge.class );

        // create a visualization using JGraph, via an adapter
        m_jgAdapter = new JGraphModelAdapter( g );
		
		JGraph jgraph = new JGraph( m_jgAdapter );

        adjustDisplaySettings( jgraph );
        getContentPane().add(jgraph);
        resize( DEFAULT_SIZE );

        // add some sample data (graph manipulated via JGraphT)
        g.addVertex( "v1" );
        g.addVertex( "v2" );
        g.addVertex( "v3" );
        g.addVertex( "v4" );

        g.addEdge( "v1", "v2" );
        g.addEdge( "v2", "v3" );
        g.addEdge( "v3", "v1" );
        g.addEdge( "v4", "v3" );

        // position vertices nicely within JGraph component
        positionVertexAt( "v1", 130, 40 );
        positionVertexAt( "v2", 60, 200 );
        positionVertexAt( "v3", 310, 230 );
        positionVertexAt( "v4", 380, 70 );

        // that's all there is to it!...
	}

	
    @Override
    public void init(  ) {


        
    }


    private void adjustDisplaySettings( JGraph jg ) {
        jg.setPreferredSize( DEFAULT_SIZE );

        Color  c        = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter( "bgcolor" );
        }
         catch( Exception e ) {}

        if( colorStr != null ) {
            c = Color.decode( colorStr );
        }

        jg.setBackground( c );
    }


    private void positionVertexAt( Object vertex, int x, int y ) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell( vertex );
        Map              attr = cell.getAttributes(  );
        Rectangle2D        b    = GraphConstants.getBounds( attr );

        GraphConstants.setBounds( attr, new Rectangle(x,y,(int)b.getWidth(),(int)b.getHeight()));

        Map cellAttr = new HashMap(  );
        cellAttr.put( cell, attr );
        m_jgAdapter.edit( cellAttr,  null, null, null );
    }
	
}
