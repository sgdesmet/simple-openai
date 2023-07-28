package io.github.sashirestela.openai.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
  
  @JsonProperty("system") SYSTEM,
  
  @JsonProperty("user") USER,
  
  @JsonProperty("assistant") ASSISTANT,

  @JsonProperty("function") FUNCTION;
  
}