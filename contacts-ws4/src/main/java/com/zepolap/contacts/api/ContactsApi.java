package com.zepolap.contacts.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zepolap.contacts.dto.Contact;
import com.zepolap.contacts.service.ContactService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContactsApi {
	
	@Autowired
	ContactService contactService;
	
	// Inyecta mapper de Dozer
	@Autowired
	Mapper mapper;
	 
	@RequestMapping(value="/contact", method=RequestMethod.POST)
	public ContactResponse updateOrSave(@RequestBody @Valid ContactRequest contactRequest){
		// Mapeo request dto ==> entity
		Contact contact = mapper.map(contactRequest, Contact.class);
	    
		// Invoca logica de negocio
		Contact updatedContact = contactService.save(contact);
	    
		// Mapeo entity ==> response dto
	    ContactResponse contactResponse = mapper.map(updatedContact, ContactResponse.class); 
	    
	    return contactResponse;
	}
	
	@RequestMapping(value="/contact", method=RequestMethod.GET)
	public Contact getById(){
		return new Contact(1L, "John", "Doe", "+57 311 222 3344", "john@sinbugs.com");
	}
	
	@RequestMapping(value="/contact/findByFirstname", method=RequestMethod.GET)
    public List<ContactResponse> findByFirstname(@RequestBody @Valid ContactRequestFirstname contactRequestFirstname) {

        String firstname=contactRequestFirstname.getFirstname();
        
        List<Contact> contactList = (List<Contact>) contactService.findByFirstname(firstname);
        List<ContactResponse> contactResponseList = new ArrayList<ContactResponse>();
        
        if (contactList != null) {
            for (Contact contact : contactList) {
            	// Invoca logica de negocio
        		Contact updatedContact = contactService.save(contact);
        	    
        		// Mapeo entity ==> response dto
        	    ContactResponse contactResponse = mapper.map(updatedContact, ContactResponse.class);
        	    
        	    contactResponseList.add(contactResponse);
           
            }
        }

        return contactResponseList;
    }
	
	@RequestMapping(value="/contact/findByFirstnameAndLastname", method=RequestMethod.GET)
    public ContactResponse findByFirstnameAndLastname(@RequestBody @Valid ContactRequestFirstnameAndLastname contactRequestFirstnameAndLastname) {

        String firstname=contactRequestFirstnameAndLastname.getFirstname();
        String lastname=contactRequestFirstnameAndLastname.getLastname();
        
        Contact contact = contactService.findByFirstnameAndLastname(firstname, lastname);
        
        if (contact != null) {
  
            // Invoca logica de negocio
        	Contact updatedContact = contactService.save(contact);
        	    
        	// Mapeo entity ==> response dto
        	ContactResponse contactResponse = mapper.map(updatedContact, ContactResponse.class);
        	
        	return contactResponse;
        }
		return null;
    }

	@RequestMapping(value="/contact/findAll", method=RequestMethod.GET)
    public List<ContactResponse> findAll() {
        
        List<Contact> contactList = (List<Contact>) contactService.findAll();
        List<ContactResponse> contactResponseList = new ArrayList<ContactResponse>();
        
        if (contactList != null) {
            for (Contact contact : contactList) {
            	// Invoca logica de negocio
        		Contact updatedContact = contactService.save(contact);
        	    
        		// Mapeo entity ==> response dto
        	    ContactResponse contactResponse = mapper.map(updatedContact, ContactResponse.class);
        	    
        	    contactResponseList.add(contactResponse);
          
            }
        }

        return contactResponseList;
    }
	
	@RequestMapping(value="/contact/updateContact", method=RequestMethod.PUT)
    public ContactResponse updateContact(@RequestBody @Valid ContactRequest contactRequest) {
		
		String firstname=contactRequest.getFirstname();
        String lastname=contactRequest.getLastname();
        String phonenumber=contactRequest.getPhonenumber();
        String email=contactRequest.getEmail();
        
        // Mapeo request dto ==> entity
        Contact contact = contactService.findByFirstnameAndLastname(firstname, lastname);

		// Invoca logica de negocio
        contact.setPhonenumber(phonenumber);
		contact.setEmail(email);
		Contact updatedContact = contactService.save(contact);
		
		// Mapeo entity ==> response dto
	    ContactResponse contactResponse = mapper.map(updatedContact, ContactResponse.class); 
	    
	    return contactResponse;
    }
    
	@RequestMapping(value="/contact/deleteByFirstnameAndLastname", method=RequestMethod.DELETE)
    public void deleteByFirstnameAndLastname(@RequestBody @Valid ContactRequestFirstnameAndLastname contactRequestFirstnameAndLastname) {
        
		String firstname=contactRequestFirstnameAndLastname.getFirstname();
		String lastname=contactRequestFirstnameAndLastname.getLastname();
        
		contactService.deleteByFirstnameAndLastname(firstname, lastname);
        
    }
}
