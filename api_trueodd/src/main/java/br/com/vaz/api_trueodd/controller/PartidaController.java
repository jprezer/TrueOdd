package br.com.vaz.api_trueodd.controller;

import br.com.vaz.api_trueodd.model.Partida;
import br.com.vaz.api_trueodd.service.AnaliseService;
import br.com.vaz.api_trueodd.service.FutebolService;
import org.springframework.web.bind.annotation.*;
import br.com.vaz.api_trueodd.dto.PrevisaoDTO;

import java.util.List;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

      private final AnaliseService analiseService;
      private final FutebolService service;

      public PartidaController(FutebolService service, AnaliseService analiseService) {
            this.service = service;
            this.analiseService = analiseService;
      }

      @PostMapping("/sincronizar")
      public List<Partida> sincronizar(){
            return service.atualizarDadosBrasileirao();
      }

      @GetMapping
      public List<Partida> listar(){
            return service.listarTodosJogos();
      }

      @GetMapping("/prever")
      public PrevisaoDTO prever(@RequestParam String casa, @RequestParam String fora) {
            return analiseService.preverJogo(casa, fora);
      }
}
