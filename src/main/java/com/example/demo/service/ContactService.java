package com.example.demo.service;

import com.example.demo.entity.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findAllContact();

    Contact findContactById(Long id);

    Contact saveContact(Contact contact);

    void deleteContactById(Long id);

    List<Contact> findContactByFirstName(String firstName);

    Contact updateContactById(Long id, Contact contact);

    Contact findContactByNameAndLastName(String firstName, String lastName);

    void deleteContactByFirstName(String firstName);
}
