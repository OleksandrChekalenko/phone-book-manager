UPDATE person
SET uuid = UUID()
WHERE uuid IS NULL;

UPDATE contact
SET uuid = UUID()
WHERE uuid IS NULL;
