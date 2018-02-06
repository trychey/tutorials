package com.baeldung.hibernate.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.baeldung.hibernate.pojo.PersonName;

@Converter
public class PersonNameConverter implements AttributeConverter<PersonName, String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(PersonName person) {
        StringBuilder sb = new StringBuilder();
        if (person.getSurname() != null) {
            sb.append(person.getSurname());
            sb.append(SEPARATOR);
        }

        if (person.getName() != null) {
            sb.append(person.getName());
        }

        return sb.toString();
    }

    @Override
    public PersonName convertToEntityAttribute(String dbPerson) {
        String[] pieces = dbPerson.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        PersonName personName = new PersonName();
        if (dbPerson.contains(SEPARATOR)) {
            personName.setSurname(pieces[0]);

            if (pieces[1] != null) {
                personName.setName(pieces[1]);
            }
        } else {
            personName.setName(pieces[0]);
        }

        return personName;
    }

}
