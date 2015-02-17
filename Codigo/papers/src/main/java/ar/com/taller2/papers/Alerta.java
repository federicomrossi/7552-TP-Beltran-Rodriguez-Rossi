package ar.com.taller2.papers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Alerta extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8486833305404664057L;

	public Alerta(Frame padre, String mensaje) {
		super(padre, "Graferator", true);
    	this.setAlwaysOnTop(true);
    	this.setLocation(padre.getSize().width/2 - 100, padre.getSize().height/2 - 75);
    	this.setSize(350, 250);
    	JLabel msj = new JLabel("<html><br/><p align='center'>" + mensaje + "</p><br/></html>");

    	JButton aceptar = new JButton("Aceptar");
    	aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
        });
    	
    	JPanel top = new JPanel(new FlowLayout());
    	JPanel center = new JPanel(new FlowLayout());
    	
    	top.add(msj);
    	center.add(aceptar);
    	
    	JPanel panel = new JPanel();
    	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    	panel.add(top);
    	panel.add(center);
        this.add(panel);
    	this.pack();
    	this.setVisible(true);
	}
}
