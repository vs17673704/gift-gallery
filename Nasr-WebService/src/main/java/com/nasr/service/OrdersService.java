package com.nasr.service;

import com.nasr.entity.Customers;
import com.nasr.entity.Items;
import com.nasr.entity.Orders;
import com.nasr.repository.OrdersRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
	
	@Autowired
	private OrdersRepository ordersRepository;
        
        private EntityManager em;
        
        @Autowired
        public OrdersService(EntityManager theEntityManager) {
		this.em = theEntityManager;
	}
        
        @Autowired
        private JdbcTemplate jdbcTemplate;
        
        private Orders order = new Orders();
	
	// Add new Order
        @Transactional
	public String addOrders(String custId, String itemId) {
            EntityTransaction tx = null;
            Session session = null;
            String placedStatus = "Failed";
            
            List<Customers> cs = em.createNamedQuery("Customers.findByCustId").setParameter("custId", custId).getResultList();
            Customers c = new Customers();
            if (!cs.isEmpty()) {
                c = cs.get(0);
            }
            
            List is = em.createNamedQuery("Items.findByItemid").setParameter("itemid", Integer.parseInt(itemId)).getResultList();
            
            Items i = new Items();
            if (!is.isEmpty()) {
                Iterator orderIterator=is.iterator();
            while(orderIterator.hasNext()) {
                    Object[] itemInfo = (Object[])orderIterator.next();
                    i.setItemid(Integer.parseInt(itemInfo[0].toString()));
                    i.setItemname(itemInfo[1].toString());
                    i.setPrice(Integer.parseInt(itemInfo[2].toString()));
                }
            }
            
	try {
            Date date = new Date();
            String regex = "dd/MM/yyyy hh:mm a";
            DateFormat df = new SimpleDateFormat(regex);
            String orderDate = df.format(date);
            order.setCustId(c);
            order.setItemid(i);
            order.setOrderdate(orderDate);
            order.setStatus("Order Placed");
            session = em.unwrap(Session.class);
            em.merge(order);
            placedStatus = "Order Successfuly Placed !";
            
        } catch (Exception e) {
            placedStatus = e.getMessage();
        }
            return placedStatus;
}

	// Get all orders
        public Iterable<Orders> getAllOrders() {
                List order_list = new ArrayList<>();
                Map order_map = new LinkedHashMap();
                List us = em.createNativeQuery("SELECT o.orderid,o.orderdate,o.status,c.custname, i.itemname, i.itemid, c.cust_id FROM orders o\n" +
                                                "INNER JOIN items i ON o.ITEMID = i.ITEMID \n" +
                                                "INNER JOIN customers c ON o.CUST_ID = c.CUST_ID")
                                                .getResultList();
                Iterator orderIterator=us.iterator();
                while(orderIterator.hasNext()) {
                    Object[] custInfo = (Object[])orderIterator.next();
                    order_map = new LinkedHashMap();
                    order_map.put("orderid",custInfo[0]);
                    order_map.put("orderdate",custInfo[1]);
                    order_map.put("status",custInfo[2]);
                    order_map.put("custName",custInfo[3]);
                    order_map.put("item",custInfo[4]);
                    order_map.put("itemid",custInfo[5]);
                    order_map.put("custid",custInfo[6]);
                    order_list.add(order_map);
                }
                return order_list;
    }
	
	public Iterable<Orders> getOrdersById(String id) {
                List order_list = new ArrayList<>();
                Map order_map = new LinkedHashMap();
                List<Orders> us = em.createNativeQuery("SELECT o.orderid,o.orderdate,o.status,c.custname, i.itemname, i.itemid, c.cust_id \n" +
                                                        "FROM ORDERS o INNER JOIN ITEMS i ON i.ITEMID=o.ITEMID INNER JOIN \n" +
                                                        "CUSTOMERS c ON c.CUST_ID=o.CUST_ID WHERE o.cust_id =?")
                                                        .setParameter(1, id).getResultList();
                Iterator orderIterator=us.iterator();
                while(orderIterator.hasNext()) {
                    Object[] custInfo = (Object[])orderIterator.next();
                    order_map = new LinkedHashMap();
                    order_map.put("orderid",custInfo[0]);
                    order_map.put("orderdate",custInfo[1]);
                    order_map.put("status",custInfo[2]);
                    order_map.put("custName",custInfo[3]);
                    order_map.put("item",custInfo[4]);
                    order_map.put("itemid",custInfo[5]);
                    order_map.put("custid",custInfo[6]);
                    order_list.add(order_map);
                    System.out.println(order_list);
                    System.out.println(order_map);
                }
                System.out.println(order_list);
                return order_list;
	}
	
	// Cancel a order
        @Transactional
	public String updateOrders(Integer id) {
                        String SQL = "UPDATE ORDERS o SET o.STATUS = 'Cancelled' WHERE o.ORDERID = ?";
                        jdbcTemplate.update(SQL,id);
                        
			return "Updated";
	}
	
	// Delete a order
        @Transactional
	public String deleteOrders(Integer id) {
		try{
                        String SQL = "DELETE FROM ORDERS o WHERE o.ORDERID = ?";
                        jdbcTemplate.update(SQL,id);
			return "Deleted";
		}catch(Exception e) {
			return "Failed";
		}
	}
	
}
