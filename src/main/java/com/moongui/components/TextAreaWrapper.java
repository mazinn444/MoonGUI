package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class TextAreaWrapper extends UIComponent {
    private final TextArea textArea;

    public TextAreaWrapper(String text) {
        this.textArea = new TextArea(text);
    }

    @Override
    public Node getNode() {
        return textArea;
    }

    public void setText(String text) {
        Platform.runLater(() -> textArea.setText(text));
    }

    public void appendText(String text) {
        Platform.runLater(() -> textArea.appendText(text));
    }

    public String getText() {
        return textArea.getText();
    }

    public void setWrapText(boolean wrap) {
        Platform.runLater(() -> textArea.setWrapText(wrap));
    }

    public void setEditable(boolean editable) {
        Platform.runLater(() -> textArea.setEditable(editable));
    }
}