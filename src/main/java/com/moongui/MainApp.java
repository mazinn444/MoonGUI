package com.moongui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.File;
import java.util.List;

public class MainApp extends Application {

    private final static String PROGRAM_VERSION = "1.1.2";

    @Override
    public void start(Stage primaryStage) {
        Platform.setImplicitExit(true);

        List<String> args = getParameters().getRaw();

        new Thread(() -> {
            try {

                String firstArg = args.get(0);

                if (args.isEmpty() || firstArg.equals("-h") || firstArg.equals("--help")) {
                    System.out.println("use: moongui <file.lua> [args...]");
                    System.out.println("       moongui -v (check version)");
                    System.exit(0);
                    return;
                }

                if (firstArg.equals("-v") || firstArg.equals("--version")) {
                    System.out.println("MoonGUI version " + PROGRAM_VERSION);
                    System.exit(0);
                    return;
                }

                File scriptFile = new File(firstArg);
                if (!scriptFile.exists()) {
                    System.err.println("Error: File not found -> " + firstArg);
                    System.exit(1);
                    return;
                }

                Globals globals = JsePlatform.standardGlobals();
                MoonGUILib lib = new MoonGUILib();
                globals.set("MoonGUI", CoerceJavaToLua.coerce(lib));

                LuaTable luaArgs = new LuaTable();
                luaArgs.set(0, LuaValue.valueOf(firstArg));

                for (int i = 1; i < args.size(); i++) {
                    luaArgs.set(i, LuaValue.valueOf(args.get(i)));
                }
                globals.set("arg", luaArgs);

                globals.get("dofile").call(LuaValue.valueOf(firstArg));

            } catch (Exception e) {
                System.err.println("Lua Script Error:");
                e.printStackTrace();
                System.exit(1);
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}