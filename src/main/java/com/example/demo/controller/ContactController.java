package com.example.demo.controller;

import com.example.demo.entity.Contact;
import com.example.demo.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.util.Mapping.CONTACT_BASE_URL;

@RestController
@RequestMapping(CONTACT_BASE_URL)
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public List<Contact> findAllContacts() {
        return contactService.findAllContact();
    }

    @GetMapping("/{contactId}")
    public Contact findContactById(@PathVariable("contactId") Long id) {
        return contactService.findContactById(id);
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }

    @DeleteMapping("/{contactId}")
    public void deleteContactById(@PathVariable("contactId") Long id) {
        contactService.deleteContactById(id);
    }

    @GetMapping("/search")
    public List<Contact> findContactByFirstName(@RequestParam("contactName") String firstName) {
        return contactService.findContactByFirstName(firstName);
    }

    @PutMapping("/{id}")
    public Contact updateContactById(@PathVariable("id") Long id, @RequestBody Contact contact) {
        return contactService.updateContactById(id, contact);
    }

    @GetMapping("/specific")
    public Contact findSpecificContact(@RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName) {
        return contactService.findContactByNameAndLastName(firstName, lastName);
    }

    @DeleteMapping
    public void deleteContactByFirstName(@RequestParam("firstName") String firstName) {
        contactService.deleteContactByFirstName(firstName);
    }


}
