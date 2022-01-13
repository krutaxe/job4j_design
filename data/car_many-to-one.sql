create table engine(
    id serial primary key,
    power int,
	volume int
);

create table car(
    id serial primary key,
    brand varchar(255),
	model varchar(255),
    engine_id int references engine(id)
);

insert into engine(power, volume) values (200, 3);
insert into car(brand, model, engine_id) VALUES ('BMW', '3', 1);

select * from car;

select * from car where id in (select id from car);