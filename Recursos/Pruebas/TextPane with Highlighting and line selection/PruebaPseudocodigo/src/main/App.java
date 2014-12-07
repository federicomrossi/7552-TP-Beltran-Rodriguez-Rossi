package main;

import javax.swing.JApplet;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

public class App extends JApplet {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the applet.
	 */
	public App() {
			
		JTextPane textPane = new JTextPane();
		
		try {
			textPane.setPage(this.getClass().getResource("dijkstra-pseudocode.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LinePainter painter = new LinePainter(textPane);
		painter.setColor(Color.yellow);
		JScrollPane scrollPane = new JScrollPane(textPane);
		TextLineNumber tln = new TextLineNumber(textPane);
		scrollPane.setRowHeaderView(tln);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textPane.setEditable(false);
		int linenumber = 5;
		textPane.setCaretPosition(textPane.getDocument().getDefaultRootElement().getElement(linenumber - 1).getStartOffset());  
		
	}
}
