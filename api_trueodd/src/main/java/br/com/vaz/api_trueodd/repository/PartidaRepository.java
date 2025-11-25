package br.com.vaz.api_trueodd.repository;

import br.com.vaz.api_trueodd.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
      List<Partida> findTop5ByTimeCasaOrderByDataHoraDesc(String timeCasa);

      List<Partida> findTop5ByTimeForaOrderByDataHoraDesc(String timeFora);
}

