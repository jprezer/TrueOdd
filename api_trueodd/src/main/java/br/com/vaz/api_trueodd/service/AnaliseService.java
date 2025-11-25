package br.com.vaz.api_trueodd.service;

import br.com.vaz.api_trueodd.dto.MatchDTO;
import br.com.vaz.api_trueodd.dto.PrevisaoDTO;
import br.com.vaz.api_trueodd.model.Partida;
import br.com.vaz.api_trueodd.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseService {
      private final PartidaRepository repository;

      public AnaliseService(PartidaRepository repository) {
            this.repository = repository;
      }

      public PrevisaoDTO preverJogo(String timeCasa, String timeFora, double oddCasa) {
            List<Partida> ultimosCasa = repository.findTop5ByTimeCasaOrderByDataHoraDesc(timeCasa);
            List<Partida> ultimosFora = repository.findTop5ByTimeForaOrderByDataHoraDesc(timeFora);

            if(ultimosCasa.isEmpty() || ultimosFora.isEmpty()){
                  return new PrevisaoDTO(timeCasa, timeFora, 0,0,0,0,0,0, 0,0,"Dados insuficientes");
            }

            double mediaGolsFeitosCasa = ultimosCasa.stream().mapToInt(Partida::getGolsCasa).average().orElse(1.0);
            double mediaGolsSofridosCasa = ultimosCasa.stream().mapToInt(Partida::getGolsFora).average().orElse(1.0);

            double mediaGolsFeitosFora = ultimosFora.stream().mapToInt(Partida::getGolsFora).average().orElse(1.0);
            double mediaGolsSofridosFora = ultimosFora.stream().mapToInt(Partida::getGolsCasa).average().orElse(1.0);

            double lambdaCasa = (mediaGolsFeitosCasa + mediaGolsSofridosFora) / 2.0;
            double lambdaFora = (mediaGolsFeitosFora + mediaGolsSofridosCasa) / 2.0;

            double probCasa = 0.0;
            double probEmpate = 0.0;
            double probFora = 0.0;

            for(int i = 0; i < 5; i++) {
                  for(int j = 0; j < 5; j++) {
                        double p = poisson(i, lambdaCasa) * poisson(j, lambdaFora);

                        if(i > j) probCasa += p;
                        else if (i == j) probEmpate += p;
                        else probFora += p;
                  }
            }

            double total = probCasa + probEmpate + probFora;
            probCasa /= total;
            probEmpate /= total;
            probFora /= total;

            double ev = (probCasa * oddCasa) - 1.0;

            String analise;
            if(ev > 0.05) analise = "✅ ESSA É GREEN PAPAI " + timeCasa;
            else if (ev > 0) analise = "⚠️ APOSTA ARRISCADA (Margem pequena) " + timeFora;
            else analise = "❌ NÃO APOSTE";


            return new PrevisaoDTO(
                    timeCasa,
                    timeFora,
                    Math.round(probCasa * 100),
                    Math.round(probEmpate * 100),
                    Math.round(probFora * 100),
                    arredondar(1 / probCasa),
                    arredondar(1 / probEmpate),
                    arredondar(1 / probFora),
                    oddCasa,
                    arredondar(ev*100),
                    analise
            );
      }

      // Fórmula matemática de Poisson: P(k; λ) = (e^-λ * λ^k) / k!
      private double poisson(int k, double lambda) {
            return (Math.pow(Math.E, -lambda) * Math.pow(lambda, k )) / fatorial(k);
      }

      private long fatorial(int n) {
            if(n <= 1) return 1;
            return n * fatorial(n - 1);
      }

      private double arredondar(double valor) {
            return Math.round(valor * 100) / 100.0;

      }
}
