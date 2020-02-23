package com.sunwell.authentication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunwell.authentication.model.Contact;
import com.sunwell.authentication.model.UserCredential;


public interface ContactRepo extends JpaRepository<Contact, Long> {
	Contact findByUserCredential(UserCredential _user);
	Contact findByUserCredential_SystemId(Integer _id);
}
