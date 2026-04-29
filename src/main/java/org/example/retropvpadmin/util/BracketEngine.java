package org.example.retropvpadmin.util;

public class BracketEngine {

    public int calcularRondaInicial(int numeroParticipanes){
        int potencia = 1;
        while (potencia * 2 <= numeroParticipanes){
            potencia *= 2;
        }
        return potencia;
    }

    public int calcularRondasPrevias(int numeroParticipantes){
        int rondaInicial = calcularRondaInicial(numeroParticipantes);
        return numeroParticipantes-rondaInicial;
    }

    public int calcularClasificadosDirectos(int numParticipantes){
        int rondaInicial = calcularRondaInicial(numParticipantes);
        int enPartidaPrevia = calcularRondasPrevias(numParticipantes) * 2;
        return numParticipantes - enPartidaPrevia;
    }

    public String calcularTop(int participantesRonda){
        return switch (participantesRonda) {
            case 2 -> "final";
            case 4 -> "semifinal";
            case 8 -> "cuartos";
            case 16 -> "octavos";
            case 32 -> "dieciseisavos";
            default -> "previa";
        };
    }
}
