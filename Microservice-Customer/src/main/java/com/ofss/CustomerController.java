package com.ofss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	
//	Address a1=new Address(5,"Golden Globe Apt","Bangalore",560102L);
//	int stocks1[]= {1,2};
//	Customer c1=new Customer(1, "Guru", a1,stocks1);
	

	Address a2=new Address(23,"Vaswani Techno","Bangalore",560155L);
	int stocks2[]= {1,2};
	Customer c2=new Customer(2, "Peter", a2,stocks2);

	Address a3=new Address(14,"SRH Building","Mumbai",234552L);
	int stocks3[]= {3,4};
	Customer c3=new Customer(3, "Mukul", a3,stocks3);

	Address a4=new Address(500,"Mantri Apt","Bangalore",560105L);
	int stocks4[]= {1,2,3};
	Customer c4=new Customer(4, "Deepak", a4,stocks4);

	Address a5=new Address(104,"Shobha Apt","Bangalore",560155L);
	int stocks5[]= {1,2,3};
	Customer c5=new Customer(5, "Pradeep", a5,stocks5);

	//ArrayList<Customer> allCustomers=new ArrayList(Arrays.asList(c1,c2,c3,c4,c5));
//	Address a2=new Address(23,"Vaswani Techno","Bangalore",560155L);
//	int stocks2[]= {1,2};
//	Customer c2=new Customer(2, "Peter", a2,stocks2);
	int[] ints = {1,2,3};
	List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
	int stocks1[]= {1,2};
	@SuppressWarnings("unchecked")
	ArrayList<Customer> allCustomers=new ArrayList<Customer>(Arrays.asList(Customer.builder()
			.customerId(1)
			.customerName("Samarth")
			.address(Address.builder()
					.flatNo(850)
					.streetName("LIC Colony")
					.city("Mumbai")
					.pinCode(400500L)
					.build())
			.stockIds(stocks1)
			.build(),
			Customer.builder()
			.customerId(2)
			.customerName("Aniket")
			.address(Address.builder()
					.flatNo(850)
					.streetName("Vaswani Techno")
					.city("Bangalore")
					.pinCode(400505L)
					.build())
			.stockIds(stocks2)
			.build(),
			Customer.builder()
			.customerId(3)
			.customerName("Parth")
			.address(Address.builder()
					.flatNo(850)
					.streetName("Shobha Apt")
					.city("Pune")
					.pinCode(400505L)
					.build())
			.stockIds(stocks3)
			.build()
			));

	@RequestMapping(value="/customers",method=RequestMethod.GET)
	public ArrayList<Customer> getCustomersList()
	{
		return allCustomers;
	}
	
	
	@RequestMapping(value="/customers/{customerid}/stocks",method=RequestMethod.GET)
	public List<Stock> getCustomerStocks(@PathVariable(value="customerid") int customerid)
	{
		System.out.println("Entering getCustomerStocks");
		List<Stock> allStocks=new ArrayList();
		Customer cust=null;
		
		// Returning Customer object for a given customer id
		for (Customer c:allCustomers)
		{
			if (c.getCustomerId()==customerid)
			{
				cust=c;
			}
				
		}
		
		// return an array of stock ids belonging to this customer
		int stockIds[]=cust.getStockIds();
		
		for (int sid:stockIds)
			System.out.println("stock id "+sid);
		
		for (int i=0;i<stockIds.length;i++)
		{
			System.out.println("Loop "+stockIds[i]);
		Stock stock=remoteStockRepository.getStock(stockIds[i]);
		allStocks.add(stock);
		}
		return allStocks;
		
		
	}
//	@RequestMapping(value="/customers/{customerid}",method=RequestMethod.GET)
//	public Customer getCustomer(@PathVariable(value="customerid") int customerid)
//	{
//		// Complete the coding
//	}
//
//
//	// Incomplete code
//	public Customer addACustomer() 
//	{
//		// Complete the coding
//	}	
	
	@RequestMapping(value="/info",method=RequestMethod.GET)
	public String info()
	{
		String msg="<h1>This is Customer microservice";
		msg+="<br>This contains info about all customers";
		msg+="<br>URI: /info to see this information";
		msg+="<br>URI: /customers to see list of customers";
		msg+="<br>URI: /customers/custid to see a specific customer<h1>";
		return msg;
	}

	
}
