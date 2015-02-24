package ar.com.taller2.papers.model.graphs;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.GraphAlgorithm;
import ar.com.taller2.papers.model.LineCode;
import ar.com.taller2.papers.model.Resultado;
import ar.com.taller2.papers.model.Selectable;
import ar.com.taller2.papers.model.Vertice;

public class FloydWarshall extends GraphAlgorithm {

	private ListenableGraph<Vertice, Arista> graph;
	private Vertice inicio;
	private Vertice fin;
	private int indiceSiguientePaso;
	private List<Selectable> items = new ArrayList<Selectable>();
	private Vector<Vertice> camino = new Vector<Vertice>();
	
	private List<String> recorrido;
	private List<Vertice> vertices;
	private double [][] d;

	// Data para modo evaluacion	
	String[][] data;
	String[] cols;
	
	TableModel model;
	
	private void createItemList() { }
	
	public FloydWarshall(ListenableGraph<Vertice, Arista> graph){
		this.graph=graph;
	}
	
	
	public void iniciar() {
		this.indiceSiguientePaso = 0;
		recorrido = new ArrayList<String>();
		Logger.getLogger(getClass().getSimpleName()).info("Inicie el algoritmo");
		createItemList();
		floydWarshall();
		fillColsAndRows(cols,data);
		
		model = new DefaultTableModel(data,cols);
		
	}
	
	private void fillColsAndRows(String[] cols2, String[][] data2) {
		int cant = vertices.size()+1;
		data = new String[vertices.size()][vertices.size()+1];
		cols = new String[vertices.size()+1];
		cols[0] = "";
		data[0][0]="";
		for(int i=0;i<cant-1;i++){
			for(int j=1;j<cant;j++){
				data[i][j]=String.valueOf(0);
			}
		}
		for(Integer i=1;i<cant;i++){
    		cols[i] = "v"+i;
    		data[i-1][0] = "v"+i;
    	}
		
		
	}

	public String siguiente() throws NextStepNotExistsException {
		Logger.getLogger(getClass().getSimpleName()).info("Siguiente");
		if(this.indiceSiguientePaso < this.recorrido.size()) {
			return recorrido.get(indiceSiguientePaso++);
		}
		else {
			throw new NextStepNotExistsException("No hay más pasos");
		}
		
//		if(this.indiceSiguientePaso < this.camino.size()) {
//			Arista v = this.camino.get(this.indiceSiguientePaso++);
//			v.select(true);
//		}
	}

	public String anterior() {
		Logger.getLogger(getClass().getSimpleName()).info("Anterior");
		if(this.indiceSiguientePaso - 1 >= 0) {
			return recorrido.get(--indiceSiguientePaso);
		}
//		if(this.indiceSiguientePaso - 1 >= 0) {
//			Arista v = this.camino.get(--this.indiceSiguientePaso);
//			v.select(false);
//			return true;
//		}
		
		return "";
	}

	

	public void terminar() {
//		while(--this.indiceSiguientePaso >= 0) {
//			Arista v = this.camino.get(this.indiceSiguientePaso);
//			v.select(false);
//		}
		this.indiceSiguientePaso = 0;
	}

	public String principio() {
		Logger.getLogger(getClass().getSimpleName()).info("Principio");
//		while(--this.indiceSiguientePaso >= 0) {
//			Arista v = this.camino.get(this.indiceSiguientePaso);
//			v.select(false);
//		}
		
		this.indiceSiguientePaso = 0;
		return recorrido.get(indiceSiguientePaso);
	}

	public String fin() {
		Logger.getLogger(getClass().getSimpleName()).info("Fin");
//		while(this.indiceSiguientePaso < this.camino.size()) {
//			Arista v = this.camino.get(this.indiceSiguientePaso++);
//			v.select(true);
//		}
		this.indiceSiguientePaso = recorrido.size()-1;
		return recorrido.get(indiceSiguientePaso);
	}

	public boolean cumpleCondicionesIniciales() {
		return true;
	}

	public String getCondicionesIniciales() {
		return null;
	}

	public boolean tieneSiguiente() {
		return (this.indiceSiguientePaso < this.recorrido.size());
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
		return this.getClass().getResource("/algorithms/floyd-pseudocode.txt");
	}

	public String getTitulo() {
		return "Algoritmo de caminos mínimos de Floyd";
	}

	public URL getDescripcion() {
		return this.getClass().getResource("/algorithms/floyd-info.html");
	}


	public Selectable getCurrentItem() {
		if (this.indiceSiguientePaso - 1 >= 0) 
			return this.items.get(this.indiceSiguientePaso - 1);
		return this.items.get(this.indiceSiguientePaso);
	}

	public void floydWarshall(){
		vertices = new ArrayList<Vertice>(graph.vertexSet());
		int n = vertices.size();
		d = new double[n][n];
	    for (int i = 0; i < n; i++) {
	    	Arrays.fill(d[i], Double.POSITIVE_INFINITY);
	    }
	    
	    for (int i = 0; i < n; i++) {
            d[i][i] = 0.0;
        }
	    
	    Set<Arista> edges = graph.edgeSet();
        for (Arista edge : edges) {
            Vertice v1 = graph.getEdgeSource(edge);
            Vertice v2 = graph.getEdgeTarget(edge);

            int v_1 = vertices.indexOf(v1);
            int v_2 = vertices.indexOf(v2);

            d[v_1][v_2] = graph.getEdgeWeight(edge);
            if (!(graph instanceof DirectedGraph<?, ?>)) {
                d[v_2][v_1] = graph.getEdgeWeight(edge);
            }
        }
        
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    double ik_kj = d[i][k] + d[k][j];
                    if (ik_kj < d[i][j]) {
                        d[i][j] = ik_kj;
                    }
                }
            }
            recorrido.add(getDistanceMatrix());
        }
	    
	}
	
	public String getDistanceMatrix(){
		int cant = vertices.size();
		StringBuilder sB = new StringBuilder();
		sB.append("\t");
    	for(int i=0;i<cant;i++){
    		sB.append("v"+(i+1)).append("\t");
    	}
    	sB.append("\n");
    	for(int i=0; i<cant;i++){
    		sB.append("v"+(i+1)).append("\t");
    		for(int j=0; j<cant;j++){
    			sB.append(d[i][j]).append("\t");
    		}
    		sB.append("\n");
    	}
		return sB.toString();
	}

	public Boolean isCorrect(Resultado r) throws NextStepNotExistsException {
		Logger.getLogger(this.getClass().getName()).info("Siguiente Evaluación");
		if(this.indiceSiguientePaso < this.recorrido.size()) {
			String v = this.recorrido.get(this.indiceSiguientePaso++);
			int cant = vertices.size();
			StringBuilder sB = new StringBuilder();
			sB.append("\t");
	    	for(int i=0;i<cant;i++){
	    		sB.append("v"+(i+1)).append("\t");
	    	}
	    	sB.append("\n");
	    	for(int i=0; i<cant;i++){
	    		sB.append("v"+(i+1)).append("\t");
	    		for(int j=0; j<cant;j++){
	    			sB.append(Double.valueOf((String)model.getValueAt(i,j+1))).append("\t");
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
	
	
	public boolean needMatrix() {
		return true;
	}

	public TableModel getMatrixData() {
		return model;
	}

	public Object[] getMatrixColumns() {
		// TODO Auto-generated method stub
		return cols;
	}
	
	
	
}
