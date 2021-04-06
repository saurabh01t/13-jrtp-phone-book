package com.ait.pbm.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.pbm.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Serializable>{

}
