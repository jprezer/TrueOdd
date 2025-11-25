package br.com.vaz.api_trueodd.dto;

public record PrevisaoDTO(
        String timeCasa,
        String timeFora,
        double probabilidadeCasa,
        double probabilidadeEmpate,
        double probabilidadeFora,
        double oddJustaCasa,   // A odd mínima para valer a pena apostar no time da casa
        double oddJustaEmpate,
        double oddJustaFora
) {}