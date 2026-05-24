package com.bastug.customerservice.service;

import com.bastug.customerservice.dtos.CreateCustomerRequest;
import com.bastug.customerservice.dtos.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);
}
