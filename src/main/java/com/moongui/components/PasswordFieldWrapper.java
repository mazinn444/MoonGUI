package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;

public class PasswordFieldWrapper extends UIComponent {
    private final PasswordField passwordField;

    public PasswordFieldWrapper(String text) {
        this.passwordField = new PasswordField();
        this.passwordField.setText(text);
    }

    @Override
    public Node getNode() {
        return passwordField;
    }

    public void setText(String text) {
        Platform.runLater(() -> passwordField.setText(text));
    }

    public String getText() {
        return passwordField.getText();
    }

    public void setPrompt(String text) {
        Platform.runLater(() -> passwordField.setPromptText(text));
    }

    public void clear() {
        Platform.runLater(passwordField::clear);
    }
}