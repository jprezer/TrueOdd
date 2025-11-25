package br.com.vaz.api_trueodd.dto;

import java.time.ZonedDateTime;

public record MatchDTO(
      Long id,
      ZonedDateTime utcDate,
      String status,
      TeamDTO homeTeam,
      TeamDTO awayTeam,
      ScoreDTO score
      ){
}
