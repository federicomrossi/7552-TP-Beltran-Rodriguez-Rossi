package ar.com.taller2.papers;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AristaDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8486833305404664057L;

	private String origen="";
	private String destino="";
	private Double peso=1d;
	private JLabel msj = new JLabel("Vértice Origen");
	private JLabel msj2 = new JLabel("Vertice Destino");
	private JLabel msj3 = new JLabel("Peso");
	private JTextField vertices = new JTextField("");
	private JTextField aristas= new JTextField("");
	private JTextField pesoField= new JTextField("1");
	
	public AristaDialog(Frame padre) {
		super(padre, "Graferator", true);
    	this.setAlwaysOnTop(true);
    	this.setLocation(padre.getSize().width/2 - 100, padre.getSize().height/2 - 75);
    	this.setSize(350, 250);
    	

    	JButton aceptar = new JButton("Aceptar");
    	aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				origen = vertices.getText();
				destino = aristas.getText();
				peso = Double.parseDouble(pesoField.getText());
				dispose();
			}
        });
    	vertices.setPreferredSize(new Dimension(50,20));
    	aristas.setPreferredSize(new Dimension(50,20));
    	pesoField.setPreferredSize(new Dimension(50,20));
    	JPanel top = new JPanel(new FlowLayout());
    	JPanel center = new JPanel(new FlowLayout());
    	JPanel pesos =  new JPanel(new FlowLayout());
    	JPanel bot = new JPanel(new FlowLayout());
    	top.add(msj);
    	top.add(vertices);
    	center.add(msj2);
    	center.add(aristas);
    	pesos.add(msj3);
    	pesos.add(pesoField);
    	bot.add(aceptar);
    	JPanel panel = new JPanel();
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	
    	panel.add(top); 
    	panel.add(center);
    	panel.add(pesos);
    	panel.add(bot);
    	this.add(panel);
    	this.pack();
    	this.setVisible(true);
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}
	
	public Double getPeso(){
		return peso;
	}
	
}
