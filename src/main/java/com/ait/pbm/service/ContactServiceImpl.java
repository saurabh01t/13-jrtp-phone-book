package com.ait.pbm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ait.pbm.entity.Contact;
import com.ait.pbm.exception.NoDataFoundException;
import com.ait.pbm.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService{
	
	private ContactRepository contactRepo;
	
	public ContactServiceImpl(ContactRepository contactRepo) {
		this.contactRepo = contactRepo;
	}

	@Override
	public boolean saveContact(Contact contact) {
		try {
			Contact savedContact = contactRepo.save(contact);
			// return savedContact.getContactId() != null ? true : false;
			/*
			 * if(null == savedContact) { throw new
			 * NoDataFoundException("Contact Not Saved Successfully !."); }
			 */
			return savedContact.getContactId() != null;
		} catch (Exception e) {
			throw new NoDataFoundException("Contact Not Saved!.");
		}

	}
	

	@Override
	public List<Contact> getAllContacts() {
		return contactRepo.findAll();
	}

	@Override
	public Contact getContactBYId(Integer contactId) {
		Optional<Contact> findById = contactRepo.findById(contactId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean deleteContactById(Integer contactId) {
		try {
			contactRepo.deleteById(contactId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	


}
