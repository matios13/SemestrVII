package converter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Customizer;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import converter.Converter.Shape;

public class ConverterBeanCustomizer extends JPanel implements Customizer {

	private Converter converter;
	private PropertyEditor colorEditor;
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	public ConverterBeanCustomizer() {
		colorEditor = PropertyEditorManager.findEditor(Color.class);
		colorEditor.addPropertyChangeListener(evt -> setBgColor((Color) colorEditor.getValue()));
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		JLabel lblBgcolor = new JLabel("bgColor");
		panel.add(lblBgcolor);
		panel.add(colorEditor.getCustomEditor());
		add(panel);
		
		JPanel panel_1 = new JPanel();
		JLabel lblDimension = new JLabel("Title");
		panel_1.add(lblDimension);
		
		add(panel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(a -> setTitle(textField.getText()));
		panel_1.add(btnOk);	
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		JLabel lblFrom = new JLabel("Shape");
		panel_3.add(lblFrom);
		
		JComboBox<Shape> jComboBox = new JComboBox<Converter.Shape>();
		Arrays.stream(Shape.values()).forEach(shape -> jComboBox.addItem(shape));
		
		jComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setShape((Shape)jComboBox.getSelectedItem());
				
			}
		});
		panel_3.add(jComboBox);
		
		

	}

	private void setShape(Shape shape) {
		if (converter == null){
			return;
		}
		Shape oldShape = converter.getShape();
		converter.setShape(shape);
		
		firePropertyChange("shape", oldShape,shape);
	}
	
	
	private void setTitle(String value) {
		if (converter == null){
			return;
		}
		String oldValue = converter.getTitle();
		converter.setTitle(value);
		firePropertyChange("title", oldValue, value);
	}

	protected void setBgColor(Color value) {
		if (converter == null){
			return;
		}
		Color oldValue = converter.getBgcolor();
		converter.setBgcolor(value);
		firePropertyChange("bgColor", oldValue, value);	
	}

	@Override
	public void setObject(Object bean) {
		 converter = (Converter) bean;
		 textField.setText(converter.getTitle());
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}
}
