create table pedido (
    id                       serial primary key,
    subtotal                 numeric(10, 2) not null,
    tx_frete                 numeric(10, 2) not null,
    valor_total              numeric(10, 2) not null,
    restaurante_id           int references restaurante not null,
    usuario_cliente_id       int references usuario not null,
    forma_pagamento_id       int references forma_pagamento not null,
    endereco_cidade_id       int references cidade not null,
    endereco_cep             varchar(9) not null,
    endereco_logradouro      varchar(100) not null,
    endereco_numero          varchar(20) not null,
    endereco_complemento     varchar(60) null,
    endereco_bairro          varchar(60) not null,
    status                   varchar(10) not null,
    data_criacao             timestamp not null,
    data_confirmacao         timestamp null,
    data_cancelamento        timestamp null,
    data_entrega             timestamp null
);

create table item_pedido (
    id                 serial primary key,
    quantidade         int not null,
    preco_unitario     numeric(10, 2) not null,
    preco_total        numeric(10, 2) not null,
    observacao         varchar(255) null,
    pedido_id          int references pedido not null,
    produto_id         int references produto not null
);