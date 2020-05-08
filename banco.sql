create table tipos_usuarios(
	id serial not null primary key,
	descricao varchar(50) unique
);

create table usuarios(
	id serial not null primary key,
	nome varchar(100) not null,
	email varchar(100) not null unique,
	senha char(32) not null,
	tipo integer not null,
	foreign key (tipo) references tipos_usuarios(id)
);

insert into tipos_usuarios(descricao) values('Juiz'),('Advogado'),('Parte');

insert into usuarios(nome,email,senha,tipo) values
('Hiago Petris','hiago@gmail.com','584da0a485f209242059e6de66aac904', 1),
('Maria Joaquina', 'maria@gmail.com', '584da0a485f209242059e6de66aac904', 3),
('Leonardo Barbosa', 'leonardo@gmail.com', '584da0a485f209242059e6de66aac904', 2),
('Fernando Hoflinger', 'fernando@gmail.com', '584da0a485f209242059e6de66aac904', 2),
('Joana Cardoso', 'joana@gmail.com', '584da0a485f209242059e6de66aac904', 3),
('Sergio Moro', 'sergio@gmail.com', '584da0a485f209242059e6de66aac904', 2),
('Luiz Inacio', 'luiz@gmail.com', '584da0a485f209242059e6de66aac904', 3),
('Marcelo Odebretch', 'marcelo@gmail.com', '584da0a485f209242059e6de66aac904', 3)