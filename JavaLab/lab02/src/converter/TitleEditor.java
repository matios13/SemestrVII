package converter;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TitleEditor extends PropertyEditorSupport {
	
	
	@Override
	public Component getCustomEditor(){
		JPanel editor = new JPanel();
		JLabel shapeEditor = new JLabel("Title 2.0");
		JTextField textField = new JTextField();
		JButton button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setValue(textField.getText());
				
			}
		});
		textField.setPreferredSize(new Dimension(100,50));
		editor.add(shapeEditor);
		editor.add(textField);
		editor.add(button);
		return editor;
		
	}
	@Override
	public boolean supportsCustomEditor(){
		return true;
		}
	
	public String getAsText() {
		String value = (String) getValue();
		return value;
	}

	public void setAsText(String s) {
		setValue(s);
	}
	
}
