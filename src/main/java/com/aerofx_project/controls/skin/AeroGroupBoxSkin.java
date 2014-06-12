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

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * Created by Shivy on 12.06.2014.
 */
public class AeroGroupBoxSkin extends StackPane implements AeroSkin{
    private Label titleLabel;

    public AeroGroupBoxSkin(){
        super();
        getStyleClass().add("group-box");
        titleLabel = new Label("The Title!");
        getChildren().add(titleLabel);
    }

    public AeroGroupBoxSkin(String boxTitle){
        super();
        getStyleClass().add("group-box");
        titleLabel = new Label(boxTitle);
        getChildren().add(titleLabel);
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        titleLabel.setLayoutX(5);
        titleLabel.setLayoutY(-8);
    }
}
