create table ordem_servico (
id serial primary key,
cliente_id bigint not null,
descricao text not null,
preco decimal(10,2) not null,
data_abertura TIMESTAMP not null,
data_finalizacao TIMESTAMP
);

ALTER TABLE ordem_servico
    ADD CONSTRAINT fk_ordem_servico_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);
