use `foro_me`;

CREATE TABLE usuario(

    id BIGINT not null auto_increment,
    email VARCHAR(150) not null unique ,
    telefono VARCHAR(100) not null,
    nombre varchar(300) not null,
    contraseña varchar(100) not null,

    primary key (id)
)


