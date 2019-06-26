package com.sinbugs.contacts.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sinbugs.contacts.dto.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	/*
     * Get contact list by first name. Please note the format should be
     * findBy<column_name>.
     */
    List<Contact> findByFirstname(String firstname);
    
}