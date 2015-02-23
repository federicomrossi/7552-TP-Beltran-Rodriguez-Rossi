package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.cycle.JohnsonSimpleCycles;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Selectable;
import ar.com.taller2.papers.model.Vertice;

public class CerraduraTransitiva extends GraphAlgorithm {

	private ListenableDirectedWeightedGraph<Vertice, Arista> graph = new ListenableDirectedWeightedGraph<Vertice, Arista>(Arista.class);
	private Vertice inicio;
	private int indiceSiguientePaso;
	private List<ListenableDirectedWeightedGraph<Vertice, Arista>> recorrido = new ArrayList<ListenableDirectedWeightedGraph<Vertice, Arista>>();
	
	Set<Vertice> vertexSet;
    Set<Vertice> newEdgeTargets = new HashSet<Vertice>();
    int bound;
    boolean done = false;
	
 // Data para modo evaluacion	
 	String[][] data;
 	String[] cols;
 	TableModel model;
    
    
	public CerraduraTransitiva(ListenableDirectedWeightedGraph<Vertice, Arista> graph){
		Graphs.addGraph(this.graph, graph);
		Logger.getLogger("MATRIX").info(getAdjacencyMatrix(graph));
	}
	
	public void iniciar() {
		this.indiceSiguientePaso = 0;
		Logger.getLogger(this.getClass().getName()).info("Inicie el algoritmo");
		JohnsonSimpleCycles<Vertice,Arista> detector = new JohnsonSimpleCycles<Vertice, Arista>((DirectedGraph<Vertice, Arista>) this.graph);
		List<List<Vertice>> ciclos = detector.findSimpleCycles();
		if(ciclos != null && ciclos.size() > 0){
			Logger.getLogger(this.getClass().getName()).warning("TIENE CICLOS");
		}
		closeSimpleDirectedGraph();
		fillColsAndRows(cols,data);
		
		model = new DefaultTableModel(data,cols);
	}
	
	
	public String siguiente() throws NextStepNotExistsException {
		Logger.getLogger(this.getClass().getName()).info("Siguiente");

		if(this.indiceSiguientePaso < recorrido.size()) {
            
            Logger.getLogger("MATRIX").info(getAdjacencyMatrix(recorrido.get(indiceSiguientePaso)));
            return getAdjacencyMatrix(recorrido.get(indiceSiguientePaso++));
		}
		else {
			throw new NextStepNotExistsException("No hay paso siguiente");
		}
	}

	public String anterior() {
		Logger.getLogger("RecorridoAnchura").info("Anterior");

		if(this.indiceSiguientePaso - 1 >= 0) {
			 Logger.getLogger("MATRIX").info(getAdjacencyMatrix(recorrido.get(--indiceSiguientePaso)));
	         return getAdjacencyMatrix(recorrido.get(indiceSiguientePaso));
		}
		return "";
		
	}

	

	public void terminar() {
        this.graph = new ListenableDirectedWeightedGraph<Vertice, Arista>(Arista.class);
        Graphs.addGraph(this.graph, recorrido.get(0));
        recorrido = new ArrayList<ListenableDirectedWeightedGraph<Vertice, Arista>>();
	}

	public String principio() {
		Logger.getLogger("RecorridoAnchura").info("Principio");
		
		while(--this.indiceSiguientePaso >= 0) {
			 Logger.getLogger("MATRIX").info(getAdjacencyMatrix(recorrido.get(indiceSiguientePaso)));
	         
		}
		this.indiceSiguientePaso = 0;
		return getAdjacencyMatrix(recorrido.get(indiceSiguientePaso));
	}

	public String fin() {
		Logger.getLogger("RecorridoAnchura").info("Fin");
		
		while(this.indiceSiguientePaso < this.recorrido.size()) {
			Logger.getLogger("MATRIX").info(getAdjacencyMatrix(recorrido.get(indiceSiguientePaso++)));
		}
		return getAdjacencyMatrix(recorrido.get(indiceSiguientePaso-1));
	}

	public boolean cumpleCondicionesIniciales() {
		return true;
	}

	public String getCondicionesIniciales() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.recorrido.size());
	}

	public URL getAlgoritmo() {
		return this.getClass().getResource("/algorithms/cerradura-transitiva-pseudocode.txt");
	}

	public String getTitulo() {
		return "Cerradura Transitiva";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/cerradura-transitiva-info.html");
	}

	public Boolean isSourceDest() {
		return Boolean.FALSE;
	}

	public void setSource(Vertice v) {
		// TODO Auto-generated method stub
		
	}

	public void setDest(Vertice v) {
		// TODO Auto-generated method stub
		
	}

	public Boolean isCorrect(Resultado r) throws NextStepNotExistsException{
		Logger.getLogger(this.getClass().getName()).info("Siguiente Evaluación");
		if(this.indiceSiguientePaso < this.recorrido.size()) {
			String v = getAdjacencyMatrix(this.recorrido.get(this.indiceSiguientePaso++));
			int cant =  graph.vertexSet().size();
			StringBuilder sB = new StringBuilder();
			sB.append("\t");
	    	for(int i=0;i<cant;i++){
	    		sB.append("v"+(i+1)).append("\t");
	    	}
	    	sB.append("\n");
	    	for(int i=0; i<cant;i++){
	    		sB.append("v"+(i+1)).append("\t");
	    		for(int j=0; j<cant;j++){
	    			sB.append(Integer.valueOf((String)model.getValueAt(i,j+1))).append("\t");
	    		}
	    		sB.append("\n");
	    	}
	    	Logger.getLogger(this.getClass().getName()).info("Input: "+sB.toString());
	    	Logger.getLogger(this.getClass().getName()).info("Result: "+v);
	    	if(v.equals(sB.toString())){
	    		return true;
	    	}
	    	this.indiceSiguientePaso--;
		}else{
			throw new NextStepNotExistsException("No hay más pasos");
		}
		return Boolean.FALSE;
	}

	public Selectable getCurrentItem() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void closeSimpleDirectedGraph()
    {
		ListenableDirectedWeightedGraph<Vertice,Arista> nuevo = new ListenableDirectedWeightedGraph<Vertice, Arista>(Arista.class);
		Graphs.addGraph(nuevo, graph);
		recorrido.add(nuevo);
        Set<Vertice> vertexSet = graph.vertexSet();

        Set<Vertice> newEdgeTargets = new HashSet<Vertice>();

        // At every iteration of the outer loop, we add a path of length 1
        // between nodes that originally had a path of length 2. In the worst
        // case, we need to make floor(log |V|) + 1 iterations. We stop earlier
        // if there is no change to the output graph.

        int bound = computeBinaryLog(vertexSet.size());
        boolean done = false;
        for (int i = 0; !done && (i < bound); ++i) {
            done = true;
            for (Vertice v1 : vertexSet) {
                newEdgeTargets.clear();

                for (Arista v1OutEdge : graph.outgoingEdgesOf(v1)) {
                    Vertice v2 = graph.getEdgeTarget(v1OutEdge);
                    for (Arista v2OutEdge : graph.outgoingEdgesOf(v2)) {
                        Vertice v3 = graph.getEdgeTarget(v2OutEdge);

                        if (v1.equals(v3)) {
                            // Its a simple graph, so no self loops.
                            continue;
                        }

                        if (graph.getEdge(v1, v3) != null) {
                            // There is already an edge from v1 ---> v3, skip;
                            continue;
                        }

                        newEdgeTargets.add(v3);
                        done = false;
                    }
                }

                for (Vertice v3 : newEdgeTargets) {
                    graph.addEdge(v1, v3);
                }
                nuevo = new ListenableDirectedWeightedGraph<Vertice, Arista>(Arista.class);
                Graphs.addGraph(nuevo, graph);
                recorrido.add(nuevo);
            }
        }
    }

    /**
     * Computes floor(log_2(n)) + 1
     */
    private int computeBinaryLog(int n)
    {
        assert n >= 0;

        int result = 0;
        while (n > 0) {
            n >>= 1;
            ++result;
        }

        return result;
    }
    
    private String getAdjacencyMatrix(ListenableDirectedWeightedGraph<Vertice, Arista> graph){
    	Integer cant = graph.vertexSet().size();
    	Integer[][] matrix = new Integer[cant][cant];
    	StringBuilder sB =  new StringBuilder();
    	sB.append("\t");
    	for(int i=0;i<cant;i++){
    		sB.append("v"+(i+1)).append("\t");
    	}
    	sB.append("\n");
    	for(int i=0; i<cant;i++){
    		sB.append("v"+(i+1)).append("\t");
    		for(int j=0; j<cant;j++){
    			matrix[i][j] = (graph.getEdge(new Vertice("v"+(i+1),false), new Vertice("v"+(j+1),false)) != null ? 1 : 0);
    			sB.append(matrix[i][j]).append("\t");
    		}
    		sB.append("\n");
    	}
    	return sB.toString();
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
		int cant = graph.vertexSet().size();
		data = new String[cant][cant+1];
		cols = new String[cant+1];
		cols[0] = "";
		data[0][0]="";
		for(int i=0;i<cant;i++){
			for(int j=1;j<cant+1;j++){
				data[i][j]=String.valueOf(0);
			}
		}
		for(Integer i=1;i<cant+1;i++){
    		cols[i] = "v"+i;
    		data[i-1][0] = "v"+i;
    	}
		
		
	}
	
}
