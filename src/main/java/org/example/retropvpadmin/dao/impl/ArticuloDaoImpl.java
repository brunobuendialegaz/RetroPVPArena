package org.example.retropvpadmin.dao.impl;

import org.example.retropvpadmin.config.SchemDB;
import org.example.retropvpadmin.dao.interfaces.IArticuloDao;
import org.example.retropvpadmin.model.Articulo;

import java.util.List;

public class ArticuloDaoImpl implements IArticuloDao {
    @Override
    public List<Articulo> listadoArticulos() {
        String query = String.format("SELECT \n" +
                "    a.%s,\n" +
                "    a.%s,\n" +
                "    a.%s,\n" +
                "    `a`.`%s`,\n" +
                "    s.%s,\n" +
                "    s.%s,\n" +
                "    c.%s AS consola_anio,\n" +
                "    j.%s       AS juego_id_consola,\n" +
                "    j.%s AS juego_anio,\n" +
                "    j.%s,\n" +
                "    ac.%s      AS accesorio_id_consola,\n" +
                "    CASE \n" +
                "        WHEN c.%s  IS NOT NULL THEN 'consola'\n" +
                "        WHEN j.%s  IS NOT NULL THEN 'juego'\n" +
                "        WHEN ac.%s IS NOT NULL THEN 'accesorio'\n" +
                "        ELSE 'desconocido'\n" +
                "    END AS tipo\n" +
                "FROM %s a\n" +
                "LEFT JOIN %s     s  ON s.%s  = a.%s\n" +
                "LEFT JOIN %s   c  ON c.%s  = a.%s\n" +
                "LEFT JOIN %s     j  ON j.%s  = a.%s\n" +
                "LEFT JOIN %s ac ON ac.%s = a.%s\n" +
                "ORDER BY tipo, a.id_articulo%s;", SchemDB.ID_ARTICULO, SchemDB.A_NOMBRE, SchemDB.A_PRECIO, SchemDB.A_DESCRIPCION,
                SchemDB.ID_STOCK, SchemDB.S_CANTIDAD, SchemDB.ANO_LANZAMIENTO,SchemDB.J_JUGADORES_PVP, SchemDB.ID_CONSOLA,
                SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO, SchemDB.TAB_ARTICULO, SchemDB.TAB_STOCK,
                SchemDB.ID_STOCK, SchemDB.ID_STOCK, SchemDB.TAB_CONSOLA, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO,
                SchemDB.TAB_JUEGO, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO, SchemDB.TAB_ACCESORIOS, SchemDB.ID_ARTICULO, SchemDB.ID_ARTICULO
                );
    }
}
