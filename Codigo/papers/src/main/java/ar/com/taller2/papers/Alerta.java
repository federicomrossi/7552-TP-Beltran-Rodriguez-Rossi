package ar.com.taller2.papers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

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

    	JButton aceptar = new JButton("Intentar de nuevo");
    	aceptar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
        });

    	getContentPane().add(msj, BorderLayout.NORTH);  
        getContentPane().add(aceptar, BorderLayout.CENTER);
    	this.pack();
    	this.setVisible(true);
	}
}
