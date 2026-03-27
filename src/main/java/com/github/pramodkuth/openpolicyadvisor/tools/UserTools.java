package com.github.pramodkuth.openpolicyadvisor.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class UserTools {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserTools.class);

    @Tool(name = "createUser")
    public void create() {
        LOGGER.info("Creating user");
    }

    @Tool(name = "deleteUser")
    public void delete() {
        LOGGER.info("Deleting user");
    }
}
