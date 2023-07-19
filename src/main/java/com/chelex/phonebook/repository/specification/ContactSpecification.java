package com.chelex.phonebook.repository.specification;

import com.chelex.phonebook.domain.entity.Contact;
import com.chelex.phonebook.domain.entity.Person;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class ContactSpecification {

  /**
   * the same as query:
   * /  select *, p.uuid from person p
   * /  join contact on p.id = contact.person_id
   * /  where  p.uuid = '97baf8ee-20b9-11ee-b3c9-0242ac170002';
   */
  public Specification<Contact> personUuid(String uuid) {
    if (!StringUtils.hasText(uuid)) {
      return null;
    }

    return (root, query, cb) -> cb.equal(root.join(Contact.Fields.person).get(Person.Fields.uuid), uuid);
  }

  public Specification<Contact> nameLike(String searchFirstName) {
    if (org.apache.commons.lang3.StringUtils.isBlank(searchFirstName)) {
      return null;
    }
    String pattern = '%' + searchFirstName + '%';
    return (root, query, cb) -> cb.like(root.get(Contact.Fields.firstName), pattern);
  }
}
