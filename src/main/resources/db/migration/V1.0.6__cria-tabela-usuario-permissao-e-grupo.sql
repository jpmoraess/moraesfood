create table usuario (
    id             serial primary key,
    nome           varchar(100) not null,
    email          varchar(100) not null,
    senha          varchar(255) not null,
    data_cadastro  timestamp not null
);

create table permissao (
    id            serial primary key,
    nome          varchar(255) not null,
    descricao     varchar(255)
);

create table grupo (
    id           serial primary key,
    nome         varchar(100) not null
);

create table usuario_grupo (
    usuario_id     int references usuario not null,
    grupo_id       int references grupo not null
);

create table grupo_permissao (
    grupo_id       int references grupo not null,
    permissao_id   int references permissao not null
);