package com.moongui.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

public class JsonUtil {
    public static LuaValue toLua(Object json) {
        if (json instanceof JSONObject) {
            JSONObject jobj = (JSONObject) json;
            LuaTable table = new LuaTable();
            for (String key : jobj.keySet()) {
                table.set(key, toLua(jobj.get(key)));
            }
            return table;
        } else if (json instanceof JSONArray) {
            JSONArray jarr = (JSONArray) json;
            LuaTable table = new LuaTable();
            for (int i = 0; i < jarr.length(); i++) {
                table.set(i + 1, toLua(jarr.get(i)));
            }
            return table;
        } else if (json instanceof String) {
            return LuaValue.valueOf((String) json);
        } else if (json instanceof Integer) {
            return LuaValue.valueOf((Integer) json);
        } else if (json instanceof Double) {
            return LuaValue.valueOf((Double) json);
        } else if (json instanceof Boolean) {
            return LuaValue.valueOf((Boolean) json);
        }
        return LuaValue.NIL;
    }
}