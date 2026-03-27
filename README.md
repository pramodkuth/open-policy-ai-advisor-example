# Spring Boot AI Chat Agent with Open Policy Agent(OPA) Advisor

This is an example implementation of a Spring Boot AI Chat Agent that uses the [open-policy-ai-advisor-spring-boot-starter](https://github.com/pramodkuth/open-policy-ai-advisor-spring-boot-starter) to enforce governance and policy-driven guardrails.
By integrating Open Policy Agent (OPA), this agent can evaluate prompts and tool calls against custom Rego policies before they ever reach the LLM.
---

## 📦 Getting Started

### Prerequisites
To run this example, you need the following components active:
-   Ollama: For running local LLMs (e.g., Llama 3).
-   OPA Server: To evaluate your security and logic policies.
-   Docker: To easily spin up the OPA environment.

### 1. Start the OPA server
This repository includes a docker-compose.yml and rego policy file that starts the OPA server and automatically configure the policy.

```bash

cd opa
docker-compose up -d
```
### 2. Start Ollama
Ensure local ollama is served.
```bash

ollama run llama3

```
---
## 🚀 How to Run This Example
### 1. POM Dependency 
This app uses the custom advisor starter. It is already included in the pom.xml
```xml
<dependency>
    <groupId>io.github.pramodkuth</groupId>
    <artifactId>open-policy-ai-advisor-spring-boot-starter</artifactId>
    <version>0.0.1-M1</version>
</dependency>
```
### 2. Application configuration
The src/main/resources/application.yml is pre-configured to connect the ChatClient to both Ollama and the OPA sidecar.
```yaml
spring:
  ai:
    ollama:
      base-url: http://localhost:11434
      chat:
        options:
          model: llama3.1
open-policy:
  agent:
    host: http://localhost:8181
    resilience4j-instance: opa
  advisors:
    my-prompt-safety-advisor:
      policies:
        - name: test
          path: v1/data/agentpolicy/allow
          guard-prompt: true
resilience4j:
  circuitbreaker:
    instances:
      opa:
        sliding-window-size: 10
        failure-rate-threshold: 50
  retry:
    instances:
      opa:
        max-attempts: 3
        wait-duration: 500ms
```
---
## 🧪 Testing the Agent
### 1. Valid tool call
```bash

curl -X 'POST' \
  'http://localhost:8080/prompt' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "prompt": "create user"
}'
```
### 2. Blocked tool call
```bash

curl -X 'POST' \
  'http://localhost:8080/prompt' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "prompt": "delete user"
}'
```



