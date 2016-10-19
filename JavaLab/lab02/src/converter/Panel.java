package converter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JButton;

import converter.Converter.Shape;

import java.awt.Color;

import javax.swing.border.CompoundBorder;

import java.awt.Dimension;

public class Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	public Panel() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Converter converter = new Converter();
		converter.setShape(Shape.szescian);
		add(converter);
	}
	public static void main(String args[]){
		Panel panel = new Panel();
		JFrame jFrame = new JFrame();
		panel.setVisible(true);
		jFrame.getContentPane().add(panel);
		jFrame.setVisible(true);
	}
	

}
