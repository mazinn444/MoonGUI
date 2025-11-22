package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;

public class WindowWrapper {
    private Pane root;
    private Stage stage;
    private Scene scene;
    private boolean canMinimize = true;

    public WindowWrapper(String title, int width, int height) {
        Platform.runLater(() -> {
            stage = new Stage();
            stage.setTitle(title);
            root = new Pane();
            scene = new Scene(root, width, height);
            stage.setScene(scene);
            
            stage.iconifiedProperty().addListener((obs, oldVal, isMinimized) -> {
                if (isMinimized && !canMinimize) stage.setIconified(false);
            });
            stage.show();
        });
    }

    public void add(Object component) {
        if (component instanceof UIComponent) {
            UIComponent comp = (UIComponent) component;
            Platform.runLater(() -> {
                if (root != null) root.getChildren().add(comp.getNode());
            });
        }
    }

    public void setIcon(String path) {
        Platform.runLater(() -> {
            try {
                stage.getIcons().add(new Image(new FileInputStream(path)));
            } catch (Exception e) {
                System.err.println("Failed to load icon: " + path);
            }
        });
    }

    public void loadCSS(String path) {
        Platform.runLater(() -> {
            try {
                File f = new File(path);
                if (f.exists()) {
                    scene.getStylesheets().add(f.toURI().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void setMinimizable(boolean allow) {
        this.canMinimize = allow;
        if (!allow) Platform.runLater(() -> { if (stage.isIconified()) stage.setIconified(false); });
    }
    
    public void setResizable(boolean resizable) { Platform.runLater(() -> stage.setResizable(resizable)); }
    public void setTitle(String title) { Platform.runLater(() -> stage.setTitle(title)); }
    public void close() { Platform.runLater(stage::close); }
    public void centerOnScreen() { Platform.runLater(stage::centerOnScreen); }
}