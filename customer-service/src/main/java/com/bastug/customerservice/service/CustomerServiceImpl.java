package com.bastug.customerservice.service;

import com.bastug.customerservice.dtos.CreateCustomerRequest;
import com.bastug.customerservice.dtos.CustomerResponse;
import com.bastug.customerservice.entity.Customer;
import com.bastug.customerservice.mapper.CustomerMapper;
import com.bastug.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer= customerMapper.toCustomer(createCustomerRequest);
        return customerMapper.toResponse(customerRepository.save(customer));
    }
}
