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
 * of the authors and should not be intercodeted as recodesenting official policies,
 * either excodessed or implied, of the FreeBSD Project.
 */

package org.aerofx;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;

/**
 * <p>
 *     The facade that provides unified access to the AeroFX implementation by exposing an easy to use API.
 *     It currently contains the following functionality:
 *
 *     <ul>
 *         <li>Style your complete application with a Windows 7 look and feel</li>
 *         <li>Style a single TitledPane as a GroupBox</li>
 *         <li>Style all TitledPanes in your application as GroupBoxes</li>
 *     </ul>
 *
 *     <p>As of version 0.1-SNAPSHOT, AeroFX can style the following JavaFX-controls:</p>
 *     <ul>
 *         <li>Button</li>
 *         <li>TextField</li>
 *         <li>CheckBox</li>
 *         <li>RadioButton</li>
 *         <li>TabPane</li>
 *         <li>Hyperlink</li>
 *         <li>TitledPane</li>
 *         <li>TableView</li>
 *     </ul>
 *
 *     <h2>Important:</h2>
 *     <p>
 *         Please call all AeroFX-functions before calling <code>primaryStage.show()</code>!
 *         After calling show(), AeroFX-calls do not have the desired effect.
 *     </p>
 *
 *     <h3>Usage examples:</h3>
 *     <p>
 *         To style your application, call:
 *         <code>AeroFX.stlye();</code>
 *     </p>
 *     <p>
 *         To style a single TitledPane as a GroupBox, use
 *         <code>AeroFX.styleGroupBox(aTitledPane)</code>
 *     </p>
 *     <p>
 *         To style all TitledPanes in your application, call <code>AeroFX.styleAllAsGroupBox(root)</code> with your applications Parent-object
 *
 *     </p>
 * </p>
 *
 * @author Matthias Meidinger
 */
public class AeroFX {
    /**
     * A constant that holds the path to the main CSS file for AeroFX
     */
    private final static String AERO_CSS_NAME = AeroFX.class.getResource("win7.css").toExternalForm();

    /**
     * Styles an application with AeroFX
     */
    public static void style(){
        Application.setUserAgentStylesheet(AERO_CSS_NAME);
    }

    /**
     * Styles a given TitledPane as a Windows-like GroupBox
     * @param p TitledPane to be styled
     */
    public static void styleGroupBox(TitledPane p){
        p.getStyleClass().clear();
        p.getStyleClass().add("group-box");
    }

    /**
     * Styles all TitledPanes in the given parent as Windows-like GroupBoxes
     * @param p Parent to look for TitledPanes
     */
    public static void styleAllAsGroupBox(Parent p){
            for(Node a : p.getChildrenUnmodifiable()){
                if(a instanceof TitledPane) {
                    styleGroupBox((TitledPane) a);
                } else if(a instanceof TabPane) {
                    for(Tab t : ((TabPane)a).getTabs()) {
                        Node content = t.getContent();
                        if(content != null && content instanceof Parent) {
                            styleAllAsGroupBox((Parent) content);
                        }
                    }
                }
                else if(a instanceof Parent) {
                    styleAllAsGroupBox((Parent) a);
                }
            }
    }
}
