package com.zorba.demo.repositroy;

import com.zorba.demo.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public class ShoppingCartRepository extends CrudRepository<Customer, Long> {
}
