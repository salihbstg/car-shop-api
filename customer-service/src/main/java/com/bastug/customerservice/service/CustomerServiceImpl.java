package com.bastug.customerservice.service;

import com.bastug.customerservice.dtos.CreateCustomerRequest;
import com.bastug.customerservice.dtos.CustomerResponse;
import com.bastug.customerservice.dtos.CustomerUpdateRequest;
import com.bastug.customerservice.entity.Customer;
import com.bastug.customerservice.exception.CustomerNotFountException;
import com.bastug.customerservice.exception.EmailAlreadyExistsException;
import com.bastug.customerservice.exception.PhoneAlreadyExistsException;
import com.bastug.customerservice.mapper.CustomerMapper;
import com.bastug.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        if(customerRepository.findByEmail(createCustomerRequest.email())!=null){
            throw new EmailAlreadyExistsException(createCustomerRequest.email());
        }
        if (customerRepository.findByPhone(createCustomerRequest.phone())!=null) {
            throw new PhoneAlreadyExistsException(createCustomerRequest.phone());
        }
        Customer customer = customerMapper.toCustomer(createCustomerRequest);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    @Override
    public CustomerResponse getCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.map(customerMapper::toResponse).orElseThrow(() -> new CustomerNotFountException(customerId.toString()));
    }

    @Override
    public CustomerResponse updateCustomer(CustomerUpdateRequest customerUpdateRequest, Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new CustomerNotFountException(customerId.toString());
        }
        Customer customerToUpdate = customer.get();
        customerMapper.updateCustomer(customerUpdateRequest,customer.get());
        return customerMapper.toResponse(customerRepository.save(customerToUpdate));
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFountException(id.toString());
        }
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerResponse getCustomerByEmail(String email) {
        Customer customer=customerRepository.findByEmail(email);
        if(customer==null){
            throw new CustomerNotFountException(email);
        }
        return customerMapper.toResponse(customer);
    }


    @Override
    public Page<CustomerResponse> getCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(customerMapper::toResponse);
    }




}
