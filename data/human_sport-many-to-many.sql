create table human(
    id serial primary key,
    name varchar(255)
);

create table sport(
    id serial primary key,
	type_sport varchar(255)
);

create table human_sport(
    id serial primary key,
	human_id int references human(id),
	sport_id int references sport(id)
);


insert into human(name) values ('Misha');
insert into human(name) values ('Sasha');
insert into human(name) values ('Masha');

insert into sport(type_sport) values ('Ski');
insert into sport(type_sport) values ('Hockey');
insert into sport(type_sport) values ('Football');

insert into human_sport(human_id, sport_id) values (1, 1);
insert into human_sport(human_id, sport_id) values (1, 2);
insert into human_sport(human_id, sport_id) values (1, 3);
insert into human_sport(human_id, sport_id) values (2, 1);
insert into human_sport(human_id, sport_id) values (3, 2);
insert into human_sport(human_id, sport_id) values (3, 3);

select * from human;
select * from sport;
select * from human_sport;
