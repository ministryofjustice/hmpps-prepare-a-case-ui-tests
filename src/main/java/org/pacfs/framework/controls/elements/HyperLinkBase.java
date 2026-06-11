package org.pacfs.framework.controls.elements;

import org.pacfs.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;

/**
 * Created by Ibi on 08/05/2026.
 */

public class HyperLinkBase extends ControlBase implements HyperLink {
    public HyperLinkBase(WebElement element) {
        super(element);
    }

    @Override
    public void ClickLink() {
        getWrappedElement().click();
    }

    @Override
    public String GetUrlText() {
        return getWrappedElement().getText();
    }

    @Override
    public boolean CheckUrlTextContains(String containsText) {
        if (getWrappedElement().getText().contains(containsText))
            return true;
        else
            return false;
    }
}
