package com.nasr.service;

import com.nasr.entity.Items;
import com.nasr.repository.ItemsRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsService {
	
	@Autowired
	private ItemsRepository itemsRepository;
        
        @Autowired
        private EntityManagerFactory entityManagerFactory;
        
            
	
	// Add new items
	public String addItems(Items i) {
		
		try {
			itemsRepository.save(i);
			return "saved";
		} catch(Exception e) {
			return "failed";
		}
	}

	// Get all items
	public Iterable<Items> getAllItems(){
                List item_list = new ArrayList<>();
                EntityManager em = entityManagerFactory.createEntityManager();
                Map item_map = new LinkedHashMap();
                try{
                    
                    List is = em.createNamedQuery("Items.findAll").getResultList();
                    Iterator itemIterator=is.iterator();
                    while(itemIterator.hasNext()) {
                        Object[] itemInfo = (Object[])itemIterator.next();
                        item_map = new LinkedHashMap();
                        item_map.put("itemid",itemInfo[0]);
                        item_map.put("itemname",itemInfo[1]);
                        item_map.put("price",itemInfo[2]);
                        item_list.add(item_map);
                        System.out.println(item_list+"\n"+item_map);
                    }
                    return item_list;
                }
                catch(Exception e) {
                    return item_list;
		}
                finally{
                        em.close();
                }
                
	}
	
	// Get single items by Id
	public Object getItems(Integer id) {
            EntityManager em = entityManagerFactory.createEntityManager();
                List item_list = new ArrayList<>();
                Map item_map = new LinkedHashMap();
                
                try{
                    
                    List<Items> is = em.createNamedQuery("Items.findByItemid").setParameter("itemid", id).getResultList();
                Items i = new Items();
                Iterator itemIterator=is.iterator();
                while(itemIterator.hasNext()) {
                    Object[] itemInfo = (Object[])itemIterator.next();
                    item_map = new LinkedHashMap();
                    item_map.put("itemid",itemInfo[0]);
                    item_map.put("itemname",itemInfo[1]);
                    item_map.put("price",itemInfo[2]);
                    item_list.add(item_map);
                }
                System.out.println(item_map);
                return item_map;
                }
                catch(Exception e) {
                    return item_map;
		}
                finally{
                        em.close();
                }
                
	}
	
	// Update a items
	public String updateItems(Integer id, Items i) {
		try {
			i.setItemid(id);
			itemsRepository.save(i);
			return "Updated";
		}catch(Exception e) {
			return "Failed";
		}
	}
	
	// Delete a items
	public String deleteItems(Integer id) {
		try{
			itemsRepository.deleteById(id);
			return "Deleted";
		}catch(Exception e) {
			return "Failed";
		}
	}
	
}
