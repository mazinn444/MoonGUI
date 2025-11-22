package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import org.luaj.vm2.LuaValue;

public abstract class UIComponent {

    public abstract Node getNode();

    public void move(double x, double y) {
        Platform.runLater(() -> {
            getNode().setLayoutX(x);
            getNode().setLayoutY(y);
        });
    }

    public void resize(double width, double height) {
        Platform.runLater(() -> {
            if (getNode() instanceof Region) {
                Region region = (Region) getNode();
                region.setMinWidth(width);
                region.setMinHeight(height);
                region.setPrefSize(width, height);
                region.setMaxSize(width, height);
            }
        });
    }

    public void style(String css) {
        Platform.runLater(() -> getNode().setStyle(css));
    }

    public void setVisible(boolean visible) {
        Platform.runLater(() -> getNode().setVisible(visible));
    }

    public void setDisabled(boolean disabled) {
        Platform.runLater(() -> getNode().setDisable(disabled));
    }

    public void setID(String id) {
        Platform.runLater(() -> getNode().setId(id));
    }

    public void OnKeyPress(LuaValue callback) {
        getNode().setOnKeyPressed((KeyEvent e) -> {
            try {
                if (callback != null && !callback.isnil()) {
                    callback.call(LuaValue.valueOf(e.getCode().toString()));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public double getX() { return getNode().getLayoutX(); }
    public double getY() { return getNode().getLayoutY(); }
    public double getWidth() {
        return (getNode() instanceof Region) ? ((Region) getNode()).getWidth() : 0;
    }
    public double getHeight() {
        return (getNode() instanceof Region) ? ((Region) getNode()).getHeight() : 0;
    }
}