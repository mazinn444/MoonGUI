package com.moongui.components;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class HBoxWrapper extends UIComponent {
    private final HBox box;

    public HBoxWrapper() {
        this.box = new HBox();
    }

    @Override
    public Node getNode() { return box; }

    public void add(Object component) {
        if (component instanceof UIComponent) {
            Platform.runLater(() -> box.getChildren().add(((UIComponent) component).getNode()));
        }
    }

    public void setSpacing(double spacing) {
        Platform.runLater(() -> box.setSpacing(spacing));
    }

    public void setAlignment(String align) {
        Platform.runLater(() -> {
            try {
                box.setAlignment(Pos.valueOf(align.toUpperCase()));
            } catch (Exception e) {
                System.err.println("Invalid Alignment: " + align);
            }
        });
    }
}