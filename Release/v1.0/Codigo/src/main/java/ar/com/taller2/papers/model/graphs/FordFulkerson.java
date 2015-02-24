package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.ListenableDirectedGraph;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Selectable;
import ar.com.taller2.papers.model.Vertice;

public class FordFulkerson extends GraphAlgorithm {


	private Vertice inicio;
	private Vertice fin;
	private int indiceSiguientePaso;
	private List<Arista> camino = new ArrayList<Arista>();
	private List<Selectable> items = new ArrayList<Selectable>();

	
	private List<Pair> recorrido;
	
	
	/* cosas del algoritmo */
	private DirectedGraph<Vertice, Arista> network; // our network
	private double epsilon; // tolerance (DEFAULT_EPSILON or user-defined)
	private int currentSource; // current source vertex
	private int currentSink; // current sink vertex
    private Map<Arista, Double> maximumFlow; // current maximum flow
    private Double maximumFlowValue; // current maximum flow value
    private int numNodes; // number of nodes in the network
    private Map<Vertice, Integer> indexer; // mapping from vertices to their indexes	                                     // in the internal representation
    private List<Node> nodes; // internal representation of the network 
	  
    public static final double DEFAULT_EPSILON = 0.000000001;
	 
 // Data para modo evaluacion	
    String[][] data;
	String[] cols;
	TableModel model;
    
	
	
	
	private void createItemList() {
		//TODO falta agregar el algoritmo
	}
	
	public FordFulkerson(ListenableDirectedGraph<Vertice, Arista> graph){
		this.network =graph;
		this.epsilon = DEFAULT_EPSILON;
	}
	
	public void iniciar() {
		Logger.getLogger(getClass().getSimpleName()).info("Inicie el algoritmo");
		createItemList();
        
		recorrido = new ArrayList<Pair>();

        currentSource = -1;
        currentSink = -1;
        maximumFlow = null;
        maximumFlowValue = null;

        
        buildInternalNetwork();
        calculateMaximumFlow(inicio, fin);
        
        fillColsAndRows(cols,data);
		
		model = new DefaultTableModel(data,cols);
        
        Logger.getLogger(getClass().getSimpleName()).info("Flow: " + maximumFlowValue);
	}

	
	private void deseleccionar(int indiceComponente) {
		if (indiceComponente >= 0 && indiceComponente < recorrido.size()) {
			List<Arista> vS = recorrido.get(indiceComponente).aristas;
			Iterator<Arista> it = vS.iterator();
			while(it.hasNext()){
				Arista v = it.next();
				v.select(false);
			}			
		}
	}

	private void seleccionar(int indiceComponente) {
		if (indiceComponente >= 0 && indiceComponente < recorrido.size()) {
			List<Arista> vS = recorrido.get(indiceComponente).aristas;
			Iterator<Arista> it = vS.iterator();
			while(it.hasNext()){
				Arista v = it.next();
				v.select(true);
			}			
		}
	}
	
	
	public String siguiente() throws NextStepNotExistsException {
		Logger.getLogger(getClass().getSimpleName()).info("Siguiente");
		if(this.indiceSiguientePaso < this.recorrido.size()) {
			deseleccionar(this.indiceSiguientePaso - 1);
			seleccionar(this.indiceSiguientePaso);
			return recorrido.get(indiceSiguientePaso++).result;
			
			//Selectable v = this.items.get(this.indiceSiguientePaso++);
			//v.select(true);
		}else {
			throw new NextStepNotExistsException("Algoritmo finalizado");
		}
	}

	public String anterior() {
		Logger.getLogger(getClass().getSimpleName()).info("Anterior");
		if(this.indiceSiguientePaso - 1 >= 0) {
			//Selectable v = this.items.get(--this.indiceSiguientePaso);
			//v.select(false);
			deseleccionar(--this.indiceSiguientePaso);
			seleccionar(this.indiceSiguientePaso - 1);
			return recorrido.get(indiceSiguientePaso).result;
		}

		return "";
	}

	public void terminar() {
		deseleccionar(this.indiceSiguientePaso - 1);
		this.indiceSiguientePaso = 0;
	}

	public String principio() {
		Logger.getLogger(getClass().getSimpleName()).info("Principio");
		deseleccionar(this.indiceSiguientePaso - 1);
		this.indiceSiguientePaso = 0;
		return "";
	}

	public String fin() {
		Logger.getLogger(getClass().getSimpleName()).info("Fin");
		deseleccionar(this.indiceSiguientePaso - 1);
		this.indiceSiguientePaso = recorrido.size()-1;
		seleccionar(this.indiceSiguientePaso);
		return recorrido.get(indiceSiguientePaso++).result;
	}

	public boolean cumpleCondicionesIniciales() {
		return (inicio != null && fin != null);
	}

	public String getCondicionesIniciales() {
		return "Debe seleccionar el Origen y Destino";
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.recorrido.size());
		//return (this.indiceSiguientePaso < this.items.size());
//		return (this.indiceSiguientePaso < this.camino.size());
	}

	public Boolean isSourceDest() {
		return Boolean.TRUE;
	}

	public void setSource(Vertice v) {
		this.inicio=v;
	}

	public void setDest(Vertice v) {
		this.fin=v;
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/ford-fulkerson-pseudocode.txt");
	}

	public String getTitulo() {
		return "Algoritmo de Flujos Máximos";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/ford-fulkerson-info.html");
	}

	public Boolean isCorrect(Resultado r) throws NextStepNotExistsException {
		Logger.getLogger(this.getClass().getName()).info("Siguiente Evaluación");
		if(this.indiceSiguientePaso < this.recorrido.size()) {
			
	    	List<Arista> aristas = this.recorrido.get(this.indiceSiguientePaso).aristas;
	    	List<Arista> res = r.getAristas();
	    	if(!res.containsAll(aristas) || !aristas.containsAll(res)){
	    		deseleccionarTodo();
	    		return Boolean.FALSE;
	    	}
	    	String v = this.recorrido.get(this.indiceSiguientePaso++).result;
	    	
	    	String[] s = v.split("\\n");
	    	if(s[2].equals("Flujo parcial: "+Double.parseDouble(model.getValueAt(0,0).toString())) &&
	    			s[3].equals("Flujo Total: "+Double.parseDouble(model.getValueAt(0,1).toString()))){
	    		return true;
	    	}
	    	deseleccionarTodo();
	    	this.indiceSiguientePaso--;
	    	return false;
		}else{
			deseleccionarTodo();
			throw new NextStepNotExistsException("No hay más pasos");
		}
	}

	public Selectable getCurrentItem() {
		if (this.indiceSiguientePaso - 1 >= 0) 
			return this.items.get(this.indiceSiguientePaso - 1);
		return this.items.get(this.indiceSiguientePaso);
	}

	
	//~ Methods ----------------------------------------------------------------

    // converting the original network into internal more convenient format
    private void buildInternalNetwork()
    {
        numNodes = network.vertexSet().size();
        nodes = new ArrayList<Node>();
        Iterator<Vertice> it = network.vertexSet().iterator();
        indexer = new HashMap<Vertice, Integer>();
        for (int i = 0; i < numNodes; i++) {
            Vertice currentNode = it.next();
            nodes.add(new Node(currentNode));
            indexer.put(currentNode, i);
        }
        for (int i = 0; i < numNodes; i++) {
            Vertice we = nodes.get(i).prototype;
            for (Arista e : network.outgoingEdgesOf(we)) {
                Vertice he = network.getEdgeTarget(e);
                int j = indexer.get(he);
                Arc e1 = new Arc(i, j, network.getEdgeWeight(e), e);
                Arc e2 = new Arc(j, i, 0.0, null);
                e1.reversed = e2;
                e2.reversed = e1;
                nodes.get(i).outgoingArcs.add(e1);
                nodes.get(j).outgoingArcs.add(e2);
            }
        }
    }

    /**
     * Sets current source to <tt>source</tt>, current sink to <tt>sink</tt>,
     * then calculates maximum flow from <tt>source</tt> to <tt>sink</tt>. Note,
     * that <tt>source</tt> and <tt>sink</tt> must be vertices of the <tt>
     * network</tt> passed to the constructor, and they must be different.
     *
     * @param source source vertex
     * @param sink sink vertex
     */
    public void calculateMaximumFlow(
        Vertice source,
        Vertice sink)
    {
        if (!network.containsVertex(source)) {
            throw new IllegalArgumentException(
                "invalid source (null or not from this network)");
        }
        if (!network.containsVertex(sink)) {
            throw new IllegalArgumentException(
                "invalid sink (null or not from this network)");
        }

        if (source.equals(sink)) {
            throw new IllegalArgumentException("source is equal to sink");
        }

        currentSource = indexer.get(source);
        currentSink = indexer.get(sink);

        for (int i = 0; i < numNodes; i++) {
            for (Arc currentArc : nodes.get(i).outgoingArcs) {
                currentArc.flow = 0.0;
            }
        }
        maximumFlowValue = 0.0;
        for (;;) {
            breadthFirstSearch();
            if (!nodes.get(currentSink).visited) {
                maximumFlow = new HashMap<Arista, Double>();
                for (int i = 0; i < numNodes; i++) {
                    for (Arc currentArc : nodes.get(i).outgoingArcs) {
                        if (currentArc.prototype != null) {
                            maximumFlow.put(
                                currentArc.prototype,
                                currentArc.flow);
                        }
                    }
                }
                return;
            }
            recorrido.add(printResult());
        }
    }

    private void breadthFirstSearch()
    {
        for (int i = 0; i < numNodes; i++) {
            nodes.get(i).visited = false;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(currentSource);
        nodes.get(currentSource).visited = true;
        nodes.get(currentSource).flowAmount = Double.POSITIVE_INFINITY;
        while (queue.size() != 0) {
            int currentNode = queue.poll();
            for (Arc currentArc : nodes.get(currentNode).outgoingArcs) {
                if ((currentArc.flow + epsilon) < currentArc.capacity) {
                    if (!nodes.get(currentArc.head).visited) {
                        nodes.get(currentArc.head).visited = true;
                        nodes.get(currentArc.head).flowAmount =
                            Math.min(
                                nodes.get(currentNode).flowAmount,
                                currentArc.capacity - currentArc.flow);
                        nodes.get(currentArc.head).lastArc = currentArc;
                        queue.add(currentArc.head);
                    }
                }
            }
        }
    }

    private List<Arista> augmentFlow(StringBuilder sB)
    {
    	List<Arista> aristas = new ArrayList<Arista>(); 
        double deltaFlow = nodes.get(currentSink).flowAmount;
        maximumFlowValue += deltaFlow;
        int currentNode = currentSink;
        Stack<String> queue = new Stack<String>();
        while (currentNode != currentSource) {
            nodes.get(currentNode).lastArc.flow += deltaFlow;
            nodes.get(currentNode).lastArc.reversed.flow -= deltaFlow;
            queue.add(nodes.get(currentNode).prototype.toString());
            aristas.add(nodes.get(currentNode).lastArc.prototype);
            currentNode = nodes.get(currentNode).lastArc.tail;
        }
        sB.append(nodes.get(currentNode).prototype.toString()).append("-");
        while(!queue.empty()){
        	sB.append(queue.pop()).append("-");
        }
        sB.append("\n");
        sB.append("Flujo parcial: ").append(deltaFlow).append("\n");
        return aristas;
    }
	
	
	//Inner classes
	class Node
    {
        Vertice prototype; // corresponding node in the original network
        List<Arc> outgoingArcs = new ArrayList<Arc>(); // list of outgoing arcs
                                                       // in the residual
                                                       // network
        boolean visited; // this mark is used during BFS to mark visited nodes
        Arc lastArc; // last arc in the shortest path
        double flowAmount; // amount of flow, we are able to push here

        Node(
            Vertice prototype)
        {
            this.prototype = prototype;
        }
    }

    // class used for internal representation of network
    class Arc
    {
        int tail; // "from"
        int head; // "to"
        double capacity; // capacity (can be zero)
        double flow; // current flow (can be negative)
        Arc reversed; // for each arc in the original network we are to create
                      // reversed arc
        Arista prototype; // corresponding edge in the original network, can be null,
                     // if it is reversed arc

        Arc(
            int tail,
            int head,
            double capacity,
            Arista prototype)
        {
            this.tail = tail;
            this.head = head;
            this.capacity = capacity;
            this.prototype = prototype;
        }
    }
    
    
    private Pair printResult(){
    	StringBuilder sB = new StringBuilder();
    	sB.append("\n").append("Camino del flujo parcial: ");
    	List<Arista> aristas =  augmentFlow(sB);
    	sB.append("Flujo Total: ").append(maximumFlowValue);
    	return new Pair(sB.toString(),aristas);
    }
    
    class Pair{
    	String result;
    	List<Arista> aristas;
    	
    	Pair(String result, List<Arista> aristas){
    		this.result=result;
    		this.aristas=aristas;
    	}
    	
    	
    	
    }


	public boolean needMatrix() {
		return true;
	}

	public TableModel getMatrixData() {
		return model;
	}

	public Object[] getMatrixColumns() {
		// TODO Auto-generated method stub
		return null;
	}
    
	private void fillColsAndRows(String[] cols2, String[][] data2) {
		data = new String[1][2];
		cols = new String[2];
		cols[0] = "Flujo Parcial";
		cols[1] = "Flujo Total";
		data[0][1] = String.valueOf(0);
		data[0][0] = String.valueOf(0);
	}
	
	private void deseleccionarTodo() {
		Set<Arista> vS = network.edgeSet();
		Iterator<Arista> it = vS.iterator();
		while(it.hasNext()){
			Arista v = it.next();
			v.select(false);
		}			
		
	}
	
	
}
