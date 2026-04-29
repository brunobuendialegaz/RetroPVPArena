package org.example.retropvpadmin.dao.interfaces;

import org.example.retropvpadmin.model.Usuario;

import java.util.List;

public interface IParticipacionDao {

    List<Usuario> listadoParticipantes(int idTorneo);

    boolean anadirParticipante(int idUsuario, int idTorneo);

    boolean eliminarParticipante(int idUsuario, int idTorneo);

}
