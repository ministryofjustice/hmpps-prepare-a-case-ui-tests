package org.pacfs.framework.controls.elements;


import org.pacfs.framework.controls.api.ImplementedBy;
import org.pacfs.framework.controls.internals.Control;

/**
 * Created by Ibi on 08/05/2026.
 */

@ImplementedBy(org.pacfs.framework.controls.elements.HyperLinkBase.class)
public interface HyperLink extends Control {

    void ClickLink();
    String GetUrlText();
    boolean CheckUrlTextContains(String containsText);
}
