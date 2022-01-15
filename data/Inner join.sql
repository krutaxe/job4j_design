create table country(
    id serial primary key,
    name varchar(255)
);

create table city(
    id serial primary key,
    name varchar(255),
	city_population int,
    country_id int references country(id)
);

insert into country(name) values ('USA');
insert into country(name) values ('Russia');
insert into country(name) values ('Canada');
insert into country(name) values ('Germany');

insert into city(name, city_population, country_id) values('Moscow', 10000, 2);
insert into city(name, city_population, country_id) values('Kazan', 8000, 2);
insert into city(name, city_population, country_id) values('New York', 20000, 1);
insert into city(name, city_population, country_id) values('Boston', 5000, 1);
insert into city(name, city_population, country_id) values('Vancouver', 3000, 3);
insert into city(name, city_population, country_id) values('Edmonton', 7500, 3);
insert into city(name, city_population, country_id) values('Dresden', 1000, 4);
insert into city(name, city_population, country_id) values('Berlin', 15000, 4);
insert into city(name, city_population) values('Paris', 12000);
insert into city(name, city_population) values('Rome', 11500);

select * from city;
select * from country;

select * 
from city 
join country p 
on city.country_id = p.id;

select pp.name, p.name, city_population
from city as pp
join country as p
on pp.country_id = p.id;

select pp.name as Город, p.name as Страна, country_id as Код_страны,
city_population as Население_города
from city as pp
join country as p
on pp.country_id = p.id;

select name as Город, city_population as Население_города
from city;

select * from city join country p on city.country_id = p.id;

select * from city join country p
on city.country_id = p.id and 
city_population < 10000;