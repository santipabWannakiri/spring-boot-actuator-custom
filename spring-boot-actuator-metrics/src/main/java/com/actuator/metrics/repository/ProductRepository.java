package com.actuator.metrics.repository;


import com.actuator.metrics.model.Product;
import com.actuator.metrics.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProductRepository")
public interface ProductRepository extends CrudRepository<Product,Integer> {
    List<Product> findByStatus(Status status);

}
