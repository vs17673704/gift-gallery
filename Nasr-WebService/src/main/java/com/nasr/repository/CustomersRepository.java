package com.nasr.repository;

import com.nasr.entity.Customers;
import com.nasr.webservice.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends CrudRepository<Customers, String>{

}
