package com.sinbugs.contacts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestParam;

//import com.sinbugs.contacts.api.ContactRequest;
//import com.sinbugs.contacts.api.ContactResponse;
import com.sinbugs.contacts.dao.ContactRepository;
import com.sinbugs.contacts.dto.Contact;

@Service
public class ContactService {
    @Autowired
    ContactRepository dao;
     
    public Contact save(Contact contact){
        return dao.saveAndFlush(contact);
    }
    
    public List<Contact> findByFirstname(String firstname) {

        List<Contact> contactList = (List<Contact>) dao.findByFirstname(firstname);

        return contactList;
    }
    
    public List<Contact> findAll() {

        List<Contact> contactList = (List<Contact>) dao.findAll();

        return contactList;
    }
    
}
