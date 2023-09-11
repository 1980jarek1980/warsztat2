insert into roles(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_WORKER'),
       ('ROLE_USER')
;

-- admin:admin
-- *:ubuntu
INSERT INTO users (dtype, active, adres, email, password, telefon, nazwa, nip, regon, imie, nazwisko, pesel,
                   dataurodzenia, stanowiskopracy)
VALUES ('User', true, null, 'admin@ad.min', '$2a$10$Xv/kU4yXMMPj8LXsothM4eqhcWGJ2RULRbVTkR8VIWF.ZcqeCQdUy', null, null,
        null, null, null, null, null, null, null),
       ('PhysicalPerson', true, 'chrobrego', 'person@per.son',
        '$2a$10$SfSBnupSBZNvU7VqZPa0m./1RLKmUcs0O/eogI1Pnd6pr1v6s2/oO', '1231421', null, '', null, 'wqe', 'asd',
        '56457981321', null, null),
       ('LegalPerson', true, 'qweasdz', 'org@org.pl', '$2a$10$SfSBnupSBZNvU7VqZPa0m./1RLKmUcs0O/eogI1Pnd6pr1v6s2/oO',
        '098776876675', 'nazwa', '098798', 'regon', null, null, null, null, null),
       ('Worker', true, 'asdasdad', 'are.qwe@qwe.qwe', '$2a$10$YHzKZdMCjy8Z.IU2.ArhUu98/bDUSA.U6AzhWCiuUhPUg3B9JLFVa',
        '76876686978', null, null, null, 'Bolek', 'Myk', '123456789', '2023-08-02', '1');


insert into users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 3),
       (3, 3),
       (4, 2);

insert into product (cena, nazwa, opis, rodzaj)
VALUES (100.50, 'opona1', 'lorem ipsum dolor sit amet', 'gumowa'),
       (101.50, 'opona2', 'lorem ipsum dolor sit amet', 'gumowa'),
       (102.50, 'opona3', 'lorem ipsum dolor sit amet', 'gumowa'),
       (103.50, 'opona4', 'lorem ipsum dolor sit amet', 'gumowa');



