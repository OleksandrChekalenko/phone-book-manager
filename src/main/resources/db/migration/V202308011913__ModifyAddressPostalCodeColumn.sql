-- Change postal_code column datatype from some to some

ALTER TABLE address MODIFY COLUMN postal_code VARCHAR(10);