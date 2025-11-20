package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class LabelWrapper extends UIComponent {
    private final Label label;

    public LabelWrapper(String text) {
        this.label = new Label(text);
    }

    @Override
    public Node getNode() {
        return label;
    }

    public void setText(String text) {
        Platform.runLater(() -> label.setText(text));
    }

    public String getText() {
        return label.getText();
    }
}