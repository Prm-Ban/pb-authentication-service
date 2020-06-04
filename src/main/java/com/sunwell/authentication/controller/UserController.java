package com.sunwell.authentication.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunwell.authentication.dto.UserCredentialDTO;
import com.sunwell.authentication.model.Contact;
import com.sunwell.authentication.model.UserCredential;
import com.sunwell.authentication.services.UserCredService;
import com.sunwell.authentication.util.ServiceUtil;


@RestController
public class UserController {
	
	@Autowired
    UserCredService userService;
	
	@Autowired
	ServiceUtil svcUtil;

	@RequestMapping(value = { "resources/userinfo" }, produces = "application/json")
	public Map<String, Object> user(OAuth2Authentication user) {
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put(
			"user",
			user.getUserAuthentication()
			.getPrincipal());
		
		userInfo.put(
			"authorities",
			AuthorityUtils.authorityListToSet(
			user.getUserAuthentication()
			. getAuthorities()));
		
		return userInfo;
	}
	
	@RequestMapping(value = "resources/users", method = RequestMethod.GET,
			produces = "application/json"
	)
	public ResponseEntity<Map<String,Object>> getUsers(
		@RequestHeader(value="Authorization", required=false) String _auth,
		@RequestParam(value="sessionString", required=false) String _sessionString,
		@RequestParam(value="systemId", required = false) Integer _systemId,
		@RequestParam(value="name", required = false) String _name,
		Pageable _page) throws Exception 
	{
		Map<String,Object> retData = null;
		
    	try {
    		
    		Contact c = userService.findContactByCredId(_systemId);
    		
    		Object mainData = null;
    		UserCredential usr = null;
			Page<UserCredential> pageUsers = null;
			int totalPages = 0;
			long totalItems = 0;
    		
    		if(_systemId != null) {
    			usr = userService.findUser(_systemId);
    		}
    		else {
    			if(_name != null) {
        			usr = userService.findUserByName( _name);
        		}
        		else {
        			pageUsers = userService.findAllUsers(_page);
        		}
    		}
    		
    		if(pageUsers != null && pageUsers.getNumberOfElements() > 0) {
    			List<UserCredential> users = pageUsers.getContent();
    			List<UserCredentialDTO> usersData = new LinkedList<>();
    			for(UserCredential u : users) {
    				usersData.add(new UserCredentialDTO(u));
    			}
    			mainData = usersData;
    			totalPages = pageUsers.getTotalPages();
    			totalItems = pageUsers.getTotalElements();
    		}
    		else if (usr != null) {
    			mainData = new UserCredentialDTO(usr);
    			totalPages = 1;
    			totalItems = 1;
    		}
    		
    		retData = svcUtil.returnSuccessfulData(mainData, totalPages, totalItems);
    	}
    	catch(Exception e) {
    		retData = svcUtil.handleException(e);
    	}
        
        return new ResponseEntity<Map<String,Object>>(retData, null, HttpStatus.OK);
    }

}
