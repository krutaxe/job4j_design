create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price int
);

insert into type (name) values ('Молоко'), ('Сыр'), ('Хлеб'), ('Мясо'), ('Овощи'); 

insert into product (name, type_id, expired_date, price) values 
('Молоко', 1, '19-01-2022', 35),
('Кефир', 1, '20-01-2022', 25),
('Катык', 1, '21-01-2022', 30),
('Йогурт', 1, '18-01-2022', 50),
('Ряженка', 1, '20-01-2022', 40),
('Плавленый', 2, '21-01-2022', 70),
('Хохланд', 2, '18-01-2022', 200),
('Голандский', 2, '24-01-2022', 350),
('Копченый', 2, '13-01-2022', 100),
('Белый', 3, '18-01-2022', 55),
('Черный', 3, '27-01-2022', 45),
('Батон', 3, '21-01-2022', 40),
('Булка', 3, '15-01-2022', 35),
('Говядина мороженое', 4, '29-01-2022', 500),
('Свинина охлажденая', 4, '10-01-2022', 300),
('Баранина', 4, '19-01-2022', 700),
('Курица', 4, '13-01-2022', 250),
('Рыба', 4, '22-01-2022', 900),
('Колбаса', 4, '18-01-2022', 550),
('Лук', 5, '20-01-2022', 40),
('Морковь', 5, '21-01-2022', 60),
('Огурцы', 5, '18-01-2022', 170),
('Помидор', 5, '20-01-2022', 200),
('Капуста', 5, '21-01-2022', 80),
('Картошка', 5, '18-01-2022', 65),
('Чеснок', 5, '20-01-2022', 25),
('Смесь овощей мороженое', 5, '21-01-2022', 130);

select * from type;
select * from product;

select * from product p
join type t on t.id = type_id
where t.name = 'Сыр';

select * from product p
join type t on t.id = type_id
where p.name like '%мороженое%';

select * from product p
join type t on t.id = type_id
where expired_date < current_date;

select name, price from product
where price = (select max(price) from product);


select t.name, count(*) from product p
join type t on t.id = type_id
group by t.name;

select * from product p
join type t on t.id = type_id
where t.name = 'Сыр' or t.name = 'Молоко';

select t.name, count(*) from product p
join type t on t.id = type_id
group by t.name
having count(*) < 6;

select t.name, p.name from product p
join type t on t.id = type_id;














