package ar.com.taller2.papers.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ar.com.taller2.papers.adapters.JGraphXAdapter;
import ar.com.taller2.papers.controller.jgraphx.MoveCellListener;
import ar.com.taller2.papers.model.Arista;
import ar.com.taller2.papers.model.Vertice;

import com.mxgraph.layout.mxEdgeLabelLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxStylesheet;

public class GraphView extends mxGraphComponent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7060444737587754276L;
	private JGraphXAdapter<Vertice, Arista> graph ;
	
	private boolean dirigido;
	
	public GraphView(JGraphXAdapter<Vertice, Arista> graph, boolean dirigido ) {
		super(graph);
		this.graph = graph;
		this.dirigido=dirigido;
		// Propiedades
		this.graph.setDisconnectOnMove(false);
        this.graph.setAllowDanglingEdges(false);
        this.graph.setCellsSelectable(true);
        this.graph.setAutoOrigin(true);
        this.graph.setCellsBendable(true);
        this.graph.setEdgeLabelsMovable(false);
		this.graph.setVertexLabelsMovable(false);
        
        //new mxHierarchicalLayout(graph).execute(graph.getDefaultParent());
        // Temp?
        this.ordernarVertices();
        this.setModoEdicion(false);
        
	}
	
	public void setDirigido(boolean dir){
		 dirigido =dir;
	}
	
	public void ordernarVertices() {

        this.graph.getModel().beginUpdate();
        double x = 50, y = 50;
        for (mxICell cell : this.graph.getVertexToCellMap().values()) {
        	this.graph.getModel().setGeometry(cell, new mxGeometry(x, y, 50, 50));
//        	this.graph.getModel().setStyle(cell, "shape=ellipse;fillColor=#3cdbfe");
            x += 150;
            if (x > 500) {
                x = 50;
                y += 150;
            }
        }
        //https://www.youtube.com/watch?v=CNMCNvoEyRI
		Map<String, Object> edge = new HashMap<String, Object>();
	    edge.put(mxConstants.STYLE_ROUNDED, true);
//	    edge.put(mxConstants.STYLE_ORTHOGONAL, false);
//	    edge.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ELBOW);
	    //edge.put(mxConstants.STYLE_EDGE, mxConstants.EDGESTYLE_ENTITY_RELATION);
	    edge.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CURVE);
	    if(dirigido){
	    	edge.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_CLASSIC);
	    }else{
	    	edge.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
	    }
	    //
	    
//	    edge.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
//	    edge.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_RIGHT);
	    edge.put(mxConstants.STYLE_STROKECOLOR, "#000000"); // default is #6482B9
	    edge.put(mxConstants.STYLE_FONTCOLOR, "#446299");
	    edge.put(mxConstants.STYLE_DELETABLE, true);

		Map<String, Object> vertex = new HashMap<String, Object>();
		vertex.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		vertex.put(mxConstants.STYLE_FILLCOLOR, mxUtils.parseColor("#3CDBFE"));
//		vertex.put(mxConstants.STYLE_GLASS, 1);
		vertex.put(mxConstants.STYLE_GRADIENTCOLOR, mxUtils.parseColor("#FFFFFF"));
		vertex.put(mxConstants.STYLE_GRADIENT_DIRECTION, mxConstants.DIRECTION_SOUTH);
		vertex.put(mxConstants.STYLE_PERIMETER, mxConstants.PERIMETER_ELLIPSE);
		
	    mxStylesheet edgeStyle = new mxStylesheet();
	    edgeStyle.setDefaultEdgeStyle(edge);
	    edgeStyle.setDefaultVertexStyle(vertex);
	    graph.setStylesheet(edgeStyle);
	    
        this.graph.getModel().endUpdate();
       // mxFastOrganicLayout first = new mxFastOrganicLayout(graph);
      
        mxParallelEdgeLayout second = new mxParallelEdgeLayout(graph);
       // first.execute(graph.getDefaultParent());
        second.execute(graph.getDefaultParent());
	}
	
	public void actualizar(){
		this.graph.getModel().beginUpdate();
        for (Entry<Vertice,mxICell> cell : this.graph.getVertexToCellMap().entrySet()) {
        	this.graph.getModel().setStyle(cell.getValue(),cell.getKey().isSelected() ? "shape=ellipse;fillColor=#FF8000;gradientDirection=south;gradientColor=#B25900;glass=true;fontBold=true": "shape=ellipse;fillColor=#3cdbfe;gradientDirection=south;gradientColor=#2ca0ba;glass=true;fontBold=true" );
        }
        StringBuilder sb = new StringBuilder();
        sb.append(mxConstants.STYLE_ROUNDED).append("=").append(true).append(";");
//        sb.append(mxConstants.STYLE_EDGE).append("=").append(mxConstants.EDGESTYLE_ELBOW).append(";");
        sb.append(mxConstants.STYLE_SHAPE).append("=").append(mxConstants.SHAPE_CURVE).append(";");
        if(dirigido){
        	sb.append(mxConstants.STYLE_ENDARROW).append("=").append(mxConstants.ARROW_BLOCK).append(";");
        }else{
        	 sb.append(mxConstants.STYLE_ENDARROW).append("=").append(mxConstants.NONE).append(";");
        }
        //
       
        sb.append(mxConstants.STYLE_FONTCOLOR).append("=").append("#446299").append(";");
        sb.append(mxConstants.STYLE_DELETABLE).append("=").append(true).append(";");
        
        for (Entry<Arista,mxICell> cell : this.graph.getEdgeToCellMap().entrySet()) {
        	StringBuilder sb2 = new StringBuilder(sb);
        	sb.append(mxConstants.STYLE_STROKECOLOR).append("=").append(cell.getKey().isSelected() ? "#FF00FF": "#000000").append(";");
        	this.graph.getModel().setStyle(cell.getValue(),sb.toString());
        }
        this.graph.getModel().endUpdate();
       // mxParallelEdgeLayout second = new mxParallelEdgeLayout(graph);
       // second.execute(graph.getDefaultParent());
        this.graph.refresh();
	}

	public JGraphXAdapter<Vertice, Arista> getGraphAdapter() {
		return graph;
	}
	
	public void addNewEdgeListener(mxIEventListener a){
		this.getConnectionHandler().addListener(mxEvent.CONNECT, a);
	}
	
	public void addChangeWeightListener(mxIEventListener a){
		this.addListener(mxEvent.LABEL_CHANGED, a);
	}
	
	public void addMoveCellListener(mxIEventListener a){
		this.graph.addListener(mxEvent.CELLS_MOVED, a);
	}
	
	public void setModoEdicion(boolean edicion){
		this.graph.setCellsDeletable(edicion);
		this.graph.setCellsEditable(edicion);
		this.graph.setCellsMovable(edicion);
		this.graph.setCellsResizable(edicion);
		this.setConnectable(edicion);
	}


	public void ordernarVertices(Vertice v, int x, int y) {
		this.graph.getModel().beginUpdate();
		this.graph.getModel().setGeometry(this.graph.getVertexToCellMap().get(v), new mxGeometry(x-25, y-25, 50, 50));
		this.graph.getModel().endUpdate();
	}
	
	public void ordernarVertices(Vertice v) {
		this.graph.getModel().beginUpdate();
		double y = getMaxY();
		double x = getMaxX(y);
		
		x += 150;
        if (x > 500) {
            x = 50;
            y += 150;
        }
		
		this.graph.getModel().setGeometry(this.graph.getVertexToCellMap().get(v), new mxGeometry(x, y, 50, 50));
		this.graph.getModel().endUpdate();
	}
	
	private double getMaxX(double y){
		double max = 0;
		for (Entry<Vertice,mxICell> cell : this.graph.getVertexToCellMap().entrySet()) {
			//graph.getView().getState(cell).getX()
			if(cell.getValue().getGeometry() != null && cell.getValue().getGeometry().getY() == y){
				if(cell.getValue().getGeometry() != null && cell.getValue().getGeometry().getX()> max){
					max = cell.getValue().getGeometry().getX();
				}
        	}
        }
		return max;
	}
	
	private double getMaxY(){
		double max = 0;
		for (Entry<Vertice,mxICell> cell : this.graph.getVertexToCellMap().entrySet()) {
        	if(cell.getValue().getGeometry() != null && cell.getValue().getGeometry().getY()> max){
        		max = cell.getValue().getGeometry().getY();
        	}
        }
		return max;
	}

	
}
