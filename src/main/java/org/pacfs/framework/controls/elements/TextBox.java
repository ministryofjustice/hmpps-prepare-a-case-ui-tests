package org.pacfs.framework.controls.elements;

import org.pacfs.framework.controls.api.ImplementedBy;
import org.pacfs.framework.controls.internals.Control;

/**
 * Created by Ibi on 08/05/2026.
 */
@ImplementedBy(TextBoxBase.class)
public interface TextBox extends Control {

    void EnterText(String text);
    String GetTextValue();
}




