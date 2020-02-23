package com.sunwell.authentication.repository;

import java.util.List;





import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sunwell.authentication.model.UserCredential;


public interface UserCredentialRepo extends JpaRepository<UserCredential, Integer> {
	public UserCredential findByUserName(String _name) ;
	public UserCredential findByRegistrationToken(String _token);
}
