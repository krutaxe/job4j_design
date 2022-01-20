create table car_body (
	id serial primary key,
	name varchar(20) 
);	

create table engine (
	id serial primary key,
	name varchar(20) 
);	

create table transmission (
	id serial primary key,
	name varchar(20) 
);	

create table car (
	id serial primary key,
	name varchar(20),
	car_body_id int references car_body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);	

insert into car_body (name) values 
('Седан'), ('Хэтчбек'), ('Минивен'), ('Внедорожник'), ('Грузовик'); 

insert into car_body (name) values 
('Автобус'); 

insert into engine (name) values
('Бензиновый'), ('Дизель'), ('ГБО'), ('Электро'), ('Гибрид');

insert into transmission (name) values
('Механика'), ('гидротрансформатор'), ('Вариатор'),
('Робот'), ('Полуавтомат');

insert into car (name, car_body_id, engine_id, transmission_id) values 
('Нива', 4, 1, 1), ('Лада', 2, 3, 1), ('Газель', 5, 1, 4), ('Гранта', 1, 3, 2), 
('Веста', 1, 4, 2),('Валдай', 5, 2, 4),('Соболь', 3, 3, 3),('Патриот', 4, 1, 1); 

select * from car;

select c.name, cb.name, e.name, t.name from car c join car_body cb
on car_body_id = cb.id join engine e
on engine_id = e.id join transmission t
on transmission_id = t.id;

select cb.name from car_body cb
full join car c on
cb.id = c.car_body_id
where c.id is null;

select e.name from engine e 
full join car c on
e.id = c.engine_id
where c.id is null;

select t.name from transmission t
full join car c on
t.id = c.transmission_id
where c.id is null;






	