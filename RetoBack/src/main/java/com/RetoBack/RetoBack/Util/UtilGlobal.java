package com.RetoBack.RetoBack.Util;

import org.json.JSONObject;

import java.util.Base64;

public class UtilGlobal {
    public String ObtieneUsuarioByToke(String token) {
        token = token.replace("Bearer ","");
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject jsonObject = new JSONObject(payload);
        return jsonObject.getString("sub");
    }
}
