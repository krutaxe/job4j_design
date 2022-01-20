create table departments(
    id serial primary key,
    name varchar(255)
);

create table emploers(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);

insert into departments (name) values ('IT'), ('Finance'), ('Sales'),
('Admin'), ('Marketing'), ('Security');

insert into emploers (name, departments_id) values 
('MTS', 1), ('MTS', 2), ('MTS', 4), ('Lukoil', 2), ('Lukoil', 3),
('Lukoil', 4), ('Sber', 1), ('Sber', 2);

insert into emploers (name) values ('Yandex');

select * from departments;
select * from emploers;

select * from departments d left join emploers e on d.id = e.departments_id; 

select * from departments d right join emploers e on d.id = e.departments_id; 

select * from departments d full join emploers e on d.id = e.departments_id; 

select * from departments cross join emploers; 

select * from departments d left join emploers e 
on d.id = e.departments_id
where e.name is null; 

select e.departments_id, e.name, d.name from departments d left join emploers e 
on d.id = e.departments_id;
select e.departments_id, e.name, d.name from emploers e right join departments d 
on d.id = e.departments_id; 

create table teens(
    id serial primary key,
    name varchar(255),
	gender varchar(255)
);

insert into teens (name, gender) values ('Roma', 'Male'), ('Anna', 'Female'),
('Tom', 'Male'), ('Elena', 'Female');

select t1.name, t2.name, t1.gender, t2.gender 
from teens t1 cross join teens t2
where t1.gender = 'Male' and t2.gender = 'Female'; 


















