-- consulta para el login, traigo el dato correo y dni de un usuario.

select email, dni from usuario 
    where email = 'bruno.buendia@gmail.com';

-- también traigo el usuario completo para integrarlo en la aplicación y llevar el login

select * from usuario 
    where email = 'bruno.buendia@gmail.com';

-- Consultas del panel general de la aplicación:
    -- count de usuarios registrados

select count(*) as total_usuarios_registrados
	from usuario u;

    -- Count de articulos con stock

select count(distinct a.id_articulo) as articulos_disponibles
    from articulo a
    join stock s on a.id_articulo = s.id_articulo 
    where s.cantidad > 0;
    
    -- articulos con poco stock, para revisar

select count(distinct a.id_articulo) as articulos_stock_bajo
    from articulo a
    join stock s on a.id_articulo = s.id_articulo 
    where s.cantidad <= 2;

    -- torneos pendientes

select count(distinct t.id_torneo ) as torneos_pendientes
    from torneo t 
    where t.estado <> 'terminado';

-- consultas panel usuario
    -- listado de todos los usuarios

select * from usuario;

    -- comprobar si un DNI esta duplicado

select exists(
    select 1 from usuario where DNI = '48704962R'
    );

-- consultas panel stock
    -- todos los productos

select * from articulo;

    -- todas las consolas para el combo de la pestaña de añadir articulo

select a.*, c.anio_lanzamiento  from articulo a
	inner join consola c on a.id_articulo = c.id_articulo;

-- Consultas del panel de torneos
    -- listado de torneos

select * from torneo;

    -- salas ocupadas por fecha

select s.*, t.fecha as ocupado from sala s
	inner join torneo t on t.id_sala = s.id_sala;

    -- Arbitro disponible

SELECT * FROM usuario u
    WHERE u.id_tipo_usuario = 1 
    AND NOT EXISTS (
      SELECT 1 
        FROM torneo t 
        WHERE t.id_usuario = u.id_usuario 
        AND DATE(t.fecha) = '2026-07-10' -- sustituyo por la fecha concreta del torneo, a la hora de la creación
  );

    -- Juegos pvp para elegir

select a.*, j.id_consola, j.jugadores_pvp  from articulo a
	inner join juego j on j.id_articulo = a.id_articulo 
	where j.jugadores_pvp > 0;

-- 