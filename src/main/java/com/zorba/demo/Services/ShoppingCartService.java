package com.zorba.demo.Services;

import com.zorba.demo.DTO.CustomerDTO;
import com.zorba.demo.entity.Customer;
import com.zorba.demo.repositroy.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class ShoppingCartService {
    public Boolean saveRecords(List<CustomerDTO> customerDetails) {
    }

    public Customer fetchItemsSelectedByCustomer(Long custId) {
    }

    public Boolean deleteCustomerRecords(Long custId) {
    }

    @Service
    public class ShoppingCartService {

        @Autowired
        private ShoppingCartRepository shoppingCartRepository;

        public Boolean saveRecords(List<CustomerDTO> customers) {
            return true;
        }

        public Customer fetchItemsSelectedByCustomer(Long custId) {
            Customer customer = new Customer();
            return customer;
        }

        public Boolean deleteCustomerRecords(Long custId) {
            return true;
        }
    }
}
