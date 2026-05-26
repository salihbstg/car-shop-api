package com.bastug.customerservice.mapper;

import com.bastug.customerservice.dtos.CreateCustomerRequest;
import com.bastug.customerservice.dtos.CustomerResponse;
import com.bastug.customerservice.dtos.CustomerUpdateRequest;
import com.bastug.customerservice.entity.Customer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);
    CustomerResponse toResponse(Customer save);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateCustomer(CustomerUpdateRequest customerUpdateRequest,@MappingTarget Customer customer);
}
