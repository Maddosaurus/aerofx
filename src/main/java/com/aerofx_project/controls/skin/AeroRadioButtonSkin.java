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

package com.aerofx_project.controls.skin;

import com.sun.javafx.scene.control.skin.LabeledText;
import com.sun.javafx.scene.control.skin.RadioButtonSkin;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Matthias on 10.06.2014.
 */
public class AeroRadioButtonSkin extends RadioButtonSkin implements AeroSkin {
    /**
     * Used for laying out the label + radio together as a group
     * <p>
     * NOTE: This extra node should be eliminated in the future.
     * Instead, position inner nodes directly with the utility
     * functions in Pane (computeXOffset()/computeYOffset()).
     *
     * @param radioButton
     */
    private Rectangle focusBorderRect;
    private InvalidationListener focusBorderListener;

    public AeroRadioButtonSkin(RadioButton radioButton) {
        super(radioButton);
        focusBorderRect = new Rectangle(0, 0, Color.TRANSPARENT);
        getChildren().add(focusBorderRect);
        focusBorderRect.setVisible(false);
        focusBorderRect.getStyleClass().add("radio-button-focus-border");
        focusBorderListener = (e) -> focusBorderRect.setVisible(getSkinnable().isFocused());
        getSkinnable().focusedProperty().addListener(focusBorderListener);
//        System.out.println(getSkinnable().getToggleGroup().getToggles());
    }
//    public void test() {
//        getSkinnable().getToggleGroup().selectedToggleProperty().addListener((e) ());
//
//        getSkinnable().onKeyPressedProperty()
//
//                //unda dann requestfocus
//
//    }


    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);
        getChildren().stream().filter(child -> child instanceof LabeledText).forEach(child -> {
            focusBorderRect.setX(x+14);
            focusBorderRect.setY(y);
            focusBorderRect.setWidth(w-12);
            focusBorderRect.setHeight(h);
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        getSkinnable().focusedProperty().removeListener(focusBorderListener);
    }
}
