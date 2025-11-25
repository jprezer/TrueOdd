package br.com.vaz.api_trueodd.controller;

import br.com.vaz.api_trueodd.model.Partida;
import br.com.vaz.api_trueodd.service.FutebolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

      private final FutebolService service;

      public PartidaController(FutebolService service) {
            this.service = service;
      }

      @PostMapping("/sincronizar")
      public List<Partida> sincronizar(){
            return service.atualizarDadosBrasileirao();
      }

      @GetMapping
      public List<Partida> listar(){
            return service.listarTodosJogos();
      }
}
