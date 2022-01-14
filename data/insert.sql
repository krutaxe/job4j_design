insert into role(name) values('Doctor');
insert into role(name) values('Prigrammer');


insert into users(name, role_id) values('Petr', 1);
insert into users(name, role_id) values('Misha', 2);


insert into rules(name) values('Admin');
insert into rules(name) values('User');


insert into role_rules(role_id, rules_id) values(1, 1);
insert into role_rules(role_id, rules_id) values(1, 2);


insert into category(name) values('A');
insert into category(name) values('B');


insert into state(name) values('done');
insert into state(name) values('not done');


insert into item(name, users_id, users_id, category_id, state_id) values('NEW', 1, 1, 2);
insert into item(name, users_id, users_id, category_id, state_id) values('OLD', 2, 2, 1);


insert into comments(name, item_id) values('Attention', 1);
insert into comments(name, item_id) values('Urgently', 2);


insert into attachs(name, item_id) values('Document.pdf', 1);
insert into attachs(name, item_id) values('Java.exe', 2);






