package ar.com.taller2.papers;

import javax.swing.JApplet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import ar.com.taller2.papers.adapters.JGraphXAdapter;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;

/**
 * @author Pablo Rodriguez Manzi
 *
 */
public class AprendiendoGrafos extends JApplet {
	private static final long serialVersionUID = 5320477892293342036L;
	private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(  ) {
    	 ListenableGraph<String, DefaultEdge> g = new ListenableDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

         // add some sample data (graph manipulated via JGraphT)
         g.addVertex( "v1" );
         g.addVertex( "v2" );
         g.addVertex( "v3" );
         g.addVertex( "v4" );

         g.addEdge( "v1", "v2" );
         g.addEdge( "v2", "v3" );
         g.addEdge( "v3", "v1" );
         g.addEdge( "v4", "v3" );

         JGraphXAdapter<String, DefaultEdge> graph = new JGraphXAdapter<String, DefaultEdge>(g);
         graph.setDisconnectOnMove(false);
         graph.setAllowDanglingEdges(false);
         mxGraphComponent graphComponent = new mxGraphComponent(graph);
         this.getContentPane().add(graphComponent);
         this.setSize(400, 320);
         this.setVisible(true);

         g.addVertex( "v5" );
         g.addVertex( "v6" );
         g.addVertex( "v7" );
         g.addVertex( "v8" );
         try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         graph.getModel().beginUpdate();
         double x = 20, y = 20;
         for (mxCell cell : graph.getVertexToCellMap().values()) {
             graph.getModel().setGeometry(cell, new mxGeometry(x, y, 20, 20));
             x += 40;
             if (x > 200) {
                 x = 20;
                 y += 40;
             }
         }
         graph.getModel().endUpdate();

        // that's all there is to it!...
    }

/*
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
    }*/
}
