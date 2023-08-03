-- Add new address table + remove city column from contact table
SET @current = NOW();

create TABLE `phonebook`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `house_number` INT NULL,
  `postal_code` INT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`));

ALTER TABLE contact DROP COLUMN city;

ALTER TABLE contact ADD COLUMN address_id INT after person_id;
ALTER TABLE contact ADD CONSTRAINT fk_contact_address
FOREIGN KEY (address_id) REFERENCES address(id);

insert into address (city, street, house_number, postal_code, created_at, updated_at)
values ('Kyiv',    'Some street', 13, 00000, @current, @current),
       ('Lviv',    'Some street', 66, 00000, @current, @current),
       ('Kharkiv', 'Some street', 24, 00000, @current, @current);

-- Drop constraint key to have possibility to drop address table
-- ALTER TABLE contact DROP CONSTRAINT fk_contact_address;
-- DROP TABLE address;