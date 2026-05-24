package com.bastug.customerservice.service;

import com.bastug.customerservice.dtos.CreateCustomerRequest;
import com.bastug.customerservice.dtos.CustomerResponse;
import com.bastug.customerservice.dtos.CustomerUpdateRequest;
import org.jspecify.annotations.Nullable;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);
    CustomerResponse getCustomer(Long customerId);
    CustomerResponse updateCustomer(CustomerUpdateRequest customerUpdateRequest,Long customerId);
    void deleteCustomer(Long id);
    CustomerResponse getCustomerByEmail(String email);
    Page<CustomerResponse> getCustomers(Pageable pageable);
}
