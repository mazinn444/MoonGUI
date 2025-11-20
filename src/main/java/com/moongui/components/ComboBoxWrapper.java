package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public class ComboBoxWrapper extends UIComponent {
    private final ComboBox<String> comboBox;

    public ComboBoxWrapper() {
        this.comboBox = new ComboBox<>();
    }

    @Override
    public Node getNode() {
        return comboBox;
    }

    public void addItem(String item) {
        Platform.runLater(() -> comboBox.getItems().add(item));
    }

    public void clearItems() {
        Platform.runLater(() -> comboBox.getItems().clear());
    }

    public String getSelected() {
        return comboBox.getValue();
    }

    public void select(int index) {
        Platform.runLater(() -> {
            if (index >= 0 && index < comboBox.getItems().size()) {
                comboBox.getSelectionModel().select(index);
            }
        });
    }
}