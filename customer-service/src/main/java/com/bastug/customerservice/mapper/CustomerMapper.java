package com.bastug.customerservice.mapper;

import com.bastug.customerservice.dtos.CreateCustomerRequest;
import com.bastug.customerservice.dtos.CustomerResponse;
import com.bastug.customerservice.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);
    CustomerResponse toResponse(Customer save);
}
