package converter;

import java.beans.*;

public class TitleEditor extends PropertyEditorSupport {
	
	
	public String getAsText() {
		String value = (String) getValue();
		return value;
	}

	public void setAsText(String s) {
		setValue(s);
	}
	
}
