-- para rodar este arquivo ao executar a api,
-- renomeie-o para 'data.sql'

create table clients(
    id integer primary key auto_increment,
    name varchar(100) not null,
    cpf varchar(15) not null
);

create table products(
    id integer primary key auto_increment,
    description varchar(100) not null,
    price numeric(20, 2)
);

create table carts(
    id integer primary key auto_increment,
    client_id integer references clients(id),
    created_at timestamp,
    total numeric(20, 2),
    status varchar(20)
);

create table items(
    id integer primary key auto_increment,
    product_id integer references products(id),
    cart_id integer references carts(id),
    qtd integer
);

create table users(
    id integer primary key auto_increment,
    login varchar(50) unique not null,
    password varchar(100) not null,
    role varchar(10) not null
);