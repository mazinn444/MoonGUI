package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowWrapper {
    private Pane root;
    private Stage stage;
    private boolean canMinimize = true;

    public WindowWrapper(String title, int width, int height) {
        Platform.runLater(() -> {
            stage = new Stage();
            stage.setTitle(title);
            root = new Pane();
            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);

            stage.iconifiedProperty().addListener((obs, oldVal, isMinimized) -> {
                if (isMinimized && !canMinimize) {
                    stage.setIconified(false);
                }
            });

            stage.show();
        });
    }

    public void add(Object component) {
        if (component instanceof UIComponent) {
            UIComponent comp = (UIComponent) component;
            Platform.runLater(() -> {
                if (root != null) {
                    root.getChildren().add(comp.getNode());
                }
            });
        }
    }

    public void setMinimizable(boolean allow) {
        this.canMinimize = allow;
        if (!allow) {
            Platform.runLater(() -> {
                if (stage != null && stage.isIconified()) {
                    stage.setIconified(false);
                }
            });
        }
    }

    public void setResizable(boolean resizable) {
        Platform.runLater(() -> {
            if (stage != null) {
                stage.setResizable(resizable);
            }
        });
    }

    public void setTitle(String title) {
        Platform.runLater(() -> {
            if (stage != null) {
                stage.setTitle(title);
            }
        });
    }

    public void close() {
        Platform.runLater(() -> {
            if (stage != null) {
                stage.close();
            }
        });
    }

    public void centerOnScreen() {
        Platform.runLater(() -> {
            if (stage != null) {
                stage.centerOnScreen();
            }
        });
    }
}