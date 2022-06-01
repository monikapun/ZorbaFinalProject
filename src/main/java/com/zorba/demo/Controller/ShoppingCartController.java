package com.zorba.demo.Controller;

import com.zorba.demo.DTO.CustomerDTO;
import com.zorba.demo.Services.ShoppingCartService;
import com.zorba.demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/shoppingCart")

public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingcartService;


    @PostMapping("/saveRecords")
    public Boolean saveRecords(@RequestBody List<CustomerDTO> customerDetails) {
        return shoppingcartService.saveRecords(customerDetails);
    }

    @GetMapping(value = "/fetchRecords", produces= MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO fetchRecords(@RequestParam(name="custId") Long custId) {
        Customer cust = shoppingcartService.fetchItemsSelectedByCustomer(custId);
        CustomerDTO customerDTO = new CustomerDTO();
        return customerDTO;
    }

    @DeleteMapping(value="/deleteRecords")
    public Boolean deleteRecords(@RequestParam(name="custId") Long custId) {
        return shoppingcartService.deleteCustomerRecords(custId);
    }
}
