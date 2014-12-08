package ar.com.taller2.papers.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import ar.com.taller2.papers.libraries.LinePainter;
import ar.com.taller2.papers.libraries.TextLineNumber;
import ar.com.taller2.papers.model.LineCode;
import ar.com.taller2.papers.model.Selectable;

public class PanelPseudocodigo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5958492539703572937L;

    private JLabel lblTituloAlgoritmo = new JLabel("");
    private JTextPane textPaneContenidoAlgoritmo = new JTextPane();
    private JScrollPane scrollPaneAlgoritmo = new JScrollPane(textPaneContenidoAlgoritmo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private int lineaActual = 1;

	private void cambiarLineaPseudocodigo(int linea) {
		// Cambiar de linea pseudocodigo
		this.lineaActual = linea;
		this.textPaneContenidoAlgoritmo.setCaretPosition(this.textPaneContenidoAlgoritmo.getDocument().getDefaultRootElement().getElement(this.lineaActual - 1).getStartOffset());
		Logger.getLogger(this.getClass().getName()).info("Linea actual: " + this.lineaActual);
		Logger.getLogger(this.getClass().getName()).info("" + this.textPaneContenidoAlgoritmo.getCaretPosition());
	}
    
	public PanelPseudocodigo() {
		
		
		this.lblTituloAlgoritmo.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.lblTituloAlgoritmo.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.lblTituloAlgoritmo.setOpaque(true);
		this.add(lblTituloAlgoritmo);

		LinePainter painter = new LinePainter(this.textPaneContenidoAlgoritmo);
		painter.setColor(Color.yellow);
		TextLineNumber tln = new TextLineNumber(this.textPaneContenidoAlgoritmo);
		
		this.scrollPaneAlgoritmo.setRowHeaderView(tln);
		this.scrollPaneAlgoritmo.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.scrollPaneAlgoritmo.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		this.textPaneContenidoAlgoritmo.setEditable(false);
		this.textPaneContenidoAlgoritmo.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.textPaneContenidoAlgoritmo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.textPaneContenidoAlgoritmo.setAutoscrolls(true);
		
		this.add(scrollPaneAlgoritmo);
		
		// Cambiar de linea pseudocodigo
		int linenumber = 1;
		this.textPaneContenidoAlgoritmo.setCaretPosition(this.textPaneContenidoAlgoritmo.getDocument().getDefaultRootElement().getElement(linenumber - 1).getStartOffset());  
			
	}
	
	public void setTitulo(String titulo) {
		lblTituloAlgoritmo.setText(titulo);
	}
	
	public void setAlgoritmo(URL algoritmo) throws IOException {
		textPaneContenidoAlgoritmo.setPage(algoritmo);
	}

	public void setPseudocodeCurrent(Selectable linea) {
		if (linea != null && linea.getClass() == LineCode.class) {
			cambiarLineaPseudocodigo(((LineCode) linea).getId());			
		}
	}
}
