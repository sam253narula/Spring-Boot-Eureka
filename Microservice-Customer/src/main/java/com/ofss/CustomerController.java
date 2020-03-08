package com.ofss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ofss.dto.Address;
import com.ofss.dto.Customer;

@RestController
public class CustomerController {

	@Autowired
	RemoteStockRepository remoteStockRepository;

	ArrayList<Customer> allCustomers = new ArrayList<Customer>(Arrays.asList(
			Customer.builder().customerId(1).customerName("Samarth")
					.address(Address.builder().flatNo(850).streetName("LIC Colony").city("Mumbai").pinCode(400500L)
							.build())
					.stockIds(new int[] { 1, 2 }).build(),
			Customer.builder().customerId(2).customerName("Aniket")
					.address(Address.builder().flatNo(850).streetName("Vaswani Techno").city("Bangalore")
							.pinCode(234552L).build())
					.stockIds(new int[] { 1, 2 }).build(),
			Customer.builder().customerId(3).customerName("Parth")
					.address(Address.builder().flatNo(860).streetName("Shobha Apt").city("Pune").pinCode(560105L)
							.build())
					.stockIds(new int[] { 3, 4 }).build(),
			Customer.builder().customerId(4).customerName("Ruth")
					.address(
							Address.builder().flatNo(870).streetName("Baga Beach").city("Goa").pinCode(560155L).build())
					.stockIds(new int[] { 1, 2, 3 }).build(),
			Customer.builder().customerId(5).customerName("Pradeep").address(
					Address.builder().flatNo(707).streetName("RNA Continental").city("mumbai").pinCode(400509L).build())
					.stockIds(new int[] { 3, 4, 5 }).build()));

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public ArrayList<Customer> getCustomersList() {
		return allCustomers;
	}

	@RequestMapping(value = "/customers/{customerid}/stocks", method = RequestMethod.GET)
	public List<Stock> getCustomerStocks(@PathVariable(value = "customerid") int customerid) {
		System.out.println("Entering getCustomerStocks");
		List<Stock> allStocks = new ArrayList();
		Customer cust = null;

		// Returning Customer object for a given customer id
		for (Customer c : allCustomers) {
			if (c.getCustomerId() == customerid) {
				cust = c;
			}

		}

		// return an array of stock ids belonging to this customer
		int stockIds[] = cust.getStockIds();

		for (int sid : stockIds)
			System.out.println("stock id " + sid);

		for (int i = 0; i < stockIds.length; i++) {
			System.out.println("Loop " + stockIds[i]);
			Stock stock = remoteStockRepository.getStock(stockIds[i]);
			allStocks.add(stock);
		}
		return allStocks;

	}
	// @RequestMapping(value="/customers/{customerid}",method=RequestMethod.GET)
	// public Customer getCustomer(@PathVariable(value="customerid") int customerid)
	// {
	// // Complete the coding
	// }
	//
	//
	// // Incomplete code
	// public Customer addACustomer()
	// {
	// // Complete the coding
	// }

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info() {
		String msg = "<h1>This is Customer microservice";
		msg += "<br>This contains info about all customers";
		msg += "<br>URI: /info to see this information";
		msg += "<br>URI: /customers to see list of customers";
		msg += "<br>URI: /customers/custid to see a specific customer<h1>";
		return msg;
	}

}
