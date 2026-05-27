package com.bastug.carservice.feign;

import com.bastug.carservice.config.FeignConfig;
import com.bastug.carservice.dtos.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "customer-service",
        url = "http://localhost:8081/api/v1/customers",
        configuration = FeignConfig.class
)
public interface CustomerClient {
    @GetMapping("/{id}")
    CustomerResponse getCustomer(@PathVariable("id") Long id);
}
