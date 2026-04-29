package org.example.retropvpadmin.dao.interfaces;

import org.example.retropvpadmin.model.Enfrentamiento;

import java.util.List;

public interface IEnfrentamientoDao {

    List<Enfrentamiento> listadoEnfrentamientos(int idTorneo);

    int crearEnfrentamiento(String nombre, String top, int participantes);

}
