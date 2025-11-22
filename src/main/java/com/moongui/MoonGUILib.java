package com.moongui;

import com.moongui.components.*;
import com.moongui.utils.JsonUtil;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.json.JSONObject;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.LuaTable;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MoonGUILib {

    public WindowWrapper Window(String t, int w, int h) { return new WindowWrapper(t, w, h); }
    public ButtonWrapper Button(String t) { return new ButtonWrapper(t); }
    public LabelWrapper Label(String t) { return new LabelWrapper(t); }
    public TextFieldWrapper TextField(String t) { return new TextFieldWrapper(t); }
    public PasswordFieldWrapper PasswordField(String t) { return new PasswordFieldWrapper(t); }
    public CheckBoxWrapper CheckBox(String t) { return new CheckBoxWrapper(t); }
    public TextAreaWrapper TextArea(String t) { return new TextAreaWrapper(t); }
    public ComboBoxWrapper ComboBox() { return new ComboBoxWrapper(); }
    public ProgressBarWrapper ProgressBar() { return new ProgressBarWrapper(); }
    public ImageWrapper Image(String p) { return new ImageWrapper(p); }

    public VBoxWrapper VBox() { return new VBoxWrapper(); }
    public HBoxWrapper HBox() { return new HBoxWrapper(); }
    public MenuBarWrapper MenuBar() { return new MenuBarWrapper(); }

    public void Alert(String msg) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(msg);
            alert.showAndWait();
        });
    }
    
    public void HTTP_Get(String url, LuaValue callback) {
        new Thread(() -> {
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                
                if (callback != null && !callback.isnil()) {
                    Platform.runLater(() -> callback.call(
                        LuaValue.valueOf(response.statusCode()),
                        LuaValue.valueOf(response.body())
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public LuaValue JSON_Parse(String jsonStr) {
        try {
            if (jsonStr.trim().startsWith("{")) {
                return JsonUtil.toLua(new JSONObject(jsonStr));
            } else if (jsonStr.trim().startsWith("[")) {
                return JsonUtil.toLua(new org.json.JSONArray(jsonStr));
            }
        } catch (Exception e) {
            System.err.println("JSON Parse Error");
        }
        return LuaValue.NIL;
    }
}