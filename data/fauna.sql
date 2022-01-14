create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('dinosaur', 30000, '300-01-01');
insert into fauna(name, avg_age, discovery_date) values ('lizard', 13700, '1930-01-01');
insert into fauna(name, avg_age, discovery_date) values ('bird', 15000, '1955-01-01');
insert into fauna(name, avg_age, discovery_date) values ('tigr', 4000, '2000-01-01');
insert into fauna(name, avg_age, discovery_date) values ('giraffe', 19900, '1000-01-01');
insert into fauna(name, avg_age, discovery_date) values ('crocodile', 10400, null);
insert into fauna(name, avg_age, discovery_date) values ('cow', 8000, '800-01-01');
insert into fauna(name, avg_age, discovery_date) values ('horse', 12500, '1500-01-01');
insert into fauna(name, avg_age, discovery_date) values ('spider', 50000, null);
insert into fauna(name, avg_age, discovery_date) values ('fish', 3500, '2010-01-01');

select * from fauna;
select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';