create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

select * from devices;
select * from people;
select * from devices_people;

insert into devices(name, price) values ('notebook', 10000), ('smartphone', 8000), 
('printer', 4500), ('TV', 12000);

insert into people(name) values ('Ivan'), ('Misha'), ('Egor'), ('Roman');

insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (3, 1), (4, 2),
(3, 2), (1, 3), (4, 3), (3, 3), (1, 4), (3, 4), (4, 4);



select avg(price) from devices;


select p.name, round(avg(price)) 
from devices d
join devices_people dp on d.id = device_id
join people p on p.id = people_id
group by p.name;


select p.name, round(avg(price)) 
from devices d
join devices_people dp on d.id = device_id
join people p on p.id = people_id
group by p.name
having avg(price) > 8000;





