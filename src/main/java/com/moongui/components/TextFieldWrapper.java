package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class TextFieldWrapper extends UIComponent {
    private final TextField textField;

    public TextFieldWrapper(String text) {
        this.textField = new TextField(text);
    }

    @Override
    public Node getNode() {
        return textField;
    }

    public void setText(String text) {
        Platform.runLater(() -> textField.setText(text));
    }

    public String getText() {
        return textField.getText();
    }

    public void setPrompt(String text) {
        Platform.runLater(() -> textField.setPromptText(text));
    }

    public void setEditable(boolean editable) {
        Platform.runLater(() -> textField.setEditable(editable));
    }

    public void clear() {
        Platform.runLater(textField::clear);
    }
}