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

      public List<String> listarTimesPorLiga(String codigoLiga) {
            return repository.findTimesByLiga(codigoLiga);
      }

      @Transactional
      public List<Partida> atualizarDadosCampeonato(String codigoLiga){
            System.out.println("Buscando dados da liga: " +  codigoLiga);
            var response = client.getFinishedMatches(apiToken, codigoLiga);

            List<Partida> partidas = response.matches().stream()
                    .map(dto -> converterParaEntidade(dto, codigoLiga))
                    .collect(Collectors.toList());
            return repository.saveAll(partidas);
      }

      private Partida converterParaEntidade(MatchDTO dto, String codigoLiga) {
            Partida p = new Partida();
            p.setId(dto.id());
            p.setTimeCasa(dto.homeTeam().name());
            p.setTimeFora(dto.awayTeam().name());
            p.setDataHora(dto.utcDate().toLocalDateTime());
            p.setStatus(dto.status());
            p.setCompetitionCode(codigoLiga);

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
