package org.pacfs.framework.controls.elements;


import org.pacfs.framework.controls.api.ImplementedBy;
import org.pacfs.framework.controls.internals.Control;

/**
 * Created by Ibi on 08/05/2026.
 */
@ImplementedBy(ButtonBase.class)
public interface Button extends Control {

    void performClick();

    String getButtonTest();

    void performSubmit();
}
