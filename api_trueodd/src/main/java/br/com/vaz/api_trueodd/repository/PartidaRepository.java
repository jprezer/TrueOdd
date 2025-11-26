package br.com.vaz.api_trueodd.repository;

import br.com.vaz.api_trueodd.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
      List<Partida> findTop5ByTimeCasaOrderByDataHoraDesc(String timeCasa);

      List<Partida> findTop5ByTimeForaOrderByDataHoraDesc(String timeFora);

      List<Partida> findByCompetitionCode(String competitionCode);

      @Query("SELECT DISTINCT p.timeCasa FROM Partida p WHERE p.competitionCode = :liga UNION SELECT DISTINCT p.timeFora FROM Partida p WHERE p.competitionCode = :liga")
      List<String> findTimesByLiga(@Param("liga") String liga);
}

