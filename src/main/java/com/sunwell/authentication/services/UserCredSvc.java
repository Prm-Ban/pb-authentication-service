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
	
//	@Autowired
//	CustomerRepo custRepo;
//	
//	@Autowired
//	SalesOfficerRepo soRepo;
	
	@Autowired
    UserCredentialRepo userRepo;
	
	@Autowired
	ContactRepo contactRepo;
	
	@Autowired
    ContactAddressRepo caRepo;
	    
	
//	@PersistenceContext
//	EntityManager em;
	
//	@Autowired
//    UserGroupRepo ugRepo;
			
//	@Autowired
//	CustomerGroupRepo cgRepo;;
//    
//    @Autowired
//    AccessRightRepo acRepo;
    
   
    
	    
    public UserCredential validate (
    		@NotNull(message="{error_no_email}") String _name, 
    		@NotNull(message="{error_no_password}") String _password ) throws Exception
    {
        try {
            UserCredential usr = userRepo.findByUserName ( _name); 

            if (usr != null) {
                // Hitung MD5 dari password masukan user.
//                String passDariUser = Util.generateMD5 (_password);
                String pass = usr.getPwd();
                pass = "$2a" + pass.substring (3);
                
//                String s = BCrypt.hashpw(_password, BCrypt.gensalt());

                System.out.println ("PASSWORD: " + _password);
//                System.out.println ("$$$$$$$$$$$$$$$$$$$$$$ hasil MD5 oleh java = " + passDariUser);
                System.out.println ("$$$$$$$$$$$$$$$$$$$$$$ passwd di DB = " + usr.getPwd ());

                // Periksa password
//                if (!passDariUser.equals (usr.getPassword ())) 
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
    
//    public Customer findCustomer(Long _id) {
//    	System.out.println("findCustomer() called");
//		return custRepo.findById(_id).orElse(null);
//    }
//    
//    public Customer findCustomerByEmail(String _email) {
//		return custRepo.findByEmail(_email);
//    }
//    
//    public Page<Customer> findAllCustomers(Pageable _page) {
//		return custRepo.findAll(_page);
//    }
//    
//    public Page<Customer> findCustomers(Filters _f, Pageable _page) throws Exception {
//		return custRepo.findAll(new GenericSpecification<Customer>(_f, Customer.class), _page);
//	}
//    
//    public List<Customer> findAllCustomers() {
//		return custRepo.findAll();
//    }
//    
//    public Customer addCustomer(
//    		@Valid @NotNull(message="{error_no_customer}") Customer _customer) 
//    {
//    	UserCredential uc = _customer.getUserCredential();
//    	_customer.setType(UserCredential.TYPE_CUSTOMER);
//    	if(uc != null) {
////	    	uc.setGroup(new UserGroup(UserGroup.CUSTOMER));
//	    	uc.setPwd(BCrypt.hashpw(uc.getPwd(), BCrypt.gensalt()));
//    	}
//        return custRepo.save(_customer);
//    }
//    
//    public Customer editCustomer(
//    		@Valid @NotNull(message="{error_no_customer}") Customer _customer, boolean _encodePassword) 
//    {
//		Customer cust = custRepo.findById(_customer.getSystemId()).orElse(null);
//		if(cust == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CUSTOMER, null);
//		
//    	_customer.setType(UserCredential.TYPE_CUSTOMER);
//    	cust.setTimezone(_customer.getTimezone());
//    	copyContact(cust, _customer, _encodePassword);
//    	return cust;
////		if(_customer.getUserCredential() != null && ) {
////			UserCredential uc = cust.getUserCredential();
////			_customer.getUserCredential().setSystemId(uc.getSystemId());
//////			_customer.getUserCredential().setGroup(uc.getGroup());
////			_customer.getUserCredential().setPwd(BCrypt.hashpw(uc.getPwd(), BCrypt.gensalt()));
////		}
////		return cust;
////		
////		if(cust.getAddresses() != null && cust.getAddresses().size() > 0) {
////			caRepo.delete(cust.getAddresses());
////			cust.setAddresses(null);
////			custRepo.flush();
////		}
////				
////        return custRepo.save(_customer);
//    }
//    
//    public Customer deleteCustomer(@NotNull(message="{error_no_id}") Long _id) {
//		Customer cust = custRepo.findById (_id).orElse(null);
//		
//		if(cust == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_CUSTOMER, null);
//		
//        custRepo.deleteById (_id);
//        return cust;
//    }
//    
//    public SalesOfficer findSalesOfficer(Long _id) {
//		return soRepo.findById(_id).orElse(null);
//    }
//    
//    public SalesOfficer findSalesOfficerByEmail(String _email) {
//		return soRepo.findByEmail(_email);
//    }
//    
//    public Page<SalesOfficer> findAllSalesOfficers(Pageable _page) {
//		return soRepo.findAll(_page);
//    }
//    
//    public List<SalesOfficer> findAllSalesOfficers() {
//		return soRepo.findAll();
//    }
//    
//    public Page<SalesOfficer> findSalesOfficers(Filters _f, Pageable _page) throws Exception {
//		return soRepo.findAll(new GenericSpecification<SalesOfficer>(_f, SalesOfficer.class), _page);
//	}
//    
//    public SalesOfficer addSalesOfficer(
//    		@Valid @NotNull(message="{error_no_sales_officer}") SalesOfficer _so) 
//    {
//    	UserCredential uc = _so.getUserCredential();
//    	_so.setType(UserCredential.TYPE_SALES_OFFICER);
//    	if(uc != null) {
////	    	uc.setGroup(new UserGroup(UserGroup.CUSTOMER));
//	    	uc.setPwd(BCrypt.hashpw(uc.getPwd(), BCrypt.gensalt()));
//    	}
//        return soRepo.save(_so);
//    }
//    
//    public SalesOfficer editSalesOfficer(
//    		@Valid @NotNull(message="{error_no_sales_officer}") SalesOfficer _so, boolean _encodePassword) 
//    {
//		SalesOfficer so = soRepo.findById(_so.getSystemId()).orElse(null);
//		if(so == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SALES_OFFICER, null);
//		
//		_so.setType(UserCredential.TYPE_SALES_OFFICER);			
//		copyContact(so, _so, _encodePassword);		
//        return so;
//		
////		if(cust.getUserCredential().getSystemId() != null ) {
////			if(!cust.getUserCredential().equals(cust.getUserCredential()))
////				throw new OperationException(StandardConstant.ERROR_CANT_FIND_USER, null);
////			
////			UserCredential user = _customer.getUserCredential();			
////			userRepo.save(user);
////		}	
//		
//		
//		
////		for(ContactAddress ca : _so.getAddresses()) {
////			ca.setOwner(so);
////		}
////		
////		so.setAddresses(_so.getAddresses());
////		so.setBirthDate(_so.getBirthDate());
////		so.setCitizenId(_so.getCitizenId());
////		so.setCitizenIdType(_so.getCitizenIdType());
////		so.setEmail(_so.getEmail());
////		so.setFirstName(_so.getFirstName());
////		so.setLastName(_so.getLastName());
////		so.setPhone(_so.getPhone());
////		if(_so.getUserCredential() != null) {
////			UserCredential uc = so.getUserCredential();
////			_so.getUserCredential().setSystemId(uc.getSystemId());
//////			_so.getUserCredential().setGroup(uc.getGroup());
////			_so.getUserCredential().setPwd(BCrypt.hashpw(uc.getPwd(), BCrypt.gensalt()));
////		}
//		
//    }
//    
//    public SalesOfficer deleteSalesOfficer(@NotNull(message="{error_no_id}") Long _id) {
//    	SalesOfficer so = soRepo.findById (_id).orElse(null);
//		
//		if(so == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_SALES_OFFICER, null);
//		
//        soRepo.deleteById (_id);
//        return so;
//    }
    
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
    
    
    
//    public Page<UserCredential> findUserByGroupId(Integer _id, Pageable _page) {
//		return userRepo.findByGroup_SystemId(_id, _page);
//    }
//    
//    public UserGroup findUserGroup(Integer _id) {
//		return ugRepo.findOne(_id);
//    }    
//    
//    public UserGroup findUserGroupByName(String _name) {
//		return ugRepo.findByName(_name);
//    }
//    
//    public Page<UserGroup> findAllUserGroups(Pageable _page) {
//        return ugRepo.findAll(_page);
//    }
//    
//    public List<UserGroup> findAllUserGroups() {
//        return ugRepo.findAll();
//    }
        
//    public Page<Customer> findCustomerByGroup(CustomerGroup _cg, Pageable _page) {
//		return custRepo.findByCustGroup(_cg, _page);
//    }
//    
//    public Page<Customer> findCustomerByGroupId(Integer _id, Pageable _page) {
//		return custRepo.findByCustGroup_SystemId(_id, _page);
//    }
        
//    public UserCredential addUser(
//    		@Valid @NotNull(message="{error_no_user}") UserCredential _user, String _imgData, String _imgPath) 
//    {
//    		
//		if(_imgData != null) {
//	        String uploadedFileLocation = _imgPath + "users/" + _user.getSystemId() + "/" + 
//	        		_user.getImage ();
//	        File dir = new File(_imgPath + "users/" + _user.getSystemId());
//	        if(!dir.exists ()) {
//	            dir.mkdir ();
//	        }
//	        sunwell.permaisuri.bus.util.Util.writeToFile(Base64.getDecoder ().decode (_imgData), uploadedFileLocation);
//        }
//    	_user.setPwd(BCrypt.hashpw(_user.getPwd(), BCrypt.gensalt()));
//        return userRepo.save(_user);
//    }
    
    
    
//    public UserCredential editUser(
//    		@Valid @NotNull(message="{error_no_user}") UserCredential _user, String _imgData, String _imgPath) 
//    {
//		UserCredential uc = userRepo.findOne(_user.getSystemId());
//		if(uc == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_USER, null);
//		
//    		if(_imgData != null) {
//	            String uploadedFileLocation = _imgPath + "users/" + _user.getSystemId() + "/" + 
//	            		_user.getImage ();
//	            File dir = new File(_imgPath + "users/" + _user.getSystemId());
//	            if(!dir.exists ()) {
//	                dir.mkdir ();
//            }
//            sunwell.permaisuri.bus.util.Util.writeToFile(Base64.getDecoder ().decode (_imgData), uploadedFileLocation);
//        }
//    	_user.setPwd(BCrypt.hashpw(_user.getPwd(), BCrypt.gensalt()));
//		return userRepo.save(_user);
//    }
    
   
    
//    public UserGroup addUserGroup(
//    		@Valid @NotNull(message="{error_no_user_group}") UserGroup _userGroup) 
//    {
//        return ugRepo.save(_userGroup);
//    }
//    
//    public UserGroup editUserGroup(
//    		@Valid @NotNull(message="{error_no_user_group}") UserGroup _userGroup) 
//    {
//		UserGroup ug = ugRepo.findOne(_userGroup.getSystemId());
//		if(ug == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_USER_GROUP, null);
//		List<AccessRights> accessRights = null;
//		
//		if(_userGroup.getAccessRights() != null && _userGroup.getAccessRights().size () > 0) {
//            accessRights = new LinkedList<>();
//            for (AccessRights accessRight : _userGroup.getAccessRights()) {
//                accessRight.setOwner(ug);
//                accessRights.add(accessRight);
//            }
//        }
//		
//		if(ug.getAccessRights() != null) {
//			acRepo.delete(ug.getAccessRights()); // mesti didelete dulu kalau objek ug diambil pakai spring data jpa
////			for(AccessRights ac : ug.getAccessRights()) {
////				acRepo.f
////			}
//			ug.setAccessRights(null);
//			ugRepo.flush();
//		}
//		
//		ug.setAccessRights(accessRights);
//		ug.setName(_userGroup.getName());
//		ug.setMemo(_userGroup.getMemo());
//		ug.setSysLastUpdate(Calendar.getInstance());
//		
//		return ug;
//    }
//    
//    public UserGroup deleteUserGroup(@NotNull(message="{error_no_id}") Integer _id) {
//		UserGroup ug = ugRepo.findOne (_id);
//		
//		if(ug == null)
//			throw new OperationException(StandardConstant.ERROR_CANT_FIND_USER_GROUP, null);
//		
//        ugRepo.delete (_id);
//        return ug;
//    }
    
//    public Customer registerCustomer(
//    		@Valid @NotNull(message="{error_no_customer}") Customer _customer) 
//    {
//    	UserCredential uc = _customer.getUserCredential();
////    	uc.setGroup(new UserGroup(UserGroup.CUSTOMER));
//    	uc.setPwd(BCrypt.hashpw(uc.getPwd(), BCrypt.gensalt()));
//		userRepo.save(_customer.getUserCredential());
//        return custRepo.save(_customer);
//    }
}
