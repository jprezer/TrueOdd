package br.com.vaz.api_trueodd.dto;

public record PrevisaoDTO(
        String timeCasa,
        String timeFora,
        double probabilidadeCasa,
        double probabilidadeEmpate,
        double probabilidadeFora,
        double oddJustaCasa,   //odd minima q faca valer a pena apostar no time da casa
        double oddJustaEmpate,
        double oddJustaFora,

        double oddCasaApostas,
        double valorEsperado,
        String recomendacao
) {}
