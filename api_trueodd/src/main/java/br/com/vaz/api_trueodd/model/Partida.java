package br.com.vaz.api_trueodd.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "partidas")
public class Partida {

      @Id
      private Long id; //mesmo id da api externa(football-data)

      private String timeCasa;
      private String timeFora;

      private Integer golsCasa;
      private Integer golsFora;

      private LocalDateTime dataHora;
      private String status; //finished ou scheduled

      public Partida() {}

      public Long getId() {
            return id;
      }

      public void setId(Long id) {
            this.id = id;
      }

      public String getTimeCasa() {
            return timeCasa;
      }

      public void setTimeCasa(String timeCasa) {
            this.timeCasa = timeCasa;
      }

      public String getTimeFora() {
            return timeFora;
      }

      public void setTimeFora(String timeFora) {
            this.timeFora = timeFora;
      }

      public Integer getGolsCasa() {
            return golsCasa;
      }

      public void setGolsCasa(Integer golsCasa) {
            this.golsCasa = golsCasa;
      }

      public Integer getGolsFora() {
            return golsFora;
      }

      public void setGolsFora(Integer golsFora) {
            this.golsFora = golsFora;
      }

      public LocalDateTime getDataHora() {
            return dataHora;
      }

      public void setDataHora(LocalDateTime dataHora) {
            this.dataHora = dataHora;
      }

      public String getStatus() {
            return status;
      }

      public void setStatus(String status) {
            this.status = status;
      }
}
