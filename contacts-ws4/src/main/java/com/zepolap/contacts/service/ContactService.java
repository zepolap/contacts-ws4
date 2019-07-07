package com.zepolap.contacts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.transaction.annotation.Transactional;

import com.zepolap.contacts.dao.ContactRepository;
import com.zepolap.contacts.dto.Contact;

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
    
    public Contact findByFirstnameAndLastname(String firstname, String lastname) {

        Contact contact = dao.findByFirstnameAndLastname(firstname, lastname);

        return contact;
    }
    
    public List<Contact> findAll() {

        List<Contact> contactList = (List<Contact>) dao.findAll();

        return contactList;
    }

    @Transactional
	public void deleteByFirstnameAndLastname(String firstname, String lastname) {
    	
    	dao.deleteByFirstnameAndLastname(firstname, lastname);
    	
    }
    
}
