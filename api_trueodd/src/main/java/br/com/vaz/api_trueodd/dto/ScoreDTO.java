package br.com.vaz.api_trueodd.dto;

public record ScoreDTO(String winner, TimeScoreDTO fulltime) {
      public record TimeScoreDTO(Integer home, Integer away) {}
}
