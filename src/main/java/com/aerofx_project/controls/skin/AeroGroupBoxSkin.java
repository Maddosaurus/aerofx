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

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;


/**
 * Created by Matthias on 12.06.2014.
 */
/*TODO: Invert clipping Rectangle! For now: White Label-Background */
public class AeroGroupBoxSkin extends StackPane implements AeroSkin{
    private Label titleLabel;
    private Rectangle clipRect;

    public final String getTitle(){return titleLabel.getText();}
    public final void setTitle(String value){titleLabel.setText(value);}

    public AeroGroupBoxSkin(){
        super();
        getStyleClass().add("group-box");
        titleLabel = new Label("");
        titleLabel.setStyle("-fx-background-color: white;");
        titleLabel.setLayoutX(9);
        titleLabel.setLayoutY(-7);
        getChildren().add(titleLabel);
        clipRect = new Rectangle();
//        setClip(clipRect);
    }

    @Override
    protected void layoutChildren() {
//        clipRect.setX(titleLabel.getLayoutX());
//        clipRect.setY(titleLabel.getLayoutY());
//        clipRect.setWidth(titleLabel.getWidth());
//        clipRect.setHeight(titleLabel.getHeight());
        for (Node c : getChildren()){
            layoutInArea(c, c.getLayoutX(), c.getLayoutY(), c.prefWidth(-1), c.prefHeight(-1), 0, HPos.LEFT, VPos.TOP);
        }
    }
}
