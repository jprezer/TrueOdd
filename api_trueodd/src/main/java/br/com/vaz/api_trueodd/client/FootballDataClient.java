package br.com.vaz.api_trueodd.client;

import br.com.vaz.api_trueodd.dto.MatchesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "footballData", url = "https://api.football-data.org/v4")
public interface FootballDataClient {
      @GetMapping("/competitions/{competition}/matches?status=FINISHED")
      MatchesResponse getFinishedMatches(
              @RequestHeader("X-Auth-Token")  String token,
              @PathVariable("competition") String competition
      );

      @GetMapping("/competitions/{competition}/matches?status=SCHEDULED")
      MatchesResponse getScheduledMatches(
              @RequestHeader("X-Auth-Token")  String token,
              @PathVariable("competition") String competition
      );
}
