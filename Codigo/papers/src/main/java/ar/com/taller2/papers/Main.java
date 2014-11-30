package ar.com.taller2.papers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;

import ar.com.taller2.papers.adapters.JGraphXAdapter;
import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.model.Vertice;
import ar.com.taller2.papers.view.GraphView;
import ar.com.taller2.papers.view.PanelAlgoritmos;
import ar.com.taller2.papers.view.PanelIzquierda;
import ar.com.taller2.papers.view.PanelModo;
import ar.com.taller2.papers.view.PapersMenu;
import ar.com.taller2.papers.view.PapersToolbar;

public class Main extends JApplet {

	private static final long serialVersionUID = 5320477892293342036L;
	private static final Logger logger = LogManager.getLogger();
	
	private JGraphXAdapter<Vertice, DefaultEdge> adapter;
	
    
    // TEMP
    private static boolean tempPlay = false;
    private JLabel lblTituloInformacion;
    // END TEMP
    
    /* MODELO */
    //GraphModel graphModel = new GraphModel();
    
    GraphView graphView;
    
    /*Controller principal*/
    AprendiendoGrafos aprendiendoGrafos;
    
    /* REFERENCIAS A LOS COMPONENTES */
    
    PapersMenu menuBar = new PapersMenu();
    JSplitPane splitPane_1 = new JSplitPane();
    PanelIzquierda panelIzquierda = new PanelIzquierda();
    final PanelAlgoritmos panelAlgoritmos = new PanelAlgoritmos();
    PanelModo panelModo = new PanelModo();
    
    
    JPanel panelCentro = new JPanel();
    PapersToolbar toolBar = new PapersToolbar();
    
    
    JTextPane txtSalida = new JTextPane();
    
    JTabbedPane tbdPaneDerecha = new JTabbedPane(JTabbedPane.TOP);
    JPanel panelInformacion = new JPanel();
    JTextPane textPaneContenidoInformacion = new JTextPane();
    JScrollPane scrollPaneInformacion = new JScrollPane(textPaneContenidoInformacion);
    JPanel panelPseudocodigo = new JPanel();
    JLabel lblTituloAlgoritmo = new JLabel("");
    JTextPane textPaneContenidoAlgoritmo = new JTextPane();
    
    
    JScrollPane scrollPaneAlgoritmo = new JScrollPane(textPaneContenidoAlgoritmo, 
			  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
			  JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    JPanel panel_grafo = new JPanel();
    
	//GraphView graphView = new GraphView(graphModel);
	
	JSplitPane splitPane_3 = new JSplitPane();
	
    
    /**
	 * Create the applet.
	 * @throws IOException 
	 */
	public Main() throws IOException {
		
		setJMenuBar(menuBar);
		
		
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		
		getContentPane().add(splitPane_1);
		
		
		splitPane_1.setLeftComponent(panelIzquierda);
		panelIzquierda.setLayout(new GridLayout(0, 1, 0, 0));
		panelAlgoritmos.setBorder(new EmptyBorder(10, 10, 10, 10) );
		panelIzquierda.add(panelAlgoritmos);
		panelAlgoritmos.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		panelModo.setBorder(new EmptyBorder(10, 10, 10, 10) );
		panelIzquierda.add(panelModo);
		panelModo.setLayout(new BoxLayout(panelModo, BoxLayout.Y_AXIS));	
		
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_1.setRightComponent(splitPane_2);
		
		splitPane_1.setDividerSize(2);
		
		splitPane_2.setLeftComponent(panelCentro);
		panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
		
		panelCentro.setBackground(new Color(91,91,91));
		
		toolBar.setFloatable(false);
		panelCentro.add(toolBar);
		
		splitPane_2.setDividerSize(2);
		
		splitPane_3.setName("splitPane_3");
		splitPane_3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_3.setAlignmentY(0.5f);
		splitPane_3.setAlignmentX(0.5f);
		panelCentro.add(splitPane_3);
		
		splitPane_3.setDividerSize(2);
		
		txtSalida.setText("Salida: ");
		splitPane_3.setRightComponent(txtSalida);
		
		
        ImageIcon icon = new ImageIcon(Main.class.getResource("/images/FondoRec2.png"));
        JLabel label = new JLabel(icon);
		splitPane_3.setLeftComponent(label);
		
		splitPane_2.setRightComponent(tbdPaneDerecha);
		splitPane_2.setBackground(new Color(91,91,91));
		
		
		tbdPaneDerecha.addTab("Información", new ImageIcon(this.getClass().getResource("/images/info.png")), panelInformacion, null);
		panelInformacion.setLayout(new BoxLayout(panelInformacion, BoxLayout.Y_AXIS));
		
		lblTituloInformacion = new JLabel("");
		lblTituloInformacion.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblTituloInformacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelInformacion.add(lblTituloInformacion);
		
		
		textPaneContenidoInformacion.setContentType("text/html");
		textPaneContenidoInformacion.setAlignmentX(Component.LEFT_ALIGNMENT);
		textPaneContenidoInformacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textPaneContenidoInformacion.setEditable(false);
		textPaneContenidoInformacion.setBorder(new EmptyBorder(0, 0, 0, 0));
//		textPaneContenidoInformacion.setText("<html><body><img src=" + this.getClass().getResource("/images/FondoAzul.png") + " alt='Smiley face'></body></html>");
//		textPaneContenidoInformacion.setText("<html><body background=" + this.getClass().getResource("/images/FondoAzul.png") + " alt='Smiley face'></body></html>");
		textPaneContenidoInformacion.setText("<html><body bgcolor=#5b5b5b style='margin: 0 auto; text-align: center; width: 100%; height:100%;'><img style='vertical-align: middle' src=" + this.getClass().getResource("/images/info-512.png") + " alt='Info'></body></html>");
		panelInformacion.add(textPaneContenidoInformacion);
		
		
		scrollPaneInformacion.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelInformacion.add(scrollPaneInformacion);
		
		
		tbdPaneDerecha.addTab("Algoritmo", new ImageIcon(this.getClass().getResource("/images/Terminalicon2.png")), panelPseudocodigo, null);
		panelPseudocodigo.setLayout(new BoxLayout(panelPseudocodigo, BoxLayout.Y_AXIS));
		
		
		lblTituloAlgoritmo.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblTituloAlgoritmo.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelPseudocodigo.add(lblTituloAlgoritmo);
		
		
		textPaneContenidoAlgoritmo.setContentType("text/html");
		textPaneContenidoAlgoritmo.setAlignmentX(Component.LEFT_ALIGNMENT);
		textPaneContenidoAlgoritmo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textPaneContenidoAlgoritmo.setEditable(false);
		textPaneContenidoAlgoritmo.setBorder(new EmptyBorder(0, 0, 0, 0));
		textPaneContenidoAlgoritmo.setText("<html><body bgcolor=#5b5b5b style='margin: 0 auto; text-align: center; width: 100%; height:100%;'><img style='vertical-align: middle' src=" + this.getClass().getResource("/images/code-icon.png") + " alt='Pseudo'></body></html>");
		panelPseudocodigo.add(textPaneContenidoAlgoritmo);
		
		
		scrollPaneAlgoritmo.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelPseudocodigo.add(scrollPaneAlgoritmo);
		
		
		this.setSize(1100, 900);
		splitPane_1.setResizeWeight(0.03d);
		splitPane_2.setResizeWeight(0d);
		splitPane_3.setResizeWeight(0.90d);
		
		panelAlgoritmos.setSize(panelAlgoritmos.getSize().width, 200);
		
		aprendiendoGrafos = new AprendiendoGrafos(this);
		
		
		//
        // TEMP: Carga inicial de la info y el pseudocodigo. En el futuro, cada clase tendrá su resource almacenada y se le pedira el contenido
        //
//        Dijkstra_old dijkstra = new Dijkstra_old();
//        lblTituloInformacion.setText(dijkstra.getTitulo());
//        lblTituloAlgoritmo.setText(dijkstra.getTitulo());
//        textPaneContenidoInformacion.setPage(dijkstra.getDescripcion());
//        textPaneContenidoAlgoritmo.setPage(dijkstra.getAlgoritmo());
        // END TEMP
        
//        try {
//            // Set System L&F
//        UIManager.setLookAndFeel(
//            UIManager.getSystemLookAndFeelClassName());
//	    } 
//	    catch (Exception e) {
//	       // handle exception
//	    }
	}	

	
    @Override
    public void init(  ) {
    	// TODO Para utilizar al ensamblar con sitio web.
    }
    
    public void mostrarMensajeEquivocacion(String mensaje) {
    	Frame padre = (Frame)SwingUtilities.windowForComponent(this.panelCentro);
    	Alerta alerta = new Alerta(padre, mensaje);
    }
    
    
    public void setGraph(ListenableGraph<Vertice, DefaultEdge> graph) {
    	this.adapter = new JGraphXAdapter<Vertice, DefaultEdge>(graph);
    	graphView = new GraphView(adapter);
    	splitPane_3.setLeftComponent(graphView);
    	//ordernarVertices();
//        this.setSize(400, 320);
//        this.setVisible(true);
    }
    
    public JGraphXAdapter<Vertice, DefaultEdge> getGraph(){
    	return this.adapter;
    }
    
    /**
     * Agrega el paso realizado a la salida
     */
    public void agregarASalida(String paso) {
    	txtSalida.setText(txtSalida.getText() + "-" + paso);
    }
    
    /**
     * Borra lo que hay en la pantalla de salida 
     */
    public void borrarSalida() {
    	txtSalida.setText("Salida: ");
    }
    
    /**
     * Quita el paso realizado a la salida
     */
    public void quitarDeSalida() {
    	
    }
    
    /**
     * Muestra la info del algoritmo en cuestion
     */
    public void mostrarInfoAlgoritmo(String titulo, URL descripcion, URL algoritmo) {
		lblTituloInformacion.setText(titulo);
		lblTituloAlgoritmo.setText(titulo);
		try {
			textPaneContenidoInformacion.setPage(descripcion);
			textPaneContenidoAlgoritmo.setPage(algoritmo);
		} catch (IOException e) {
			e.printStackTrace();
}
    }
    
    /**
     * Listener de los clicks en el grafo
     */
    public void addAdapterVertexListener() {
    	adapter.addVertexListener();
    }

    public void removeAdapterVertexListener() {
    	adapter.removeVertexListener();
    }
    
    /**
     * Obtiene el nombre del vertice seleccionado
     */
    public String getVerticeSeleccionado() {
    	return adapter.getVerticeSeleccionado();
    }
    
    /**
     * La idea es que solo actualice los cambios y no mueva los vertices de posicion
     */
    public void actualizar(){
    	this.graphView.actualizar();
    }
    
    /**
     * Redibuja el grafo desde cero
     */
    public void rerenderGrafo() {
    	this.graphView.ordernarVertices();
	}
    
    public void desbloquearPanel() {
    	this.panelAlgoritmos.desbloquearTodo();
    	this.toolBar.desbloquearTodo();
    	this.panelModo.desbloquearTodo();
    }
    
    public void bloquearPanel() {
    	this.panelAlgoritmos.bloquearTodo();
    	this.panelModo.bloquearTodo();
    }
    
    
    /* Listeners del MENU */
    
    public void addMenuArchivoNuevoGrafoOrientadoActionListener(ActionListener a){
		menuBar.addArchivoNuevoGrafoOrientadoActionListener(a);
	}
	
	public void addMenuArchivoNuevoGrafoNoOrientadoActionListener(ActionListener a){
		menuBar.addArchivoNuevoGrafoNoOrientadoActionListener(a);
	}
	
	public void addMenuArchivoAbrirActionListener(ActionListener a){
		menuBar.addArchivoAbrirActionListener(a);
	}
	
	public void addMenuArchivoGuardarActionListener(ActionListener a){
		menuBar.addArchivoGuardarActionListener(a);
	}
	
	public void addMenuArchivoGuardarComoActionListener(ActionListener a){
		menuBar.addArchivoGuardarComoActionListener(a);
	}
	
	public void addMenuEditarNuevoVerticeActionListener(ActionListener a){
		menuBar.addEditarNuevoVerticeActionListener(a);
	}
	
	public void addNewEdgeListener(mxIEventListener a){
		graphView.addNewEdgeListener(a);
	}
	
	public void addMenuAyudaAcercaDeActionListener(ActionListener a){
		menuBar.addAyudaAcercaDeActionListener(a);
	}
	
	public void addMenuAyudaGuiaUsuarioActionListener(ActionListener a){
		menuBar.addAyudaGuiaUsuarioActionListener(a);
	}
	
	public void removeMenuArchivoNuevoGrafoOrientadoActionListener(ActionListener a){
		menuBar.removeArchivoNuevoGrafoOrientadoActionListener(a);
	}
	
	public void removeMenuArchivoNuevoGrafoNoOrientadoActionListener(ActionListener a){
		menuBar.removeArchivoNuevoGrafoNoOrientadoActionListener(a);
	}
	
	public void removeMenuArchivoAbrirActionListener(ActionListener a){
		menuBar.removeArchivoAbrirActionListener(a);
	}
	
	public void removeMenuArchivoGuardarActionListener(ActionListener a){
		menuBar.removeArchivoGuardarActionListener(a);
	}
	
	public void removeMenuArchivoGuardarComoActionListener(ActionListener a){
		menuBar.removeArchivoGuardarComoActionListener(a);
	}
	
	public void removeMenuEditarNuevoVerticeActionListener(ActionListener a){
		menuBar.removeEditarNuevoVerticeActionListener(a);
	}
	
	public void removeMenuAyudaAcercaDeActionListener(ActionListener a){
		menuBar.removeAyudaAcercaDeActionListener(a);
	}
	
	public void removeMenuAyudaGuiaUsuarioActionListener(ActionListener a){
		menuBar.removeAyudaGuiaUsuarioActionListener(a);
	}
	
	/* Listeners de los Algoritmos */
	
	public void addAlgoritmosPruebaAciclidadItemListener(ItemListener il){
		panelAlgoritmos.addPruebaAciclidadItemListener(il);
	}
	
	public void addAlgoritmosRecorridoTopologicoAnchuraItemListener(ItemListener l){
		panelAlgoritmos.addRecorridoTopologicoAnchuraItemListener(l);
	}
	
	public void addAlgoritmosCerraduraTransitivaItemListener(ItemListener l){
		panelAlgoritmos.addCerraduraTransitivaItemListener(l);
	}
	
	public void addAlgoritmosComponentesFuertementeConexasItemListener(ItemListener l){
		panelAlgoritmos.addComponentesFuertementeConexasItemListener(l);
	}
	
	public void addAlgoritmosDijkstraItemListener(ItemListener l){
		panelAlgoritmos.addDijkstraItemListener(l);
	}
	
	public void addAlgoritmosFloydItemListener(ItemListener l){
		panelAlgoritmos.addFloydItemListener(l);
	}
	
	public void addAlgoritmosFordFulkersonItemListener(ItemListener l){
		panelAlgoritmos.addFordFulkersonItemListener(l);
	}
	
	public void addAlgoritmosRecorridoProfundidadItemListener(ItemListener l){
		panelAlgoritmos.addRecorridoProfundidadItemListener(l);
	}
	
	public void addAlgoritmosRecorridoAnchuraItemListener(ItemListener l){
		panelAlgoritmos.addRecorridoAnchuraItemListener(l);
	}
	
	public void addAlgoritmosRecorridoTopologicoProfundidadItemListener(ItemListener l){
		panelAlgoritmos.addRecorridoTopologicoProfundidadItemListener(l);
	}
	
	public void addAlgoritmosArbolExpansionCosteMinimoItemListener(ItemListener l){
		panelAlgoritmos.addArbolExpansionCosteMinimoItemListener(l);
	}
	
	public void removeAlgoritmosPruebaAciclidadItemListener(ItemListener il){
		panelAlgoritmos.removePruebaAciclidadItemListener(il);
	}
	
	public void removeAlgoritmosRecorridoTopologicoAnchuraItemListener(ItemListener l){
		panelAlgoritmos.removeRecorridoTopologicoAnchuraItemListener(l);
	}
	
	public void removeAlgoritmosCerraduraTransitivaItemListener(ItemListener l){
		panelAlgoritmos.removeCerraduraTransitivaItemListener(l);
	}
	
	public void removeAlgoritmosComponentesFuertementeConexasItemListener(ItemListener l){
		panelAlgoritmos.removeComponentesFuertementeConexasItemListener(l);
	}
	
	public void removeAlgoritmosDijkstraItemListener(ItemListener l){
		panelAlgoritmos.removeDijkstraItemListener(l);
	}
	
	public void removeAlgoritmosFloydItemListener(ItemListener l){
		panelAlgoritmos.removeFloydItemListener(l);
	}
	
	public void removeAlgoritmosFordFulkersonItemListener(ItemListener l){
		panelAlgoritmos.removeFordFulkersonItemListener(l);
	}
	
	public void removeAlgoritmosRecorridoProfundidadItemListener(ItemListener l){
		panelAlgoritmos.removeRecorridoProfundidadItemListener(l);
	}
	
	public void removeAlgoritmosRecorridoAnchuraItemListener(ItemListener l){
		panelAlgoritmos.removeRecorridoAnchuraItemListener(l);
	}
	
	public void removeAlgoritmosRecorridoTopologicoProfundidadItemListener(ItemListener l){
		panelAlgoritmos.removeRecorridoTopologicoProfundidadItemListener(l);
	}
	
	public void removeAlgoritmosArbolExpansionCosteMinimoItemListener(ItemListener l){
		panelAlgoritmos.removeArbolExpansionCosteMinimoItemListener(l);
	}
	
	/*Listener de Modo */
	public void addModoAprendizajeItemListener(ItemListener l){
		panelModo.addAprendizajeItemListener(l);
	}
	
	public void addModoEvaluacionItemListener(ItemListener l){
		panelModo.addEvaluacionItemListener(l);
	}
	
	public void removeModoAprendizajeItemListener(ItemListener l){
		panelModo.removeAprendizajeItemListener(l);
	}
	
	public void removeModoEvaluacionItemListener(ItemListener l){
		panelModo.removeEvaluacionItemListener(l);
	}
	
	/* Listeners del ToolBar */
	public void addToolbarInitActionListener(ActionListener l){
		toolBar.addInitActionListener(l);
	}
	
	public void addToolbarPreviousActionListener(ActionListener l){
		toolBar.addPreviousActionListener(l);
	}
	
	public void addToolbarNextActionListener(ActionListener l){
		toolBar.addNextActionListener(l);
	}
	
	public void addToolbarEndActionListener(ActionListener l){
		toolBar.addEndActionListener(l);
	}
	
	public void addToolBarPlayActionListener(ActionListener l){
		toolBar.addPlayActionListener(l);
	}
	
	public void removeToolBarInitActionListener(ActionListener l){
		toolBar.removeInitActionListener(l);
	}
	
	public void removeToolBarPreviousActionListener(ActionListener l){
		toolBar.removePreviousActionListener(l);
	}
	
	public void removeToolBarNextActionListener(ActionListener l){
		toolBar.removeNextActionListener(l);
	}
	
	public void removeToolBarEndActionListener(ActionListener l){
		toolBar.removeEndActionListener(l);
	}
	
	public void removeToolBarPlayActionListener(ActionListener l){
		toolBar.removePlayActionListener(l);
	}
	
}
