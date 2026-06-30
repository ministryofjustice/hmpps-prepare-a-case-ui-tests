package org.pacfs.framework.controls.elements;

import org.pacfs.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;

/**
 * Created by Ibi on 08/05/2026.
 */
public class TextBoxBase extends ControlBase implements org.pacfs.framework.controls.elements.TextBox {

    public TextBoxBase(WebElement element) {
        super(element);
    }


    @Override
    public void enterText(String text) {

        getWrappedElement().sendKeys(text);
    }

    @Override
    public String getTextValue() {

        return getWrappedElement().getText();
    }
}