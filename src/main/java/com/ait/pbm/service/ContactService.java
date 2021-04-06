package com.ait.pbm.service;

import java.util.List;

import com.ait.pbm.entity.Contact;
import com.ait.pbm.exception.NoDataFoundException;

public interface ContactService {
	
	public boolean saveContact(Contact contact) throws NoDataFoundException;
	
	public List<Contact> getAllContacts();
	
	public Contact getContactBYId (Integer contactId) ;
	
	public boolean deleteContactById(Integer contactId) ;

}
