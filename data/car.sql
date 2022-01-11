create table Car(
	id serial primary key,
	brand text,
	model text,
	year integer,
	engine char 
);
insert into car(brand, model, year, engine) values('Mazda', 'Atenza', 2020, 'D');
insert into car(brand, model, year, engine) values('BMW', 'Super', 2018, 'C');
insert into car(brand, model, year, engine) values('Ford', 'Focus', 2021, 'D');
update car set year = 2022;
update car set model = 'Tiguan';
select * from car;
delete from car;

