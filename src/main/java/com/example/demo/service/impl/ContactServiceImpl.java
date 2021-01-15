package com.example.demo.service.impl;

import com.example.demo.entity.Contact;
import com.example.demo.entity.PhoneNumber;
import com.example.demo.exception.ContactNotFoundException;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllContact() {
        return contactRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("contact not found with id " + id));
    }

    @Override
    @Transactional
    public Contact saveContact(Contact contact) {
        contact.getPhoneNumbers()
                .forEach(phoneNumber -> phoneNumber.setContact(contact));
        return contactRepository.save(contact);
    }

    @Override
    @Transactional
    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findContactByFirstName(String firstName) {
        return contactRepository.findContactByFirstName(firstName);
    }

    @Override
    @Transactional
    public Contact updateContactById(Long id, Contact contact) {
        contact.setId(id);
        contact.getPhoneNumbers().forEach(phoneNumber -> phoneNumber.setContact(contact));
        return contactRepository.save(contact);
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findContactByNameAndLastName(String firstName, String lastName) {
        return contactRepository.findContactByNameAndLastName(firstName, lastName);
    }

    @Override
    @Transactional
    public void deleteContactByFirstName(String firstName) {
        contactRepository.deleteContactByFirstName(firstName);
    }

    @PostConstruct
    public void init() {
        PhoneNumber phoneNumber1 = PhoneNumber.builder().phoneNumber("+380637681278").build();
        PhoneNumber phoneNumber2 = PhoneNumber.builder().phoneNumber("+7777777").build();

        Contact contact = Contact.builder().firstName("Dima").lastName("Tischenko").build();

        phoneNumber1.setContact(contact);
        phoneNumber2.setContact(contact);

        contact.setPhoneNumbers(Arrays.asList(phoneNumber1, phoneNumber2));


        contactRepository.save(contact);
    }
}
