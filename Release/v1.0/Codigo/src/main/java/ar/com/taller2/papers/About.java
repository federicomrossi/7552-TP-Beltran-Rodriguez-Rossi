package ar.com.taller2.papers;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel logoPanel = new JPanel();


	/**
	 * Create the dialog.
	 */
	public About() {
		
		this.setModal(true);
		
		setBounds(100, 100, 484, 342);
		getContentPane().setLayout(new BorderLayout());
		logoPanel.setBorder(new EmptyBorder(10, 15, 5, 15));
		getContentPane().add(logoPanel, BorderLayout.NORTH);
		logoPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JLabel labelLogo = new JLabel(new ImageIcon(Main.class.getResource("/images/graferator-logo.png")));
			labelLogo.setHorizontalAlignment(SwingConstants.LEFT);
			labelLogo.setSize(272, 78);
			logoPanel.add(labelLogo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Cerrar");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JPanel panelInfoAbout = new JPanel();
			getContentPane().add(panelInfoAbout, BorderLayout.CENTER);
			panelInfoAbout.setBorder(new EmptyBorder(10, 20, 10, 20));
			panelInfoAbout.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JLabel label = new JLabel("");
				panelInfoAbout.add(label);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Graferator. Simulador de grafos.");
				panelInfoAbout.add(lblNewLabel_4);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Version: 1.0.0");
				panelInfoAbout.add(lblNewLabel_5);
			}
			{
				JLabel label = new JLabel("");
				panelInfoAbout.add(label);
			}
			{
				JLabel lblNewLabel = new JLabel("Autores:");
				panelInfoAbout.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Belén Beltran (belubeltran@gmail.com)");
				panelInfoAbout.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Pablo Ariel Rodriguez (prodriguez@fi.uba.ar)");
				panelInfoAbout.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Federico Martín Rossi (federicomrossi@gmail.com)");
				panelInfoAbout.add(lblNewLabel_3);
			}
			{
				JLabel label = new JLabel("");
				panelInfoAbout.add(label);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("(c) Copyright Graferator contribuidores y otros 2014.  Todos los derechos reservados.");
				panelInfoAbout.add(lblNewLabel_6);
			}
			{
				JLabel label = new JLabel("");
				panelInfoAbout.add(label);
			}
		}
		

	}


	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}

}
