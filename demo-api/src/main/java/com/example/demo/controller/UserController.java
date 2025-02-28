package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final Map<Long, Map<String, String>> users = new HashMap<>();
    private long idCounter = 1;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", new ArrayList<>(users.values()));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, String> user) {
        user.put("id", String.valueOf(idCounter));
        users.put(idCounter++, user);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        if (users.containsKey(id)) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", users.get(id));
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body(Map.of("status", "error", "message", "User not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody Map<String, String> userDetails) {
        if (users.containsKey(id)) {
            userDetails.put("id", String.valueOf(id));
            users.put(id, userDetails);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", userDetails);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body(Map.of("status", "error", "message", "User not found"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> partiallyUpdateUser(@PathVariable Long id, @RequestBody Map<String, String> userDetails) {
        if (users.containsKey(id)) {
            Map<String, String> existingUser = users.get(id);
            userDetails.forEach(existingUser::put);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", existingUser);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body(Map.of("status", "error", "message", "User not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        if (users.containsKey(id)) {
            users.remove(id);
            return ResponseEntity.ok(Map.of("status", "success", "message", "User deleted successfully"));
        }
        return ResponseEntity.status(404).body(Map.of("status", "error", "message", "User not found"));
    }
}