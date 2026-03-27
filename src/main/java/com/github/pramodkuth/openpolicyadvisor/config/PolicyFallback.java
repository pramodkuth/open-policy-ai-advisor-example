package com.github.pramodkuth.openpolicyadvisor.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pramodkuth.openpolicy.client.OpaClientFallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PolicyFallback implements OpaClientFallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(PolicyFallback.class);

    @Override
    public JsonNode fallback(Throwable t) {
        LOGGER.info("Executing fallback method");
        ObjectMapper mapper = new ObjectMapper();
        FallbackResponse res = new FallbackResponse();
        return mapper.valueToTree(res);
    }

    public static class FallbackResponse {
        boolean result = true;

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }
    }
}
