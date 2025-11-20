package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import org.luaj.vm2.LuaValue;

public class CheckBoxWrapper extends UIComponent {
    private final CheckBox checkBox;

    public CheckBoxWrapper(String text) {
        this.checkBox = new CheckBox(text);
    }

    @Override
    public Node getNode() {
        return checkBox;
    }

    public boolean isChecked() {
        return checkBox.isSelected();
    }

    public void setChecked(boolean checked) {
        Platform.runLater(() -> checkBox.setSelected(checked));
    }

    public void OnToggle(LuaValue callback) {
        checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            try {
                if (callback != null && !callback.isnil()) {
                    callback.call(LuaValue.valueOf(newVal));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}