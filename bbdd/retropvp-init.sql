-- inserts de datos a todas las tablas.

INSERT INTO tipo_usuario (id_tipo_usuario, tipo) VALUES 
(1, 'Administrador'),
(2, 'Jugador'),

INSERT INTO sala (nombre, tamanio) VALUES
('sala principal', 32),
('sala pequeña', 16);

INSERT INTO articulo (nombre, precio, description) VALUES
('Super Nintendo', 49.99, 'Consola de 16 bits de Nintendo'),
('Sega Mega Drive', 55.00, 'Consola de 16 bits de Sega'),
('Nintendo 64', 89.90, 'Primera consola de 64 bits de Nintendo'),
('PlayStation 1', 45.00, 'Primera consola de Sony que usó CD-ROM'),
('Game Boy', 60.00, 'Consola portátil de 8 bits con pantalla monocroma'),
('Atari 2600', 75.50, 'Consola pionera de la segunda generación'),
('Sega Dreamcast', 120.00, 'Última consola de Sega, pionera en juego online'),
('NES', 50.00, 'Nintendo Entertainment System de 8 bits'),
('Super Mario Bros 3', 35.00, 'Aclamado juego de plataformas para NES'),
('Sonic The Hedgehog', 25.50, 'Juego que introdujo a la mascota rápida de Sega'),
('Tetris', 15.00, 'Juego de puzzles imprescindible de Game Boy'),
('Zelda Ocarina Time', 45.99, 'Épica aventura en 3D para Nintendo 64'),
('Final Fantasy VII', 50.00, 'RPG revolucionario lanzado en PlayStation 1'),
('Street Fighter II', 30.00, 'Juego de lucha clásico para Super Nintendo'),
('Super Metroid', 65.00, 'Aventura de exploración espacial para SNES'),
('Chrono Trigger', 80.00, 'Legendario juego de rol japonés para SNES'),
('Pac-Man', 20.00, 'El famoso comecocos en versión para Atari'),
('Shenmue', 55.00, 'Aventura pionera en mundo abierto para Dreamcast'),
('Mando SNES', 15.99, 'Mando original con cruceta para Super Nintendo'),
('Mando N64', 22.50, 'Mando de tres picos con joystick para N64'),
('Memory Card PS1', 10.00, 'Tarjeta de memoria de 1MB para guardar partidas'),
('Rumble Pak N64', 18.00, 'Accesorio que añade vibración al mando de N64'),
('Pistola NES Zapper', 25.00, 'Pistola de luz para jugar a Duck Hunt'),
('Cable Link Game Boy', 12.00, 'Cable para conectar dos Game Boy y jugar juntos'),
('Sega VMU', 20.00, 'Tarjeta de memoria con pantalla para Dreamcast'),
('Super Smash Bross', 70.00, 'Increible juego de peleas con las IP de Nintendo');

INSERT INTO usuario (id_tipo_usuario, nombre ,apellido, email, direccion, telefono, dni) VALUES 
(1,'Bruno','Buendia Legaz','bruno.buendia@gmail.com','Librilla - Murcia','697161397','11223344S'),
(2,'Maria','Gomez Sanchez','maria.gs@gmail.com','Alhama de Murcia - Murcia','612345678','22334455K'),
(2,'Juan','Perez Lopez','jperez@hotmail.com','Alcantarilla - Murcia','622334455','33445566G'),
(2,'Carmen','Martinez Ruiz','carmen.m@yahoo.es','Totana - Murcia','633445566','44556677T'),
(2,'Jose','Garcia Fernandez','jgarciaf@gmail.com','Sangonera la Verde - Murcia','644556677','55667788P'),
(2,'Ana','Navarro Diaz','ananavarro@gmail.com','El Palmar - Murcia','655667788','66778899V'),
(2,'Antonio','Romero Cavas','antonio.rc@hotmail.com','Sangonera la Seca - Murcia','666778899','77889900H'),
(2,'Laura','Vidal Marin','lvidalm@gmail.com','Mula - Murcia','677889900','88990011C'),
(2,'Francisco','Gallego Ortiz','pacogo@gmail.com','Pliego - Murcia','688990011','99001122S'),
(1,'Pablo','Tome','tomesito_pablix@empresa.com','Murcia - Murcia','699001122','10293847C'),
(1,'Dani','Meco','temeto_un_meco@gmail.com','Fuente Alamo - Murcia','600112233','56473829L'),
(2,'Isabel','Rubio Blazquez','isabelrubio@yahoo.com','Corvera - Murcia','611223344','82736451B'),
(2,'David','Ortiz Moreno','dortiz@gmail.com','Alhama de Murcia - Murcia','622334455','91827364P'),
(2,'Marta','Gimenez Ruiz','martagim@hotmail.com','Alcantarilla - Murcia','633445566','37485960M'),
(2,'Javier','Hernandez Gil','javihernandez@gmail.com','Totana - Murcia','644556677','28394051X'),
(2,'Lucia','Cano Martinez','luciacano@yahoo.es','El Palmar - Murcia','655667788','65748392M'),
(2,'Carlos','Sanchez Perez','carlossanchez@gmail.com','Sangonera la Verde - Murcia','666778899','49586730A'),
(2,'Sofia','Diaz Gomez','sofiadg@hotmail.com','Mula - Murcia','677889900','15263748W'),
(2,'Manuel','Lopez Garcia','manulopez@gmail.com','Murcia - Murcia','688990011','74839201E'),
(1,'Daniel','Villacampa','daniel.admin@empresa.com','Alhama de Murcia - Murcia','699001122','59607182T'),
(2,'Pablo','Ruiz Navarro','pabloruiz@gmail.com','Pliego - Murcia','600112233','31425364X'),
(2,'Paula','Marin Serrano','paulamarin@yahoo.com','Alcantarilla - Murcia','611223344','85940312F'),
(2,'Alejandro','Gomez Romero','alegomez@gmail.com','Corvera - Murcia','622334455','60718293Q'),
(2,'Sara','Perez Vidal','sarapv@hotmail.com','Fuente Alamo - Murcia','633445566','27384950R'),
(2,'Daniel','Martinez Gallego','danielmg@gmail.com','Totana - Murcia','644556677','94051627Y'),
(2,'Nerea','Garcia Molina','nereagm@yahoo.es','Sangonera la Seca - Murcia','655667788','48576920M'),
(2,'Jorge','Navarro Rubio','jorgenr@gmail.com','El Palmar - Murcia','666778899','19283746L'),
(2,'Alba','Romero Ortiz','albaro@hotmail.com','Alhama de Murcia - Murcia','677889900','50617283R'),
(2,'Diego','Vidal Gimenez','diegovg@gmail.com','Mula - Murcia','688990011','73849506S'),
(1,'Marina','Gallego Hernandez','marina.it@empresa.com','Murcia - Murcia','699001122','32415067N'),
(2,'Adrian','Serrano Cano','adriansc@gmail.com','Alcantarilla - Murcia','600112233','81920374V'),
(2,'Clara','Molina Sanchez','claramolina@yahoo.com','Totana - Murcia','611223344','46573829F'),
(2,'Ruben','Rubio Diaz','rubenrd@gmail.com','Librilla - Murcia','622334455','90123456F');

INSERT INTO stock (id_articulo, cantidad ) VALUES 
(1, 15),
(2, 8),
(3, 0),
(4, 12),
(5, 5),
(6, 20),
(7, 3),
(8, 17),
(9, 9),
(10, 1),
(11, 14),
(12, 6),
(13, 11),
(14, 19),
(15, 2),
(16, 18),
(17, 7),
(18, 4),
(19, 13),
(20, 10),
(21, 16),
(22, 2),
(23, 9),
(24, 0),
(25, 12);

INSERT INTO consola (id_articulo, anio_lanzamiento) VALUES
(1, 1990),
(2, 1988),
(3, 1996),
(4, 1994),
(5, 1989),
(6, 1977),
(7, 1998),
(8, 1983);

INSERT INTO juego (id_articulo, id_consola, anio_lanzamiento, jugadores_pvp) VALUES
(9, 8, 1988, 2),
(10, 2, 1991, 0),
(11, 5, 1989, 2),
(12, 3, 1998, 0),
(13, 4, 1997, 0),
(14, 1, 1992, 2),
(15, 1, 1994, 0),
(16, 1, 1995, 0),
(17, 6, 1982, 2),
(18, 7, 1999, 0),
(26, 3, 1999, 4);

INSERT INTO accesorio (id_articulo, id_consola) VALUES
(19, 1),
(20, 3),
(21, 4),
(22, 3),
(23, 8),
(24, 5),
(25, 7);

INSERT INTO pedido (id_pedido, id_usuario, fecha, importe) VALUES
(1, 2, '2025-03-15', 95.98),
(2, 10, '2025-04-20', 115.00),
(3, 5, '2025-06-11', 75.00),
(4, 12, '2025-08-05', 205.89),
(5, 25, '2025-09-22', 110.00),
(6, 30, '2025-10-14', 215.00),
(7, 3, '2025-11-03', 80.50),
(8, 15, '2025-11-25', 80.00),
(9, 22, '2025-12-10', 65.00),
(10, 7, '2026-01-08', 95.50),
(11, 33, '2026-01-15', 129.99),
(12, 1, '2026-02-02', 157.40),
(13, 19, '2026-02-28', 162.00),
(14, 11, '2026-03-10', 55.00),
(15, 28, '2026-04-05', 50.00);

INSERT INTO articulo_pedido (id_articulo, id_pedido, unidades) VALUES
(1, 1, 1),
(14, 1, 1),
(19, 1, 1),
(4, 2, 1),
(13, 2, 1),
(21, 2, 2),
(5, 3, 1),
(11, 3, 1),
(3, 4, 1),
(12, 4, 1),
(26, 4, 1),
(8, 5, 1),
(9, 5, 1),
(23, 5, 1),
(7, 6, 1),
(18, 6, 1),
(25, 6, 2),
(2, 7, 1),
(10, 7, 1),
(16, 8, 1),
(15, 9, 1),
(6, 10, 1),
(17, 10, 1),
(1, 11, 1),
(16, 11, 1),
(3, 12, 1),
(20, 12, 3),
(5, 13, 2),
(24, 13, 1),
(11, 13, 2),
(4, 14, 1),
(21, 14, 1),
(13, 15, 1);

INSERT INTO torneo (id_torneo, id_usuario, id_sala, id_juego, fecha, estado) VALUES
(1, 1, 1, 14, '2026-05-15 10:00:00', 'terminado'),
(2, 10, 2, 11, '2026-06-20 17:00:00', 'terminado'),
(3, 20, 1, 26, '2026-07-10 12:00:00', 'creado');

'creado', 'iniciado', 'terminado'

INSERT INTO participacion (id_torneo, id_usuario) VALUES
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(2, 11),
(2, 12),
(2, 13),
(2, 14),
(2, 15),
(2, 16),
(2, 17),
(2, 18),
(3, 2),
(3, 4),
(3, 6),
(3, 8),
(3, 12),
(3, 14),
(3, 16),
(3, 18),
(3, 21),
(3, 22),
(3, 23),
(3, 24),
(3, 25),
(3, 27),
(3, 28),
(3, 29);

INSERT INTO enfrentamiento (id_enfrentamiento, nombre, top, participantes) VALUES
(1, 'SF2 - Cuartos 1', 'cuartos', 2),
(2, 'SF2 - Cuartos 2', 'cuartos', 2),
(3, 'SF2 - Cuartos 3', 'cuartos', 2),
(4, 'SF2 - Cuartos 4', 'cuartos', 2),
(5, 'SF2 - Semi 1', 'semifinal', 2),
(6, 'SF2 - Semi 2', 'semifinal', 2),
(7, 'SF2 - Final', 'final', 2),
(8, 'Tetris - Cuartos 1', 'cuartos', 2),
(9, 'Tetris - Cuartos 2', 'cuartos', 2),
(10, 'Tetris - Cuartos 3', 'cuartos', 2),
(11, 'Tetris - Cuartos 4', 'cuartos', 2),
(12, 'Tetris - Semi 1', 'semifinal', 2),
(13, 'Tetris - Semi 2', 'semifinal', 2),
(14, 'Tetris - Final', 'final', 2),
(15, 'Smash - Cuartos 1', 'cuartos', 4),
(16, 'Smash - Cuartos 2', 'cuartos', 4),
(17, 'Smash - Cuartos 3', 'cuartos', 4),
(18, 'Smash - Cuartos 4', 'cuartos', 4),
(19, 'Smash - Final', 'final', 4);


INSERT INTO rival (id_usuario, id_torneo, id_enfrentamiento, es_ganador) VALUES
(2, 1, 1, TRUE),  (3, 1, 1, FALSE),
(4, 1, 2, FALSE), (5, 1, 2, TRUE),
(6, 1, 3, TRUE),  (7, 1, 3, FALSE),
(8, 1, 4, FALSE), (9, 1, 4, TRUE),
(2, 1, 5, TRUE),  (5, 1, 5, FALSE),
(6, 1, 6, FALSE), (9, 1, 6, TRUE),
(2, 1, 7, FALSE), (9, 1, 7, TRUE),
(11, 2, 8, FALSE), (12, 2, 8, TRUE),
(13, 2, 9, TRUE),  (14, 2, 9, FALSE),
(15, 2, 10, FALSE),(16, 2, 10, TRUE),
(17, 2, 11, TRUE), (18, 2, 11, FALSE),
(12, 2, 12, TRUE), (13, 2, 12, FALSE),
(16, 2, 13, FALSE),(17, 2, 13, TRUE),
(12, 2, 14, TRUE), (17, 2, 14, FALSE),
(2, 3, 15, FALSE), (4, 3, 15, TRUE),  (6, 3, 15, FALSE), (8, 3, 15, FALSE), 
(12, 3, 16, FALSE),(14, 3, 16, FALSE),(16, 3, 16, TRUE), (18, 3, 16, FALSE),
(21, 3, 17, TRUE), (22, 3, 17, FALSE),(23, 3, 17, FALSE),(24, 3, 17, FALSE),
(25, 3, 18, FALSE),(27, 3, 18, FALSE),(28, 3, 18, FALSE),(29, 3, 18, TRUE),
(4, 3, 19, FALSE), (16, 3, 19, FALSE),(21, 3, 19, TRUE), (29, 3, 19, FALSE);

-- version comentada

INSERT INTO enfrentamiento (id_enfrentamiento, nombre, top, participantes) VALUES
/* -----------------------------------------------------
   TORNEO 1 - Street Fighter II (8 jugadores, 1 vs 1) 
   ----------------------------------------------------- */
(1, 'SF2 - Cuartos 1', 'cuartos', 2),
(2, 'SF2 - Cuartos 2', 'cuartos', 2),
(3, 'SF2 - Cuartos 3', 'cuartos', 2),
(4, 'SF2 - Cuartos 4', 'cuartos', 2),
(5, 'SF2 - Semi 1', 'semifinal', 2),
(6, 'SF2 - Semi 2', 'semifinal', 2),
(7, 'SF2 - Final', 'final', 2),

/* -----------------------------------------------------
   TORNEO 2 - Tetris (8 jugadores, 1 vs 1) 
   ----------------------------------------------------- */
(8, 'Tetris - Cuartos 1', 'cuartos', 2),
(9, 'Tetris - Cuartos 2', 'cuartos', 2),
(10, 'Tetris - Cuartos 3', 'cuartos', 2),
(11, 'Tetris - Cuartos 4', 'cuartos', 2),
(12, 'Tetris - Semi 1', 'semifinal', 2),
(13, 'Tetris - Semi 2', 'semifinal', 2),
(14, 'Tetris - Final', 'final', 2),

/* -----------------------------------------------------
   TORNEO 3 - Smash Bros (16 jugadores, 4 contra 4) 
   (4 enfrentamientos de cuartos -> Los 4 ganadores a la Final)
   ----------------------------------------------------- */
(15, 'Smash - Cuartos 1', 'cuartos', 4),
(16, 'Smash - Cuartos 2', 'cuartos', 4),
(17, 'Smash - Cuartos 3', 'cuartos', 4),
(18, 'Smash - Cuartos 4', 'cuartos', 4),
(19, 'Smash - Final', 'final', 4);


INSERT INTO rival (id_usuario, id_torneo, id_enfrentamiento, es_ganador) VALUES
/* =====================================================
   RESULTADOS TORNEO 1: Street Fighter II
   ===================================================== */
/* --- CUARTOS DE FINAL --- */
(2, 1, 1, TRUE),  (3, 1, 1, FALSE),  /* Gana 2 */
(4, 1, 2, FALSE), (5, 1, 2, TRUE),   /* Gana 5 */
(6, 1, 3, TRUE),  (7, 1, 3, FALSE),  /* Gana 6 */
(8, 1, 4, FALSE), (9, 1, 4, TRUE),   /* Gana 9 */

/* --- SEMIFINALES --- */
(2, 1, 5, TRUE),  (5, 1, 5, FALSE),  /* Gana 2 */
(6, 1, 6, FALSE), (9, 1, 6, TRUE),   /* Gana 9 */

/* --- FINAL --- */
(2, 1, 7, FALSE), (9, 1, 7, TRUE),   /* Campeón: 9 (Francisco) */


/* =====================================================
   RESULTADOS TORNEO 2: Tetris
   ===================================================== */
/* --- CUARTOS DE FINAL --- */
(11, 2, 8, FALSE), (12, 2, 8, TRUE),   /* Gana 12 */
(13, 2, 9, TRUE),  (14, 2, 9, FALSE),  /* Gana 13 */
(15, 2, 10, FALSE),(16, 2, 10, TRUE),  /* Gana 16 */
(17, 2, 11, TRUE), (18, 2, 11, FALSE), /* Gana 17 */

/* --- SEMIFINALES --- */
(12, 2, 12, TRUE), (13, 2, 12, FALSE), /* Gana 12 */
(16, 2, 13, FALSE),(17, 2, 13, TRUE),  /* Gana 17 */

/* --- FINAL --- */
(12, 2, 14, TRUE), (17, 2, 14, FALSE), /* Campeón: 12 (Isabel) */


/* =====================================================
   RESULTADOS TORNEO 3: Super Smash Bros
   ===================================================== */
/* --- CUARTOS DE FINAL (Solo avanza 1 de cada grupo de 4) --- */
(2, 3, 15, FALSE), (4, 3, 15, TRUE),  (6, 3, 15, FALSE), (8, 3, 15, FALSE), /* Gana 4 */
(12, 3, 16, FALSE),(14, 3, 16, FALSE),(16, 3, 16, TRUE), (18, 3, 16, FALSE),/* Gana 16 */
(21, 3, 17, TRUE), (22, 3, 17, FALSE),(23, 3, 17, FALSE),(24, 3, 17, FALSE),/* Gana 21 */
(25, 3, 18, FALSE),(27, 3, 18, FALSE),(28, 3, 18, FALSE),(29, 3, 18, TRUE), /* Gana 29 */

/* --- FINAL --- */
(4, 3, 19, FALSE), (16, 3, 19, FALSE),(21, 3, 19, TRUE), (29, 3, 19, FALSE);/* Campeón: 21 (Pablo Ruiz) */