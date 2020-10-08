create database BDJSFPRODUTO;
use BDJSFPRODUTO;

create table produto(
	id     int           primary key    auto_increment,
	nome   varchar(50)   unique,
	autor  varchar(50),
	preco  double
);

show tables;

insert into produto (id, nome, autor, preco) values
	(null, 'Java Certificacao', 'Kathy Sierra', 200),
	(null, 'Java OO', 'Douglas Rocha', 100),
	(null, 'Java EE', 'Cleiton Sampaio', 80);
	
select * from produto;