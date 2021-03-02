create database hiber;

create table roles(
	id serial primary key,
	role VARCHAR(45) not null
);

insert into roles (role) values ('admin');
insert into roles (role) values ('client');

create table users (
	id serial primary key,
	login varchar(45) unique not null,
	password varchar(45) not null,
	full_name varchar(60),
	age integer,
	role integer not null,
	foreign key (role) references roles (id)
);

create table client_orders (
	id serial primary key,
	user_id integer not null,
	foreign key (user_id) references users (id),
	cost integer not null
);

create table booking (
	id serial primary key,
	product_name varchar(45) not null,
	cost integer
);

create table users_booking (
	user_id integer not null,
	foreign key (user_id) references users (id),
	booking_id integer not null,
	foreign key (booking_id) references booking (id)
);