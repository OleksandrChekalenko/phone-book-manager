SET @current = NOW();

CREATE TABLE person
(
    id         bigint UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(256)    NOT NULL,
    last_name  varchar(256)    NOT NULL,
    created_at datetime        NOT NULL,
    updated_at datetime
);


SET @current = NOW();

INSERT INTO person (first_name, last_name, created_at, updated_at)
VALUES ('John', 'Kirbi', @current, @current),
       ('Initial', 'Kirbi', @current, @current),
       ('Test', 'Kirbi', @current, @current),
       ('Amanda', 'Kirbi', @current, @current),
       ('Jimmy', 'Kirbi', @current, @current),
       ('Kal', 'Kirbi', @current, @current);
