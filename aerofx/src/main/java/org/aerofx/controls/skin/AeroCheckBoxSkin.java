/*
 * Copyright (c) 2014, Matthias Meidinger
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those
 * of the authors and should not be interpreted as representing official policies,
 * either expressed or implied, of the FreeBSD Project.
 */

package org.aerofx.controls.skin;

import com.sun.javafx.scene.control.skin.CheckBoxSkin;
import javafx.beans.InvalidationListener;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Custom implementation of the CheckBoxSkin-class
 *
 * @author Matthias Medinger
 */
public class AeroCheckBoxSkin extends CheckBoxSkin implements AeroSkin {

    private Rectangle focusBorderRect;
    private InvalidationListener focusBorderListener;

    /**
     * Sets up a CheckBox with a Windows-style dotted focus border.
     * This border is styled by the CSS-class <code>check-box-focus-border</code>
     */
    public AeroCheckBoxSkin(CheckBox checkbox) {
        super(checkbox);
        focusBorderRect = new Rectangle(0,0, Color.TRANSPARENT);
        getChildren().add(focusBorderRect);
        focusBorderRect.setVisible(false);
        focusBorderRect.getStyleClass().add("check-box-focus-border");
        focusBorderListener = (e) -> focusBorderRect.setVisible(getSkinnable().isFocused());
        getSkinnable().focusedProperty().addListener(focusBorderListener);
    }

    /**
     * Override of layoutChildren to resize and position the focus rectangle
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        focusBorderRect.setX(x+17);
        focusBorderRect.setY(y+1);
        focusBorderRect.setWidth(w-14);
        focusBorderRect.setHeight(h-2);
    }

    /**
     * Deregisters all listeners
     */
    @Override
    public void dispose() {
        super.dispose();
        getSkinnable().focusedProperty().removeListener(focusBorderListener);
    }
}
