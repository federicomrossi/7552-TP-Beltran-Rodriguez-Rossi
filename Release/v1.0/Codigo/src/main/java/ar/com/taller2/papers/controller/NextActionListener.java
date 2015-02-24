package ar.com.taller2.papers.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.table.TableModel;

import ar.com.taller2.papers.exceptions.NextStepNotExistsException;

public class NextActionListener implements ActionListener {

	AprendiendoGrafos app;

	public NextActionListener(AprendiendoGrafos app) {
		this.app = app;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (app.getTutor().esModoEvaluacion()) {
				if (app.getModelo().getAlgorithm().needMatrix()) {

					TableModel data = app.getModelo().getAlgorithm().getMatrixData();
					//Object[] columns = app.getModelo().getAlgorithm().getMatrixColumns();

					app.getVista().showMatrixInput(data);

				} else {
					if (!app.getModelo().nextStepEvaluacion()) {
						// Mostrar msj de equivocacion
						Logger.getLogger(this.getClass().getName()).info(
								"Elegi otro man!");
						//app.getModelo().previousStepAlgorithm();
						app.getVista().mostrarMensajeEquivocacion(
								"Resultado incorrecto!");
					} else {
						app.getVista().mostrarMensajeEquivocacion(
								"Resultado correcto!");
					}
				}
			} else {
				String result = app.getModelo().nextStepAlgorithm();
				app.getVista().borrarSalida();
				app.getVista().agregarASalida(result);
				
				// Pseudocodigo
				try {
					app.getVista().setPseudocodeCurrent(app.getModelo().getAlgorithm().getCurrentItem());
				}
				catch (Exception e1) { }
			}
			app.getVista().actualizar();
		} catch (NextStepNotExistsException e1) {
			app.getVista().mostrarMensajeEquivocacion(e1.getMessage());
		}
	}

}
