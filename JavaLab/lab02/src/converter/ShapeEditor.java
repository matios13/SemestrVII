package converter;

import java.beans.PropertyEditorSupport;

import converter.Converter.Shape;

public class ShapeEditor extends PropertyEditorSupport {
	
	public String getAsText() {
		String value = ((Shape)getValue()).toString();
		
		return value;
	}

	public void setAsText(String s) {
		setValue(Shape.valueOf(s));
	}
}
