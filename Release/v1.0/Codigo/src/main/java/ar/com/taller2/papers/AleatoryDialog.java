package ar.com.taller2.papers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AleatoryDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8486833305404664057L;

	private String cantVertices="0";
	private String cantAristas="0";
	private JLabel msj = new JLabel("Cantidad de Vertices");
	private JLabel msj2 = new JLabel("Cantidad de Aristas");
	private JTextField vertices = new JTextField("0");
	private JTextField aristas= new JTextField("0");;
	
	public AleatoryDialog(Frame padre) {
		super(padre, "Graferator", true);
    	this.setAlwaysOnTop(true);
    	this.setLocation(padre.getSize().width/2 - 100, padre.getSize().height/2 - 75);
    	this.setSize(350, 250);
    	

    	JButton aceptar = new JButton("Aceptar");
    	aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				cantVertices = vertices.getText();
				cantAristas = aristas.getText();
				dispose();
			}
        });
    	vertices.setPreferredSize(new Dimension(50,20));
    	aristas.setPreferredSize(new Dimension(50,20));
    	JPanel top = new JPanel(new FlowLayout());
    	JPanel center = new JPanel(new FlowLayout());
    	JPanel bot = new JPanel(new FlowLayout());
    	top.add(msj);
    	top.add(vertices);
    	center.add(msj2);
    	center.add(aristas);
    	bot.add(aceptar);
    	
    	this.add(top, BorderLayout.NORTH); 
    	this.add(center, BorderLayout.CENTER);
    	this.add(bot, BorderLayout.SOUTH);
    	this.pack();
    	this.setVisible(true);
	}

	public String getCantVertices() {
		return cantVertices;
	}

	public String getCantAristas() {
		return cantAristas;
	}
	
}
