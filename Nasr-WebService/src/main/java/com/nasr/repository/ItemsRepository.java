package com.nasr.repository;

import com.nasr.entity.Items;
import com.nasr.webservice.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends CrudRepository<Items, Integer>{


}
