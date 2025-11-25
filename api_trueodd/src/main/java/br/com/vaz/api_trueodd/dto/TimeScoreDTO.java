package br.com.vaz.api_trueodd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TimeScoreDTO(
        @JsonProperty("home") Integer home,
        @JsonProperty("away") Integer away
) {}