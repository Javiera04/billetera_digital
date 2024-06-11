drop database billetera;
create schema billetera;
use billetera;

create table usuario(
	id_usuario int auto_increment primary key,
    username varchar(100) not null,
    password varchar(100) not null,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    email varchar(100) not null

);

create table contacto(
    id_usuario int not null,
    id_contacto int not null,
    
    primary key(id_usuario, id_contacto),
    foreign key(id_usuario) references usuario(id_usuario) on delete cascade,
    foreign key(id_contacto) references usuario(id_usuario) on delete cascade
);


create table cuenta(
	id_cuenta int auto_increment primary key,
    nro_cuenta int not null,
    saldo int not null,
    id_usuario int not null,
    
	foreign key(id_usuario) references usuario(id_usuario) on delete cascade
    
);

create table tipo_transaccion(
	id_tipo int auto_increment primary key,
	nombre_tipo varchar(30)	
);

create table transaccion(
	id_transaccion int auto_increment primary key,
    id_cuenta_origen int null,
    id_cuenta_destino int null, 
    fecha_transaccion datetime not null,
    monto int not null,
    id_tipo int not null,
    
    foreign key(id_cuenta_origen) references cuenta(id_cuenta) on delete cascade,
    foreign key(id_cuenta_destino) references cuenta(id_cuenta) on delete cascade,
    foreign key(id_tipo) references tipo_transaccion(id_tipo) on delete cascade
);
   
insert into tipo_transaccion(nombre_tipo)
values('Depósito'),('Retiro'),('Transferencia');

