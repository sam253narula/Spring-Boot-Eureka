package com.ofss;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

	
	Stock s1=new Stock(1, "orcl", 1234);
	Stock s2=new Stock(2, "wipro", 2345);
	Stock s3=new Stock(3, "infy", 3456);
	Stock s4=new Stock(4, "techmahindra", 5678);	
	Stock s5=new Stock(5, "accenture", 7890);

	ArrayList<Stock> allStocks=new ArrayList<Stock>(Arrays.asList(s1,s2,s3,s4,s5));
	
	@RequestMapping("/stocks")
	public ArrayList<Stock> listAllStocks()
	{
		return allStocks;
	}

	
	@RequestMapping("/stocks/{stockId}")
	public Stock getAStock(@PathVariable("stockId") int stockId)
	{
		Stock st=null;
		for (Stock s:allStocks)
		{
			if (s.getStockId()==stockId)
			{
				st=s;
				break;
			}
		}
		return st;
	}
}
