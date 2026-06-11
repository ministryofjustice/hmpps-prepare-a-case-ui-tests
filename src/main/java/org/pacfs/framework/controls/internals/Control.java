package org.pacfs.framework.controls.internals;


import org.pacfs.framework.controls.api.ImplementedBy;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.internal.Locatable;
//import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.WrapsElement;

/**
 * Created by Ibi on 08/05/2026.
 */

@ImplementedBy(org.pacfs.framework.controls.internals.ControlBase.class)
public interface Control extends WebElement, WrapsElement, Locatable {
    //todo: Custom Controls
//    ControlBase WaitForPage();

//    ControlBase WaitForVisible();

//    ControlBase Click();

//    ControlBase ScrollToElement();
}