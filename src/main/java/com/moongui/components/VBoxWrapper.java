package com.moongui.components;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class VBoxWrapper extends UIComponent {
    private final VBox box;

    public VBoxWrapper() {
        this.box = new VBox();
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