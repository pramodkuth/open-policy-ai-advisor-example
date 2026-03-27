package com.github.pramodkuth.openpolicyadvisor.config;

import com.github.pramodkuth.openpolicy.advisor.OpenPolicyAdvisor;
import com.github.pramodkuth.openpolicyadvisor.tools.UserTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {
    @Bean
    public ChatClient chatClient(OllamaChatModel model, UserTools userTools, CallAdvisor advisor) {
        return ChatClient.builder(model).defaultAdvisors(advisor)
                .defaultTools(userTools).build();
    }

    @Bean
    public CallAdvisor advisor(OpenPolicyAdvisor.Builder builder) {
        return builder.advisor("my-prompt-safety-advisor").build();
    }
}
