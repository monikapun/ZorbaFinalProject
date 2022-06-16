package com.zorba.shoppingCart.controller;

import java.util.List;

import com.zorba.shoppingCart.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.zorba.shoppingCart.DTO.CustomerDTO;
import com.zorba.shoppingCart.service.ShoppingCartService;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingcartService;



	@PostMapping("/saveRecords")
	public Boolean saveRecords(@RequestBody List<CustomerDTO> customerDetails) {
		return shoppingcartService.saveAllRecords(customerDetails);
	}

	@GetMapping(value = "/getAllRecords", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomerDTO> getAllRecords(){
		return shoppingcartService.fetchRecords();

	}


	/*@GetMapping(value = "/fetchRecords", produces=MediaType.APPLICATION_JSON_VALUE)
	public CustomerDTO fetchRecords(@RequestParam(name="custId") Long c) {
		Customer cust = shoppingcartService.fetchItemsSelectedByCustomer(c);
		CustomerDTO customerDTO = new CustomerDTO();
		return customerDTO;
	}*/
	@GetMapping(value = "/fetchRecords", produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerDTO fetchRecords(@RequestParam(name = "customerId") Long customerId) {
		CustomerDTO customer = shoppingcartService.fetchItemsSelectedByCustomer(customerId);
		return customer;
	}
	@DeleteMapping(value="/deleteRecords")
	public Boolean deleteRecords(@RequestParam(name="customerId") Long customerId) {
		return shoppingcartService.deleteCustomerRecords(customerId);
	}
@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
		public String update(@RequestBody CustomerDTO customerDTO, @RequestParam ("customerId") Long customerId){
		String status="";
		try{
		if (shoppingcartService.updateCustomerInformation(customerDTO, customerId))	{
			status = "record update successful";
		}else {
			status="failure, cannot update record";
		}
		}catch (Exception e){
			status=e.getMessage();
		}


	return status;

}
}
