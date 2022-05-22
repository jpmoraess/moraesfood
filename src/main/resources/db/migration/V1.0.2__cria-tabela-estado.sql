create table estado (
	id          serial primary key,
	nome        varchar(30) not null
);

insert into estado (nome) select distinct nome_estado from cidade;

alter table cidade add column estado_id int not null default 0;

update cidade set estado_id = (select e.id from estado e where e.nome = cidade.nome_estado);

alter table cidade add constraint fk_cidade_estado foreign key (estado_id) references estado;

alter table cidade drop column nome_estado;

alter table cidade rename nome_cidade to nome;