package org.example.retropvpadmin.dao.interfaces;

public interface IRivalDao {

    boolean asignarRival(int idUsuario, int idTorneo, int idEnfrentamiento);

    boolean marcarGanador(int idUsuario, int idTorneo, int idEnfrentamiento);

}
