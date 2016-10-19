package converter;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;

	public Window() {
		
		Converter converter_1 = new Converter();
		converter_1.setPreferredSize(new Dimension(300, 300));
		
		converter_1.setSize(new Dimension(300, 300));
		converter_1.setBounds(new Rectangle(0, 0, 300, 300));
		converter_1.setBgcolor(Color.GREEN);
		getContentPane().add(converter_1, BorderLayout.CENTER);
		
		Converter converter = new Converter();
	}

}
