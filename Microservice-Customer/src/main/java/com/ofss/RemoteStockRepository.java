package com.ofss;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class RemoteStockRepository {
	@Autowired
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;
	Stock stocks[];
	ArrayList<Stock> stockList;
	
 	public RemoteStockRepository(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	public void getAllStocks() {
		stocks = restTemplate.getForObject(serviceUrl+"/stocks", Stock[].class);
		stockList=new ArrayList<Stock>(Arrays.asList(stocks));
		
				
	}

	public Stock getStock(int stockId) {
		System.out.println(serviceUrl + "/stocks/{stockId}");
		return restTemplate.getForObject(serviceUrl + "/stocks/{stockId}",
				Stock.class, stockId);
	}

}
