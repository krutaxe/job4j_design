create table qr_code(
    id serial primary key,
    code int
);

create table employee(
    id serial primary key,
    name varchar(255),
    qr_code_id int references qr_code(id) unique
);

insert into qr_code(code) values (234582355);
insert into employee(name, qr_code_id ) VALUES ('Roman', 1);

select * from employee;
select * from qr_code;
