package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.InputStream;

public class ImageWrapper extends UIComponent {
    private final ImageView imageView;

    public ImageWrapper(String path) {
        this.imageView = new ImageView();
        this.imageView.setPreserveRatio(true);
        loadImage(path);
    }

    private void loadImage(String path) {
        try {
            InputStream stream = new FileInputStream(path);
            Image image = new Image(stream);
            imageView.setImage(image);
        } catch (Exception e) {
            System.err.println("Failed to load image: " + path);
        }
    }

    @Override
    public Node getNode() {
        return imageView;
    }

    @Override
    public void resize(double width, double height) {
        Platform.runLater(() -> {
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
        });
    }

    public void setImage(String path) {
        Platform.runLater(() -> loadImage(path));
    }

    public void setPreserveRatio(boolean preserve) {
        Platform.runLater(() -> imageView.setPreserveRatio(preserve));
    }
}