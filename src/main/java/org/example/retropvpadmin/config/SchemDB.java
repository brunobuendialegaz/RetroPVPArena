package org.example.retropvpadmin.config;

public interface SchemDB {

    //tabla usuario
    String TAB_USUARIO = "usuario";
    String ID_USUARIO = "id_usuario";
    String ID_TIPO_USUARIO = "id_tipo_usuario";
    String U_NOMBRE = "nombre";
    String U_APELLIDO = "apellido";
    String U_EMAIL = "email";
    String U_DIRECCION = "direccion";
    String U_TLF = "telefono";
    String U_DNI = "DNI";

    //tabla Stock
    String TAB_STOCK = "stock";
    String ID_STOCK = "id_stock";
    String ID_ARTICULO = "id_articulo";
    String S_CANTIDAD = "cantidad";

    //tabla articulo
    String TAB_ARTICULO = "articulo";
    String A_NOMBRE = "nombre";
    String A_PRECIO = "precio";
    String A_DESCRIPCION = "description";

    //tabla consola
    String TAB_CONSOLA = "consola";
    String ANO_LANZAMIENTO = "anio_lanzamiento";

    //tabla juego
    String TAB_JUEGO = "juego";
    String ID_CONSOLA = "id_consola";
    String J_JUGADORES_PVP = "jugadores_pvp";

    //tabla accesorio
    String TAB_ACCESORIOS = "accesorio";

    //tabla torneo
    String TAB_TORNEO = "torneo";
    String ID_TORNEO = "id_torneo";
    String ID_SALA = "id_sala";
    String ID_JUEGO = "id_juego";
    String T_FECHA = "fecha";
    String T_ESTADO = "estado";
    String T_NOMBRE = "nombre";

    //tabla sala
    String TAB_SALA = "sala";
    String SL_NOMBRE = "nombre";
    String SL_TAMANIO = "tamanio";

    //tabla participacion
    String TAB_PARTICIPACION = "participacion";
    String P_FECHA = "fecha_registro";

    //tabla enfrentamientos
    String TAB_ENFRENTAMIENTO = "enfrentamiento";
    String ID_ENFRENTAMIENTO = "id_enfrentamiento";
    String E_NOMBRE = "nombre";
    String E_TOP = "top";
    String E_PARTICIPANTES = "participantes";

    //tabla rival
    String TAB_RIVAL = "rival";
    String R_GANADOR = "es_ganador";




}
