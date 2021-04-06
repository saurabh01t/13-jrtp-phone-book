package com.ait.pbm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.pbm.AppProperties;
import com.ait.pbm.constant.AppConstant;
import com.ait.pbm.entity.Contact;
import com.ait.pbm.service.ContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1.0/phonebook")
@Api("This is phone book application")
public class ContactRestController {
	
	private static final Logger looger = LoggerFactory.getLogger(ContactRestController.class);
	
	private ContactService contactSrv;
	
	private AppProperties appProperties;
	
	public ContactRestController(ContactService contactSrv, AppProperties appProperties) {
		this.contactSrv = contactSrv;
		this.appProperties = appProperties;
	}

	@PostMapping
	@ApiOperation("This method is used for adding new Contact")
	public ResponseEntity<String> saveContact(@RequestBody Contact contact) {
		boolean savedContact = contactSrv.saveContact(contact);
		if (savedContact) {
			String succMsg = appProperties.getMessages().get(AppConstant.SAVE_CONTACT_SUCCESS);
			return new ResponseEntity<>(succMsg, HttpStatus.CREATED);
		}
		String failMsg = appProperties.getMessages().get(AppConstant.SAVE_CONTACT_FAILURE);
		return new ResponseEntity<>(failMsg, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping
	@ApiOperation("This method is used for get all contacts")
	public ResponseEntity<List<Contact>> getAllContact() {
		List<Contact> allContacts = contactSrv.getAllContacts();
		return new ResponseEntity<>(allContacts, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("this method is used for getting contact by id")
	public ResponseEntity<Contact> findContactById (@PathVariable String id) {
		Contact contactBYId = contactSrv.getContactBYId(Integer.valueOf(id));
		return new ResponseEntity<Contact>(contactBYId, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("this method is used for deleting contact by id")
	public ResponseEntity<?> deleteContactById (@PathVariable String id) {
		boolean deletedContact = contactSrv.deleteContactById(Integer.valueOf(id));
		if(deletedContact) {
			return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
