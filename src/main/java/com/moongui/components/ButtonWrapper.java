package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import org.luaj.vm2.LuaValue;

public class ButtonWrapper extends UIComponent {
    private final Button btn;

    public ButtonWrapper(String text) {
        this.btn = new Button(text);
    }

    @Override
    public Node getNode() {
        return btn;
    }

    public void setText(String text) {
        Platform.runLater(() -> btn.setText(text));
    }

    public String getText() {
        return btn.getText();
    }

    public void OnClick(LuaValue callback) {
        btn.setOnAction(e -> {
            try {
                if (callback != null && !callback.isnil()) {
                    callback.call();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}