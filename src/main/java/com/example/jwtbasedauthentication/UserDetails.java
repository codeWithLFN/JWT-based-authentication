package com.example.jwtbasedauthentication;

import java.util.HashMap;
import java.util.Map;

public class UserDetails {
    private static final Map<String, String> users = new HashMap<>();

    static {
        users.put("user1", "password123");
    }

    public static boolean authenticate(String username, String password) {
        String storedPassword = users.get(username);
        return storedPassword!= null && storedPassword.equals(password);
    }
}
