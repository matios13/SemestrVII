package converter;

import java.awt.Component;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import converter.Converter.Shape;

public class ShapeEditor extends PropertyEditorSupport {
	@Override
	public String getAsText() {
		String value = ((Shape)getValue()).toString();
		
		return value;
	}
	@Override
	public void setAsText(String s) {
		
	}
	@Override
	public Component getCustomEditor(){
		JPanel editor = new JPanel();
		JLabel shapeEditor = new JLabel("Shape Editor 2.0");
		JComboBox<Shape> jComboBox = new JComboBox<Converter.Shape>();
		Arrays.stream(Shape.values()).forEach(shape -> jComboBox.addItem(shape));
		jComboBox.addActionListener(evt -> setValue((Shape)jComboBox.getSelectedItem()));
		jComboBox.setVisible(true);
		editor.add(shapeEditor);
		shapeEditor.add(jComboBox);	
		return jComboBox;
		
	}
	@Override
	public boolean supportsCustomEditor(){
		return true;
		}
}
