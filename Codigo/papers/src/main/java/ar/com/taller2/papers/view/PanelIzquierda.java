package ar.com.taller2.papers.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class PanelIzquierda extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3526541641197401747L;

	
	public PanelIzquierda() {
		this.setBackground(new Color(91, 91, 91));
	}
    
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;
	    Color color1 = getBackground();
	    Color color2 = color1.darker();
	    int w = getWidth();
	    int h = getHeight();
	    GradientPaint gp = new GradientPaint(
	        0, 0, color1, 0, h, color2);
	    g2d.setPaint(gp);
	    g2d.fillRect(0, 0, w, h);
	}
  
}
