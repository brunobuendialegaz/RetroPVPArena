package org.example.retropvpadmin.dao.interfaces;

import org.example.retropvpadmin.model.Torneo;
import org.example.retropvpadmin.model.enums.TorneoEstadoEnum;

import java.util.List;

public interface ITorneoDao {

    long torneosProgramados();

    List<Torneo> listadoTorneos();

    boolean crearTorneo(Torneo torneo);

    boolean actualizarEstado(int idTorneo, TorneoEstadoEnum estado);

}
