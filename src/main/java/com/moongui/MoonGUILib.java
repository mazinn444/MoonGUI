package com.moongui;

import com.moongui.components.*;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class MoonGUILib {

    public WindowWrapper Window(String title, int width, int height) {
        return new WindowWrapper(title, width, height);
    }

    public ButtonWrapper Button(String text) {
        return new ButtonWrapper(text);
    }

    public LabelWrapper Label(String text) {
        return new LabelWrapper(text);
    }

    public TextFieldWrapper TextField(String text) {
        return new TextFieldWrapper(text);
    }
    
    public ImageWrapper Image(String path) {
        return new ImageWrapper(path);
    }

    public CheckBoxWrapper CheckBox(String text) {
        return new CheckBoxWrapper(text);
    }

    public PasswordFieldWrapper PasswordField(String text) {
        return new PasswordFieldWrapper(text);
    }

    public TextAreaWrapper TextArea(String text) {
        return new TextAreaWrapper(text);
    }

    public ComboBoxWrapper ComboBox() {
        return new ComboBoxWrapper();
    }

    public ProgressBarWrapper ProgressBar() {
        return new ProgressBarWrapper();
    }

    // methods

    public void Alert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public void Error(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}