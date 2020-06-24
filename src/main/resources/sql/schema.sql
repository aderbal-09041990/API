create table sequences (
    sequence_name varchar(255) not null,
    sequence_next_hi_value int8,
    primary key (sequence_name)
);

insert into sequences(
    sequence_name,
    sequence_next_hi_value)
values ('id_usuario',0);

create table usuario (
    id int8 not null,
    ativo boolean,
    cpf varchar(255),
    email varchar(100),
    nome varchar(200),
    senha varchar(255),
    primary key (id)
);