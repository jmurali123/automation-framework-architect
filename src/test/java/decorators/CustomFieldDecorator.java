package decorators;

import annotations.FindByLocator;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import pages.components.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CustomFieldDecorator extends DefaultFieldDecorator {

    private static final Map<Class<?>, Class<?>> componentRegistry
            = new HashMap<>();

    static {
        componentRegistry.put(HtmlInput.class, HtmlInput.class);
        componentRegistry.put(HtmlButton.class, HtmlButton.class);
        componentRegistry.put(HtmlLabel.class, HtmlLabel.class);
        componentRegistry.put(HtmlDropdown.class, HtmlDropdown.class);
        componentRegistry.put(HtmlCheckbox.class, HtmlCheckbox.class);
        componentRegistry.put(HtmlRadioButton.class, HtmlRadioButton.class);
        componentRegistry.put(HtmlLink.class, HtmlLink.class);
        componentRegistry.put(HtmlImage.class, HtmlImage.class);
        componentRegistry.put(HtmlTextArea.class, HtmlTextArea.class);
        componentRegistry.put(HtmlTable.class, HtmlTable.class);
    }

    public CustomFieldDecorator(ElementLocatorFactory factory){
        super(factory);
    }

    @Override
    public Object decorate(ClassLoader loader, Field field){

        FindByLocator annotation = field.getAnnotation(FindByLocator.class);

        if(annotation == null){
            return super.decorate(loader, field);
        }

        String locatorKey = annotation.value();
        Class<?> fieldType = field.getType();

        if(componentRegistry.containsKey(fieldType)){
            try {
                Constructor<?> constructor =
                        fieldType.getConstructor(String.class);
                return constructor.newInstance(locatorKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}