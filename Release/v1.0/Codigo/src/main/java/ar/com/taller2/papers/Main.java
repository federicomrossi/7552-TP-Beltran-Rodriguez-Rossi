package ar.com.taller2.papers;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.ListenableGraph;

import ar.com.taller2.papers.adapters.JGraphXAdapter;
import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.libraries.FileManager;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Selectable;
import ar.com.taller2.papers.model.Vertice;
import ar.com.taller2.papers.view.GraphView;
import ar.com.taller2.papers.view.PanelAlgoritmos;
import ar.com.taller2.papers.view.PanelInformacion;
import ar.com.taller2.papers.view.PanelIzquierda;
import ar.com.taller2.papers.view.PanelModo;
import ar.com.taller2.papers.view.PanelPseudocodigo;
import ar.com.taller2.papers.view.PapersMenu;
import ar.com.taller2.papers.view.PapersToolbar;

import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.util.mxEventSource.mxIEventListener;

public class Main extends JFrame {

	private static final long serialVersionUID = 5320477892293342036L;
	private static final Logger logger = LogManager.getLogger();
	
	private JGraphXAdapter<Vertice, Arista> adapter;
	private mxParallelEdgeLayout layout;
	
    
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
    
    
    JTextArea txtSalida = new JTextArea();
    JScrollPane jsp = new JScrollPane(txtSalida);
    
    
    JTabbedPane tbdPaneDerecha = new JTabbedPane(JTabbedPane.TOP);
    PanelInformacion panelInformacion = new PanelInformacion();
    
    PanelPseudocodigo panelPseudocodigo = new PanelPseudocodigo();
    
    JPanel panel_grafo = new JPanel();
    
	//GraphView graphView = new GraphView(graphModel);
	
	JSplitPane splitPane_3 = new JSplitPane();
	
	boolean modoEdicion =false;
	
	FileManager fileManager;
	
    
    /**
	 * Create the applet.
	 * @throws IOException 
	 */
	public Main() {
		super("Graferator");
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
		splitPane_3.setRightComponent(jsp);
		
        ImageIcon icon = new ImageIcon(Main.class.getResource("/images/FondoRec2.png"));
        JLabel label = new JLabel(icon);
		splitPane_3.setLeftComponent(label);
		
		splitPane_2.setRightComponent(tbdPaneDerecha);
		splitPane_2.setBackground(new Color(91,91,91));
		
		
		//
		// Panel de Informacion
		//
		
		tbdPaneDerecha.addTab("Información", new ImageIcon(this.getClass().getResource("/images/info.png")), panelInformacion, null);
		panelInformacion.setLayout(new BoxLayout(panelInformacion, BoxLayout.Y_AXIS));
		
		//
		// Fin panel de informacion
		
		//
		// Panel de Pseudocodigo
		//
		

		tbdPaneDerecha.addTab("Algoritmo", new ImageIcon(this.getClass().getResource("/images/Terminalicon2.png")), panelPseudocodigo, null);
		panelPseudocodigo.setLayout(new BoxLayout(panelPseudocodigo, BoxLayout.Y_AXIS));
		
		//
		// Fin panel de pseudocodigo
		

		this.setSize(1100, 900);
		splitPane_1.setResizeWeight(0.03d);
		splitPane_2.setResizeWeight(0d);
		splitPane_3.setResizeWeight(0.90d);
		
		panelAlgoritmos.setSize(panelAlgoritmos.getSize().width, 200);
		
		aprendiendoGrafos = new AprendiendoGrafos(this);

		this.setName("Graferator");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}	

	public static void main(String[] args){
		//JFrame f = new JFrame();
		Main m = new Main();
	}
	
	/*
    @Override
    public void init(  ) {
    	// TODO Para utilizar al ensamblar con sitio web.
    }*/
    
    public void mostrarMensajeEquivocacion(String mensaje) {
    	Frame padre = (Frame)SwingUtilities.windowForComponent(this.panelCentro);
    	Alerta alerta = new Alerta(padre, mensaje);
    }
    
    
    public void setGraph(ListenableGraph<Vertice, Arista> graph,boolean dirigido) {
    	this.adapter = new JGraphXAdapter<Vertice, Arista>(graph);
    	graphView = new GraphView(adapter,dirigido);
    	//panel_grafo.add(graphView);
    	splitPane_3.setLeftComponent(graphView);
    	layout = new mxParallelEdgeLayout(adapter);
        layout.execute(adapter.getDefaultParent());
        this.actualizar();
        // Selecciona el primer algoritmo de la lista de posibles
       //this.panelAlgoritmos.seleccionarPrimero();
        this.panelModo.seleccionarPrimero();
        this.setModoEdicion(true);
    	//ordernarVertices();
//        this.setSize(400, 320);
//        this.setVisible(true);
    }
    
    public JGraphXAdapter<Vertice, Arista> getGraph(){
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
    	panelInformacion.setTitulo(titulo);
		panelPseudocodigo.setTitulo(titulo);
		try {
			panelInformacion.setInformacion(descripcion);
			panelPseudocodigo.setAlgoritmo(algoritmo);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Listener de los clicks en el grafo
     */
    public void addAdapterVertexListener() {
    	adapter.addVertexListener(aprendiendoGrafos);
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
    
    public void runLayout(){
    	this.graphView.runLayout();
    }
    
    public FileManager getFileManager() {
    	return this.fileManager;
    }
    
    public void setFileManager(FileManager fileManager) {
    	this.fileManager = fileManager;
    }
    
    public void setPseudocodeCurrent(Selectable linea) {
    	this.panelPseudocodigo.setPseudocodeCurrent(linea);
    }
    
    public void desbloquearPanel() {
    	this.panelAlgoritmos.desbloquearTodo();
    	//this.toolBar.desbloquearTodo();
    	this.panelModo.desbloquearTodo();
    }
    
    public void desbloquearPanelEdicion() {
    	this.panelAlgoritmos.bloquearTodo();
    	//this.toolBar.desbloquearTodo();
    	this.panelModo.desbloquearTodo();
    }
    
    public void bloquearPanel() {
    	this.panelAlgoritmos.bloquearTodo();
    	this.panelModo.bloquearTodo();
    }
    
    public void desbloquearToolbar(){
    	this.toolBar.desbloquearToolbar();
    }
    
    public void bloquearToolbar(){
    	this.toolBar.bloquearToolbar();
    }
    
    public void bloquearToolbarFin() {
    	this.toolBar.bloquearFin();
    }
    
    public void bloquearOrientado(){
    	this.panelAlgoritmos.bloquearDirigido();
    }
    public void bloquearNoOrientado(){
    	this.panelAlgoritmos.bloquearNoDirigido();
    }

    public void bloquearMenuGuardar() {
    	this.menuBar.bloquearMenu();
    }
    
    public void desbloquearMenuGuardar() {
    	this.menuBar.desbloquearMenu();
    }
    
    /* Eventos trigger de MENU */
    
    public void MenuArchivoNuevoGrafoOrientadoVacioTriggerAction() {
    	menuBar.archivoNuevoGrafoOrientadoVacioTriggerAction();
    }
    
    public void MenuArchivoNuevoGrafoNoOrientadoVacioTriggerAction() {
    	menuBar.archivoNuevoGrafoNoOrientadoVacioTriggerAction();
    }
    
    /* Listeners del MENU */
    
    public void addMenuArchivoNuevoGrafoOrientadoAleatorioActionListener(ActionListener a){
		menuBar.addArchivoNuevoGrafoOrientadoAleatorioActionListener(a);
	}
    
    public void addMenuArchivoNuevoGrafoOrientadoVacioActionListener(ActionListener a){
		menuBar.addArchivoNuevoGrafoOrientadoVacioActionListener(a);
	}
	
	public void addMenuArchivoNuevoGrafoNoOrientadoAleatorioActionListener(ActionListener a){
		menuBar.addArchivoNuevoGrafoNoOrientadoAleatorioActionListener(a);
	}
	
	public void addMenuArchivoNuevoGrafoNoOrientadoVacioActionListener(ActionListener a){
		menuBar.addArchivoNuevoGrafoNoOrientadoVacioActionListener(a);
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
	
	public void addMenuEditarNuevaAristaActionListener(ActionListener a){
		menuBar.addEditarNuevaAristaActionListener(a);
	}
	
	public void addNewEdgeListener(mxIEventListener a){
		graphView.addNewEdgeListener(a);
	}
	
	public void addMoveCellListener(mxIEventListener a){
		graphView.addMoveCellListener(a);
	}
	
	public void addChangeWeightListener(mxIEventListener a){
		graphView.addChangeWeightListener(a);
	}
	
	public void addNewVertexListener(MouseListener m){
		graphView.getGraphControl().addMouseListener(m);
	}
	
	public void addSourceDestSelectionListener(){
		adapter.addSourceDestSelectionListener(aprendiendoGrafos);
		this.mostrarMensajeEquivocacion("Seleccione los vértices de Origen y Destino para iniciar el algoritmo.");
	}
	
	public void addSourceSelectionListener(){
		adapter.addSourceSelectionListener(aprendiendoGrafos);
		this.mostrarMensajeEquivocacion("Seleccione el vértice de Origen para iniciar el algoritmo.");
	}
	
	public void removeSourceDestSelectionListener(){
		adapter.removeSourceDestSelectionListener();
		aprendiendoGrafos.getModelo().setSourceVertex(null);
		aprendiendoGrafos.getModelo().setDestVertex(null);
	}
	
	public void removeSourceSelectionListener(){
		adapter.removeSourceSelectionListener();
		aprendiendoGrafos.getModelo().setSourceVertex(null);
		aprendiendoGrafos.getModelo().setDestVertex(null);
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
	
	public void addModoEdicionItemListener(ItemListener l){
		panelModo.addEdicionItemListener(l);
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


	public void showMatrixInput(TableModel data) {
		Frame padre = (Frame)SwingUtilities.windowForComponent(this.panelCentro);
		InputMatrixDialog dialog = new InputMatrixDialog(padre, data, "Ingrese el resultado", aprendiendoGrafos);
	}


	public void installKeyboardListener() {
		adapter.addCellRemovedListener(aprendiendoGrafos);
		new mxKeyboardHandler( graphView);
		
	}


	public void setModoEdicion(boolean b) {
		modoEdicion = b;
		//graphView.setEnabled(b);
		graphView.setModoEdicion(b);
		menuBar.setModoEdicion(b);
		toolBar.setModoEvaluacion(b);
		panelAlgoritmos.setModoEdicion(b,aprendiendoGrafos.getModelo().getTipo());
	}

	
	public boolean isModoEdicion(){
		return modoEdicion;
	}



	public void rerenderGrafo(Vertice v, int i, int j) {
		graphView.ordernarVertices(v, i, j);
		
	}


	public void rerenderGrafo(Vertice v) {
		graphView.ordernarVertices(v);
		
	}


	public void activarSourceDestListeners(boolean b) {
		adapter.activarSourceDestListeners(b);
		
	}
	


	
}
