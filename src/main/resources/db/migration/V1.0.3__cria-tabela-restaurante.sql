create table restaurante (
    id                        serial primary key,
    tx_frete                  numeric(5, 2) not null,
    cozinha_id                int references cozinha,
    endereco_cep              varchar(10),
    endereco_logradouro       varchar(70),
    endereco_numero           varchar(10),
    endereco_complemento      varchar(70),
    endereco_bairro           varchar(40),
    endereco_cidade_id        int references cidade,
    data_cadastro             timestamp not null,
    data_atualizacao          timestamp not null
);