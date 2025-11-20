package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;

public class ProgressBarWrapper extends UIComponent {
    private final ProgressBar progressBar;

    public ProgressBarWrapper() {
        this.progressBar = new ProgressBar(0);
    }

    @Override
    public Node getNode() {
        return progressBar;
    }

    public void setProgress(double value) {
        Platform.runLater(() -> progressBar.setProgress(value));
    }

    public double getProgress() {
        return progressBar.getProgress();
    }
    
    public void setIndeterminate() {
        Platform.runLater(() -> progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS));
    }
}