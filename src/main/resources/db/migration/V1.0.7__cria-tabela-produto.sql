create table produto (
    id               serial primary key,
    nome             varchar(100) not null,
    descricao        varchar(255) not null,
    preco            numeric(10, 2) not null,
    ativo            boolean default true not null,
    restaurante_id   int references restaurante not null
);