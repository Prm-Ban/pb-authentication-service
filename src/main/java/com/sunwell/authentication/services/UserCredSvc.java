package com.sunwell.authentication.services;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserCredFacade.java
 *
 * Created on Oct 16, 2017, 9:59:37 AM
 */



import java.io.File;

import java.util.Base64;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sunwell.authentication.exception.OperationException;
import com.sunwell.authentication.model.Contact;
import com.sunwell.authentication.model.ContactAddress;
import com.sunwell.authentication.model.UserCredential;
import com.sunwell.authentication.repository.ContactAddressRepo;
import com.sunwell.authentication.repository.ContactRepo;
import com.sunwell.authentication.repository.UserCredentialRepo;


/**
 *
 * @author Benny
 */
@Service
@Transactional
@Validated
public class UserCredSvc implements UserCredService
{	
	@Autowired
    UserCredentialRepo userRepo;
	
	@Autowired
	ContactRepo contactRepo;
	
	@Autowired
    ContactAddressRepo caRepo;   
   
    
	    
    public UserCredential validate (
    		@NotNull(message="{error_no_email}") String _name, 
    		@NotNull(message="{error_no_password}") String _password ) throws Exception
    {
        try {
            UserCredential usr = userRepo.findByUserName ( _name); 

            if (usr != null) {
                // Hitung MD5 dari password masukan user.
                String pass = usr.getPwd();
                pass = "$2a" + pass.substring (3);
                
//                String s = BCrypt.hashpw(_password, BCrypt.gensalt());

                System.out.println ("PASSWORD: " + _password);
                System.out.println ("$$$$$$$$$$$$$$$$$$$$$$ passwd di DB = " + usr.getPwd ());

                // Periksa password
                if (!BCrypt.checkpw (_password, pass)) {
                    System.out.println ("DOESN't MATCH");
                    usr = null;
                }
                else {
                    System.out.println ("MATCHED");
                }
            }
            return usr;
        }
        catch (NoResultException ex) {
            ex.printStackTrace ();
            return null;
        }
    }
    
    public UserCredential findUser(Integer _id) {
		return userRepo.findById(_id).orElse(null);
    }
    
    public UserCredential findUserByToken(String _token) {
    	return userRepo.findByRegistrationToken(_token);
    }
    
    public UserCredential findUserByName(String _name) {
		return userRepo.findByUserName(_name);
    }
    
    public Page<UserCredential> findAllUsers(Pageable _page) {
		return userRepo.findAll(_page);
    }
    
    public List<UserCredential> findAllUsers() {
    	return userRepo.findAll();
    }
    
    public UserCredential addUser(
    		@Valid @NotNull(message="{error_no_user}") UserCredential _user) 
    {
    	_user.setPwd(BCrypt.hashpw(_user.getPwd(), BCrypt.gensalt()));
        return userRepo.save(_user);
    }
    
    public UserCredential editUser(
    		@Valid @NotNull(message="{error_no_user}") UserCredential _user, String _imgData, String _imgPath, boolean _encodePassword) 
    {
    	if(_encodePassword)
    		_user.setPwd(BCrypt.hashpw(_user.getPwd(), BCrypt.gensalt()));
		return userRepo.save(_user);
    }
    
    public UserCredential deleteUser(@NotNull(message="{error_no_id}") Integer _id) {
		UserCredential uc = userRepo.findById(_id).orElse(null);
		userRepo.deleteById(_id);
		return uc;
    }   
    
    public Contact findContact(Long _id) {
		return contactRepo.findById(_id).orElse(null);
    }
        
    public Contact findContactByCred(UserCredential _uc) {
    	return contactRepo.findByUserCredential(_uc);
    }
    
    public Contact findContactByCredId(Integer _uc) {
    	return contactRepo.findByUserCredential_SystemId(_uc);
    }   
    
    public void flush() {
    	userRepo.flush();
    	contactRepo.flush();
    }
    
    private void copyContact( Contact con, Contact other, boolean _encodePassword) {
    	
    	if(con.getAddresses() != null && con.getAddresses().size() > 0) {
			caRepo.deleteAll(con.getAddresses());
			con.setAddresses(null);
			contactRepo.flush();
		}
    	
    	for(ContactAddress ca : other.getAddresses()) {
			ca.setOwner(con);
		}
		
		con.setAddresses(other.getAddresses());
		con.setBirthDate(other.getBirthDate());
		con.setCitizenId(other.getCitizenId());
		con.setCitizenIdType(other.getCitizenIdType());
		con.setEmail(other.getEmail());
		con.setFirstName(other.getFirstName());
		con.setLastName(other.getLastName());
		con.setPhone(other.getPhone());
		if(other.getUserCredential() != null) {
			UserCredential uc = other.getUserCredential();
			con.getUserCredential().setUserName(uc.getUserName());
			if(_encodePassword)
				con.getUserCredential().setPwd(BCrypt.hashpw(uc.getPwd(), BCrypt.gensalt()));
		}
    }
}
