package br.com.vaz.api_trueodd.service;

import br.com.vaz.api_trueodd.client.FootballDataClient;
import br.com.vaz.api_trueodd.dto.MatchDTO;
import br.com.vaz.api_trueodd.dto.MatchesResponse;
import br.com.vaz.api_trueodd.model.Partida;
import br.com.vaz.api_trueodd.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FutebolService {

      private FootballDataClient client;
      private PartidaRepository repository;

      @Value("${football.api.token}")
      private String apiToken;

      public FutebolService(FootballDataClient client, PartidaRepository repository) {
            this.client = client;
            this.repository = repository;
      }
      @Transactional
      public List<Partida> atualizarDadosBrasileirao(){
            var response = client.getFinishedMatches(apiToken, "BSA");

            List<Partida> partidas = response.matches().stream()
                    .map(this::converterParaEntidade)
                    .collect(Collectors.toList());
            return repository.saveAll(partidas);
      }

      private Partida converterParaEntidade(MatchDTO dto) {
            Partida p = new Partida();
            p.setId(dto.id());
            p.setTimeCasa(dto.homeTeam().name());
            p.setTimeFora(dto.awayTeam().name());
            p.setDataHora(dto.utcDate().toLocalDateTime());
            p.setStatus(dto.status());

            // --- DEBUG: Vamos ver o que está chegando no console ---
            if (dto.score() == null) {
                  System.out.println("⚠️ Jogo " + dto.id() + ": Score veio NULO");
            } else if (dto.score().fullTime() == null) {
                  System.out.println("⚠️ Jogo " + dto.id() + ": fullTime veio NULO. Winner: " + dto.score().winner());
            }
            // -------------------------------------------------------

            if (dto.score() != null && dto.score().fullTime() != null) {
                  p.setGolsCasa(dto.score().fullTime().home());
                  p.setGolsFora(dto.score().fullTime().away());
            }
            return p;
      }

      public List<Partida> listarTodosJogos(){
            return repository.findAll();
      }
}
