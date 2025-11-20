package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.Region;

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

    public double getX() {
        return getNode().getLayoutX();
    }

    public double getY() {
        return getNode().getLayoutY();
    }

    public double getWidth() {
        if (getNode() instanceof Region) {
            return ((Region) getNode()).getWidth();
        }
        return 0;
    }

    public double getHeight() {
        if (getNode() instanceof Region) {
            return ((Region) getNode()).getHeight();
        }
        return 0;
    }
}