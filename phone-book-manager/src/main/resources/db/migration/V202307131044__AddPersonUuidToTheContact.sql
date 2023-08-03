ALTER TABLE contact
    ADD COLUMN person_uuid varchar(36) NOT NULL after uuid;

update contact c
set c.person_uuid = (select uuid from person p where c.person_id = p.id)
where c.person_uuid = '';
