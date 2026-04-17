-- Creación de BBDD

CREATE DATABASE retropvp;

-- Creación de todas las tablas 

CREATE TABLE IF NOT EXISTS tipo_usuario(
    id_tipo_usuario INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL UNIQUE 
);

CREATE TABLE IF NOT EXISTS usuario(
    id_usuario int auto_increment primary key,
    id_tipo_usuario int not null,
    nombre varchar(20) not NULL ,
    apellido varchar(40) not null,
    email varchar(40) not null unique,
    direccion varchar(40) not null,
    telefono varchar(20) not null,
    
    CONSTRAINT fk_usuario_tipo
    foreign key (id_tipo_usuario)
    references tipo_usuario(id_tipo_usuario)
    on delete restrict
    on update cascade
);

create table if not exists pedido(
	id_pedido int auto_increment primary key,
	id_usuario int not null,
	fecha date not null,
	importe DECIMAL(10, 2) not null,
	
	CONSTRAINT fk_usuario
	foreign key (id_usuario)
		references usuario(id_usuario)
		on delete restrict
		on update cascade
);

create table if not exists articulo(
	id_articulo int auto_increment primary key,
	nombre varchar(20) not null,
	precio decimal(10, 2) not null,
	description varchar(100) not null,
	URL_foto varchar(150) not null
);

create table if not exists stock(
id_stock int auto_increment primary key,
id_articulo int not null,
cantidad int not null,

constraint fk_articulo
	foreign key (id_articulo)
	references articulo(id_articulo)
	on delete restrict
	on update cascade

);

create table if not exists artiulo_pedido(
	id_articulo int not null,
	id_pedido int not null,
	unidades int not null,
	
	primary key (id_articulo, id_pedido),
	
	constraint fk_ap_articulo
	foreign key (id_articulo)
	references articulo(id_articulo)
	on delete cascade,
	
	constraint fk_ap_pedido
	foreign key (id_pedido)
	references pedido(id_pedido)
	on delete CASCADE 
);

create table if not exists consola(
	id_articulo int primary key,
	anio_lanzamiento year not null,
	
	CONSTRAINT fk_consola_articulo
		foreign key (id_articulo)
		references articulo(id_articulo)
		on delete cascade
);

create table if not exists juego(
	id_articulo int primary key,
	id_consola int not null,
	anio_lanzamiento year not null,
	jugadores_pvp int not null,
	
	CONSTRAINT fk_juego_articulo
		foreign key (id_articulo)
		references articulo(id_articulo)
		on delete cascade,
		
	constraint fk_juego_consola
		foreign key (id_consola)
		references consola(id_articulo)
		on delete restrict
		on update cascade
);

create table if not exists accesorio(
	id_articulo int primary key,
	id_consola int not null,
	
	constraint fk_accesorio_articulo
		foreign key (id_articulo)
		references articulo(id_articulo)
		on delete cascade,
		
	constraint fk_accesorio_consola
		foreign key (id_consola)
		references consola(id_articulo)
		on delete restrict
		on update cascade
);

create table if not exists sala(
	id_sala int auto_increment primary key,
	nombre varchar(20) not null,
	tamanio int not null
);

create table if not exists torneo(
	id_torneo int auto_increment primary key,
	id_usuario int not null,
	id_sala int not null,
	id_juego int not null,
	fecha date not null,
	
	constraint fk_torneo_usuario
		foreign key (id_usuario)
		references usuario(id_usuario)
		on delete restrict
		on update cascade,
		
	constraint fk_torneo_sala
		foreign key (id_sala)
		references sala(id_sala)
		on delete restrict
		on update cascade,
		
	constraint fk_torneo_juego
		foreign key(id_juego)
		references juego(id_articulo)
		on delete restrict
		on update cascade
);

create table if not exists participacion(
	id_usuario int not null,
	id_torneo int not null,
	
	primary key (id_usuario, id_torneo),
	
	constraint fk_participacion_usuario
		foreign key (id_usuario)
		references usuario(id_usuario)
		on delete cascade,
		
	constraint fk_participacion_torneo
		foreign key (id_torneo)
		references torneo(id_torneo)
		on delete cascade
	);

create table if not exists enfrentamiento(
	id_enfrentamiento int auto_increment primary key,
	nombre varchar(20) not null,
	top enum('final', 'semifinal', 'cuartos', 'octavos', 'dieciseisavos') not null,
	participantes int not null	
);

create table if not exists rival(
	id_usuario int not null,
	id_torneo int not null,
	id_enfrentamiento int not null,
	es_ganador boolean default FALSE,
	
	primary key (id_usuario, id_torneo, id_enfrentamiento),
	
	constraint fk_rival_participacion
		foreign key (id_usuario, id_torneo)
		references participacion(id_usuario, id_torneo)
		on delete cascade,
		
	constraint fk_rival_enfrentamiento
		foreign key (id_enfrentamiento)
		references enfrentamiento(id_enfrentamiento)
		on delete cascade
);