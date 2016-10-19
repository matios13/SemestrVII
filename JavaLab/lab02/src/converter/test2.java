package converter;

import java.io.Serializable;

import javax.swing.JPanel;
import converter.Converter.Shape;

public class test2 extends JPanel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6419304326000566130L;

	/**
	 * Create the panel.
	 */
	public test2() {
		
		Converter converter = new Converter();
		converter.setShape(Shape.szescian);
		add(converter);

	}

}
