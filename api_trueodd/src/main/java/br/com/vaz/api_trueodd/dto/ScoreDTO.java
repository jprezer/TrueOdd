package br.com.vaz.api_trueodd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScoreDTO(
        @JsonProperty("winner") String winner,
        @JsonProperty("fullTime") TimeScoreDTO fullTime
) {}