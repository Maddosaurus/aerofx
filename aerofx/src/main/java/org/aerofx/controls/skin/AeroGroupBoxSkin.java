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

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TitledPane;
import javafx.scene.shape.Rectangle;


/**
 * Custom implementation of a JavaFX-TitledPane to mimic a Windows-like GroupBox
 *
 * @author Matthias Meidinger
 */
public class AeroGroupBoxSkin extends SkinBase<TitledPane> implements AeroSkin {
    private Label titleLabel;
    private Rectangle captionBg;
    private Rectangle groupBoxBg;
    private Rectangle clippingRect;

    InvalidationListener focusListener;

    /**
     * Takes a TitledPane, styles it as GroupBox and binds the textProperty to the title.
     * The border can be style via the CSS-class <code>group-box-border</code>
     * @param p Pane to be styled
     */
    public AeroGroupBoxSkin(TitledPane p) {
        super(p);
        titleLabel = new Label("");
        titleLabel.textProperty().bind(p.textProperty());
        getChildren().add(titleLabel);
        captionBg = new Rectangle();
        p.setCollapsible(false);
        captionBg.setStyle("-fx-fill:transparent;");
        groupBoxBg = new Rectangle();
        groupBoxBg.setStyle("-fx-fill:transparent;");
        clippingRect = new Rectangle();
        getChildren().add(groupBoxBg);
        groupBoxBg.getStyleClass().add("group-box-border");
        if (p.getContent() != null)
            getChildren().add(p.getContent());
        p.setPadding(new Insets(7, 0, 0, 0));

        focusListener = new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if (p.isFocused()) {
                    Node content = p.getContent();
                    if (content != null && content instanceof Parent) {
                        if (((Parent) content).getChildrenUnmodifiable().size() > 0)
                            ((Parent) content).getChildrenUnmodifiable().get(0).requestFocus();
                    }
                }
            }
        };
        p.focusedProperty().addListener(focusListener);
    }


    /**
     * Override to have full control over layout.
     * The background has a clip, as the border otherwise would cut through the title text.
     */
    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);

        titleLabel.relocate(x + 9, y +1);
        captionBg.relocate(7, -7);
        captionBg.setWidth(titleLabel.getWidth()+4);
        captionBg.setHeight(titleLabel.getHeight());

        groupBoxBg.relocate(x, y + 7);
        groupBoxBg.setWidth(w);
        groupBoxBg.setHeight(h - 7);

        clippingRect.relocate(0, 0);
        clippingRect.setWidth(groupBoxBg.getWidth());
        clippingRect.setHeight(groupBoxBg.getHeight());

        groupBoxBg.setClip(Rectangle.subtract(clippingRect, captionBg));

        if (getSkinnable().getContent() != null) {
            getSkinnable().getContent().relocate(x, y);
            getSkinnable().getContent().resize(w, h);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        getSkinnable().focusedProperty().removeListener(focusListener);
    }
}


