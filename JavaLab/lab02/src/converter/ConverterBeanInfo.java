package converter;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class ConverterBeanInfo extends SimpleBeanInfo {
	@Override
	public BeanDescriptor getBeanDescriptor() {
		return new BeanDescriptor(Converter.class, ConverterBeanCustomizer.class);
	}

	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor titleDescriptor;
		PropertyDescriptor shapeDescriptor;
		try {
			titleDescriptor = new PropertyDescriptor("title", Converter.class);
			titleDescriptor.setPropertyEditorClass(TitleEditor.class);
			shapeDescriptor = new PropertyDescriptor("shape", Converter.class);
			shapeDescriptor.setPropertyEditorClass(ShapeEditor.class);
			return new PropertyDescriptor[] {titleDescriptor};
		} catch (IntrospectionException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
