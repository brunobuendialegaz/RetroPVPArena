package org.example.retropvpadmin.dao.interfaces;

import org.example.retropvpadmin.model.Articulo;
import org.example.retropvpadmin.model.Consola;
import org.example.retropvpadmin.model.Stock;

import java.math.BigDecimal;
import java.util.List;

public interface IArticuloDao {

    List<Articulo> listadoArticulos();

    int checkIdArticulo(int idArticulo);

    boolean updateConsola(String idArticulo, String nombre, String precio, String description, int stock, int anioLanzamiento);

    boolean crearConsola(String nombre, String precio, String description, int stock, int anioLanzamiento);

    boolean updateJuego(String idArticulo, String nombre, String precio, String description, int stock, int idConsola, int anioLanzamiento, int jugadoresPvp);

    boolean crearJuego(String nombre, String precio, String description, int stock, int idConsola, int anioLanzamiento, int jugadoresPvp);

    boolean updateAccesorio(String idArticulo, String nombre, String precio, String description, int stock, int idConsola);

    boolean crearAccesorio(String nombre, String precio, String description, int stock, int idConsola);

}
