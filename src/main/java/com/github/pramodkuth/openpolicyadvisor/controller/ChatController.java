package com.github.pramodkuth.openpolicyadvisor.controller;

import com.github.pramodkuth.openpolicyadvisor.model.UserPromptRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatClient client;

    public ChatController(ChatClient client) {
        this.client = client;
    }

    @PostMapping("/prompt")
    public ResponseEntity<String> prompt(@RequestBody UserPromptRequest promptRequest) {
        String res = client.prompt(promptRequest.getPrompt()).call().content();
        return ResponseEntity.ok(res);

    }
}
