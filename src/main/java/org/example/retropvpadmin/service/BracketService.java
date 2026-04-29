package org.example.retropvpadmin.service;

import org.example.retropvpadmin.dao.interfaces.IEnfrentamientoDao;
import org.example.retropvpadmin.dao.interfaces.IRivalDao;
import org.example.retropvpadmin.util.BracketEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BracketService {

    private final IEnfrentamientoDao enfrentamientoDao;
    private final IRivalDao rivalDao;
    private final BracketEngine bracketEngine;

    public BracketService(IEnfrentamientoDao enfrentamientoDao, IRivalDao rivalDao) {
        this.enfrentamientoDao = enfrentamientoDao;
        this.rivalDao = rivalDao;
        this.bracketEngine = new BracketEngine();
    }

    public boolean generarBracket(int idTorneo, List<Integer> idsParticipantes) {
        List<Integer> participantes = new ArrayList<>(idsParticipantes);
        Collections.shuffle(participantes);

        int numParticipantes = participantes.size();
        int rondaInicial = bracketEngine.calcularRondaInicial(numParticipantes);
        int partidasPrevias = bracketEngine.calcularRondasPrevias(numParticipantes);
        String topRondaInicial = bracketEngine.calcularTop(rondaInicial);

        int indice = 0;

        // Crear partidas previas (play-in) — 2 rivales cada una
        for (int i = 0; i < partidasPrevias; i++) {
            int idEnf = enfrentamientoDao.crearEnfrentamiento(
                    "Previa " + (i + 1), "previa", 2
            );
            if (idEnf == -1) return false;

            rivalDao.asignarRival(participantes.get(indice++), idTorneo, idEnf);
            rivalDao.asignarRival(participantes.get(indice++), idTorneo, idEnf);
        }

        // Crear ronda inicial — clasificados directos ya entran,
        // el segundo hueco de cada partida lo ocupa el ganador de una previa
        int partidasRondaInicial = rondaInicial / 2;
        for (int i = 0; i < partidasRondaInicial; i++) {
            int idEnf = enfrentamientoDao.crearEnfrentamiento(
                    topRondaInicial + " " + (i + 1), topRondaInicial, 2
            );
            if (idEnf == -1) return false;

            // Solo asigno si queda clasificado directo
            if (indice < participantes.size()) {
                rivalDao.asignarRival(participantes.get(indice++), idTorneo, idEnf);
            }
            // El segundo hueco se rellena al avanzar el ganador de la previa
        }

        return true;
    }

    public boolean avanzarGanador(int idUsuario, int idTorneo, int idEnfrentamientoDestino) {
        return rivalDao.asignarRival(idUsuario, idTorneo, idEnfrentamientoDestino);
    }

    public boolean definirGanador(int idUsuario, int idTorneo, int idEnfrentamiento) {
        return rivalDao.marcarGanador(idUsuario, idTorneo, idEnfrentamiento);
    }

    public boolean sortearSiguienteRonda(int idTorneo, List<Integer> idsGanadores) {
        if (idsGanadores.size() < 2) return false;

        List<Integer> mezclados = new ArrayList<>(idsGanadores);
        Collections.shuffle(mezclados);

        // Calcular el top de la siguiente ronda
        String topSiguiente = bracketEngine.calcularTop(mezclados.size());
        int numPartidas = mezclados.size() / 2;

        for (int i = 0; i < numPartidas; i++) {
            int idEnf = enfrentamientoDao.crearEnfrentamiento(
                    topSiguiente + " " + (i + 1), topSiguiente, 2
            );
            if (idEnf == -1) return false;
            rivalDao.asignarRival(mezclados.get(i * 2),     idTorneo, idEnf);
            rivalDao.asignarRival(mezclados.get(i * 2 + 1), idTorneo, idEnf);
        }

        return true;
    }
}
