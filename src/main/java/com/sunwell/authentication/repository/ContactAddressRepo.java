package com.sunwell.authentication.repository;

import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;

import com.sunwell.authentication.model.ContactAddress;


public interface ContactAddressRepo extends JpaRepository<ContactAddress, Long> {
}
