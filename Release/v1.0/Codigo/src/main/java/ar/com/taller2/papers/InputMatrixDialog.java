package ar.com.taller2.papers;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ar.com.taller2.papers.controller.AprendiendoGrafos;
import ar.com.taller2.papers.exceptions.NextStepNotExistsException;

public class InputMatrixDialog extends JDialog {

	AprendiendoGrafos app;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8313424520100050818L;

	public InputMatrixDialog(Frame padre, TableModel model,
			String mensaje, AprendiendoGrafos app2) {
		super(padre, "Graferator", true);
		this.app=app2;
		this.setAlwaysOnTop(true);
		this.setLocation(padre.getSize().width / 2 - 100,
				padre.getSize().height / 2 - 75);
		this.setSize(350, 250);
		JLabel msj = new JLabel("<html><br/><p align='center'>" + mensaje
				+ "</p><br/></html>");

		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!app.getModelo().nextStepEvaluacion()) {
						// Mostrar msj de equivocacion
						Logger.getLogger(this.getClass().getName()).info(
								"Elegi otro man!");
						app.getModelo().previousStepAlgorithm();
						JOptionPane.showMessageDialog(InputMatrixDialog.this, "Resultado Incorrecto!");
						//app.getVista().mostrarMensajeEquivocacion(
							//	"Resultado incorrecto!");
					} else {
						JOptionPane.showMessageDialog(InputMatrixDialog.this, "Resultado Correcto!");
						//app.getVista().mostrarMensajeEquivocacion(
							//	"Resultado correcto!");
					}
				} catch (NextStepNotExistsException e1) {
					JOptionPane.showMessageDialog(InputMatrixDialog.this, e1.getMessage());
				}finally{
					dispose();
				}
			}
		});
		
		JTable table = new JTable(model);
		getContentPane().add(msj, BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
	                BorderLayout.CENTER);
	        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    getContentPane().add(aceptar, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}

}
