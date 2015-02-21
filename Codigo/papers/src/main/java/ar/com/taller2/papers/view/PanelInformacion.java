package ar.com.taller2.papers.view;

import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class PanelInformacion extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3273426058472538502L;
	private JLabel lblTituloInformacion = new JLabel("");
    JTextPane textPaneContenidoInformacion = new JTextPane();
    JScrollPane scrollPaneInformacion = new JScrollPane(textPaneContenidoInformacion, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
	public PanelInformacion() {
		
		lblTituloInformacion.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblTituloInformacion.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.add(lblTituloInformacion);
			
		scrollPaneInformacion.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		textPaneContenidoInformacion.setContentType("text/html");
		textPaneContenidoInformacion.setAlignmentX(Component.LEFT_ALIGNMENT);
		textPaneContenidoInformacion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textPaneContenidoInformacion.setEditable(false);
		textPaneContenidoInformacion.setBorder(new EmptyBorder(0, 0, 0, 0));
//		textPaneContenidoInformacion.setText("<html><body><img src=" + this.getClass().getResource("/images/FondoAzul.png") + " alt='Smiley face'></body></html>");
//		textPaneContenidoInformacion.setText("<html><body background=" + this.getClass().getResource("/images/FondoAzul.png") + " alt='Smiley face'></body></html>");
		textPaneContenidoInformacion.setText("<html><body bgcolor=#5b5b5b style='margin: 0 auto; text-align: center; width: 100%; height:100%;'><img style='vertical-align: middle' src=" + this.getClass().getResource("/images/info-512.png") + " alt='Info'></body></html>");
		textPaneContenidoInformacion.setAutoscrolls(true);

		this.add(scrollPaneInformacion);
	}
	
	public void setTitulo(String titulo) {
		lblTituloInformacion.setText(titulo);
	}
	
	public void setInformacion(URL informacion) throws IOException {
		textPaneContenidoInformacion.setPage(informacion);
	}
}