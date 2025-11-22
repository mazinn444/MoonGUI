package com.moongui.components;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

public class MenuBarWrapper extends UIComponent {
    private final MenuBar menuBar;

    public MenuBarWrapper() {
        this.menuBar = new MenuBar();
    }

    @Override
    public Node getNode() { return menuBar; }

    public void addMenu(String title, LuaTable items) {
        Platform.runLater(() -> {
            Menu menu = new Menu(title);
            int len = items.length();
            for (int i = 1; i <= len; i++) {
                LuaValue itemData = items.get(i);
                String label = itemData.get("label").toString();
                
                MenuItem mi = new MenuItem(label);
                LuaValue callback = itemData.get("action");
                
                if (!callback.isnil()) {
                    mi.setOnAction(e -> {
                        try { callback.call(); } catch (Exception ex) { ex.printStackTrace(); }
                    });
                }
                menu.getItems().add(mi);
            }
            menuBar.getMenus().add(menu);
        });
    }
}