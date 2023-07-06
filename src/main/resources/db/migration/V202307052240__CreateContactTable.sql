SET @current = NOW();

CREATE TABLE contact
(
    id           bigint UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name   varchar(255)    NOT NULL,
    last_name    varchar(255),
    email        varchar(255),
    phone_number varchar(13)     NOT NULL,
    city         varchar(255),
    created_at   datetime        NOT NULL,
    updated_at   datetime,
    person_id    bigint UNSIGNED NOT NULL,
    foreign key (person_id) references person (id)
);

INSERT INTO contact (first_name, last_name, email, phone_number, city, created_at, updated_at, person_id)
VALUES ('John',     'Kirbi', null, '+380734123366', 'Kyiv', @current, @current, 1),
       ('Initial',  'Kirbi', null, '+380734121465', 'Kyiv', @current, @current, 2),
       ('Test',     'Kirbi', null, '+380456923366', 'Kyiv', @current, @current, 3),
       ('Amanda',   'Kirbi', null, '+380734125466', 'Kyiv', @current, @current, 3),
       ('Jimmy',    'Kirbi', null, '+386978523366', 'Kyiv', @current, @current, 3),
       ('Kal',      'Kirbi', null, '+380733698745', 'Kyiv', @current, @current, 1);
