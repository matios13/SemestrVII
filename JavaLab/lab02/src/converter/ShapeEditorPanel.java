package converter;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;

import converter.Converter.Shape;

public class ShapeEditorPanel extends JPanel {
	
	public ShapeEditorPanel(){
		
		JLabel shapeEditor = new JLabel("Shape Editor");
		add(shapeEditor);
		JComboBox<Shape> jComboBox = new JComboBox<Converter.Shape>();
		Arrays.stream(Shape.values()).forEach(shape -> jComboBox.addItem(shape));
		add(jComboBox);
		
	}
	
	public ShapeEditorPanel(TitleEditor unitsEditor){
		
	}
	
	

}
