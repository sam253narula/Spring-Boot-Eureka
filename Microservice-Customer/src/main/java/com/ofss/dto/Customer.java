package com.ofss.dto;

import com.ofss.dto.Address.AddressBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	int customerId;
	String customerName;
	Address address;
	int stockIds[];
	
}