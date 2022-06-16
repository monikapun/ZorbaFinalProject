package com.zorba.shoppingCart.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;




import com.zorba.shoppingCart.DTO.CustomerDTO;
import com.zorba.shoppingCart.DTO.ItemsDTO;
import com.zorba.shoppingCart.entity.Customer;
import com.zorba.shoppingCart.entity.Items;
import com.zorba.shoppingCart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	// method to save all the records in the Customer DTO ( data transfer object )
	public boolean saveAllRecords(List<CustomerDTO> customerDTOList) {

		//Customer customer = new Customer(); // create a Customer object
		//List<Items> foodItems = new ArrayList<>(); // Arraylist to store food items

		// save or transfer information from DTO object to Entity object
		for (CustomerDTO customerDTO : customerDTOList) {
			Customer customer = new Customer();
			customer.setCustName(customerDTO.getCustName());
			customer.setMobileNumber(customerDTO.getMobNumber());
			customer.setCountry(customerDTO.getCountry());

			// create list of ItemsDTO to save the item list in the CustomerDTO
			List<ItemsDTO> itemsDTOList = customerDTO.getItems(); // Items list retrieved from ItemDTO
			for (ItemsDTO iterating : itemsDTOList) { // iterate through itemDTO list
				Items items = new Items(); // create Item object
				items.setName(iterating.getName()); // save item name
				items.setQuantity(iterating.getQuantity());
				//foodItems.add(items);
				customer.addItems(items); // invoke addItems method in Customer Entity class
			}
			//customer.setItems(foodItems);

			shoppingCartRepository.save(customer); // saving the entire CustomerDTO object in to the Customer object
		}
		return true;
	}

	// fetch all customers
	public List<CustomerDTO> fetchRecords() {
		Iterable<Customer> customerList = shoppingCartRepository.findAll();
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		Iterator iterator = customerList.iterator();
		while (iterator.hasNext()){
			Customer customer = (Customer) iterator.next();
			CustomerDTO customerDTO = new CustomerDTO();

			customerDTO.setCustName(customer.getCustName());
			customerDTO.setMobNumber(customer.getMobileNumber());
			customerDTO.setCountry(customer.getCountry());
			List<ItemsDTO> itemsDTOList = new ArrayList<>();
			for(Items items: customer.getItems()){
				ItemsDTO itemsDTO = new ItemsDTO();
				itemsDTO.setName(items.getName());
				itemsDTO.setQuantity(items.getQuantity());
				itemsDTOList.add(itemsDTO);
			}
			customerDTO.setItems(itemsDTOList);
			customerDTOList.add(customerDTO);


		}

		return customerDTOList; // return all the customer objects
	}


	public CustomerDTO fetchItemsSelectedByCustomer(Long customerId) {
		Customer customer = new Customer();
		CustomerDTO customerDTO = new CustomerDTO();
		Optional<Customer> cust = shoppingCartRepository.findById(customerId);
		if(cust != null)
		{
			customer = cust.get();
			customerDTO.setCustName(customer.getCustName());
			customerDTO.setMobNumber(customer.getMobileNumber());
			customerDTO.setCountry(customer.getCountry());
			List<ItemsDTO> itemsDTOList = new ArrayList<>();
			for(Items items: customer.getItems()){
				ItemsDTO itemsDTO = new ItemsDTO();
				itemsDTO.setName(items.getName());
				itemsDTO.setQuantity(items.getQuantity());
				itemsDTOList.add(itemsDTO);
			}
			customerDTO.setItems(itemsDTOList);
		}


		return customerDTO;
	}

	public Boolean deleteCustomerRecords(Long customerId) {

shoppingCartRepository.deleteById(customerId);
		return true;
	}


	public boolean updateCustomerInformation(CustomerDTO customerDTO, Long customerId) {
		Optional<Customer> optionalCustomer = shoppingCartRepository.findById(customerId);
		if (optionalCustomer != null) {
			Customer newCustomer = optionalCustomer.get();
			newCustomer.setCustName(customerDTO.getCustName());
			newCustomer.setMobileNumber(customerDTO.getMobNumber());
			newCustomer.setCountry(customerDTO.getCountry());
			List<ItemsDTO> itemsDTOList = customerDTO.getItems();
			for (ItemsDTO iterating : itemsDTOList) { // iterate through itemDTO list
				Items items = new Items(); // create Item object
				items.setName(iterating.getName()); // save item name
				items.setQuantity(iterating.getQuantity());
				//foodItems.add(items);
				newCustomer.addItems(items); // invoke addItems method in Customer Entity class
			}
			// save updated customer
			shoppingCartRepository.save(newCustomer);

		}
		return true;
	}
}


