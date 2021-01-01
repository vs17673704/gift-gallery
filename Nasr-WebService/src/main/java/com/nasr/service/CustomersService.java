package com.nasr.service;

import com.nasr.entity.Customers;
import com.nasr.entity.Items;
import com.nasr.repository.CustomersRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {
	
	@Autowired
	private CustomersRepository customersRepository;
        @Autowired
        @PersistenceContext(type = PersistenceContextType.EXTENDED)
        private EntityManager em;
	
	// Add new customers
	public String addCustomers(Customers c) {
		
		try {
			customersRepository.save(c);
			return "saved";
		} catch(Exception e) {
			return "failed";
		}
	}

	// Get all customers
	public List getAllCustomers(){
                List cust_list = new ArrayList<>();
                Map cust_map = new LinkedHashMap();
                List us = em.createNamedQuery("Customers.findAll").getResultList();
                Iterator custIterator=us.iterator();
                while(custIterator.hasNext()) {
                    Object[] custInfo = (Object[])custIterator.next();
                    cust_map = new LinkedHashMap();
                    cust_map.put("custId",custInfo[0]);
                    cust_map.put("custName",custInfo[1]);
                    cust_map.put("address",custInfo[2]);
                    cust_map.put("phone",custInfo[3]);
                    cust_map.put("email",custInfo[4]);
                    cust_map.put("password",custInfo[5]);
                    cust_list.add(cust_map);
                    System.out.println(cust_list);
                    System.out.println(cust_map);
                }
                return cust_list;
	}
	
	// Get single customers by Id
	public Map loginCustomers(String username, String password) {
                List<Customers> us = new ArrayList<>();
                us = em.createNamedQuery("Customers.Login").setParameter("p1", username).setParameter("p2", password).getResultList();
                Customers customers = new Customers();
                if (    !us.isEmpty()) {
                    customers = us.get(0);
                }
                Map msg = new LinkedHashMap();
                String role = (String)null;
                try {
                    if ("nasr".equals(username) && "13643".equals(password)) {
                        role = "Admin";
                    } else if (customers.getPassword().equals(password)) {
                        role = "Customer";
                    } else {
                        role = "Login Failed";
                    }
                    msg.put("role", role);
                    msg.put("custId", customers.getCustId());
                    msg.put("custname", customers.getCustname());
                    msg.put("address", customers.getAddress());
                    msg.put("phone", customers.getPhone());
                    msg.put("email", customers.getEmail());
                    msg.put("password", customers.getPassword());


                } catch (Exception e) {
                    role = "Login Failed";
                }
                return msg;
        }
	
	// Update a Student
	public String updateCustomers(String id, Customers c) {
		try {
			customersRepository.save(c);
			return "Updated";
		}catch(Exception e) {
			return "Failed";
		}
	}
	
	// Delete a Student
	public String deleteCustomers(String id) {
		try{
			customersRepository.deleteById(id);
			return "Deleted";
		}catch(Exception e) {
			return "Failed";
		}
	}

    public Map getCustomers(String id) {
            List cust_list = new ArrayList<>();
            Map cust_map = new LinkedHashMap();
            List us = new ArrayList<>();
                us = em.createNamedQuery("Customers.findByCustName").setParameter("custId", id).getResultList();
                Customers customers = new Customers();
                Customers c = new Customers();
            if (!us.isEmpty()) {
                Iterator orderIterator=us.iterator();
            while(orderIterator.hasNext()) {
                    Object[] custInfo = (Object[])orderIterator.next();
                    cust_map = new LinkedHashMap();
                    cust_map.put("custId",custInfo[0]);
                }
            
            }
                return cust_map;
    }
	
}
