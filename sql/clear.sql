delete from users_roles where true;
delete from order_worker where true;

delete from order_entity where true;
delete from product where true;
delete from roles where true;
delete from car where true;
delete from service where true;
delete from users where true;

alter table product AUTO_INCREMENT = 1;
alter table roles AUTO_INCREMENT = 1;
alter table users AUTO_INCREMENT = 1;
alter table car AUTO_INCREMENT = 1;
alter table service AUTO_INCREMENT = 1;
alter table order_entity AUTO_INCREMENT = 1;
alter table users_roles AUTO_INCREMENT = 1;
alter table order_worker AUTO_INCREMENT = 1;





