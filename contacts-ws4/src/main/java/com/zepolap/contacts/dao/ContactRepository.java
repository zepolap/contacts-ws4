package com.zepolap.contacts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.zepolap.contacts.dto.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	/*
     * Get contact list by first name. Please note the format should be
     * findBy<column_name>.
     */
    List<Contact> findByFirstname(String firstname);
    
    Contact findByFirstnameAndLastname(String firstname, String lastname);
    
    @Transactional
    void deleteByFirstnameAndLastname(String firstname, String lastname);
    
}
