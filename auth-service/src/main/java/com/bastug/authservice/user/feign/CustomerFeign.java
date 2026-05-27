package com.bastug.authservice.user.feign;

import com.bastug.authservice.user.dto.CustomerResponse;
import com.bastug.authservice.user.dto.CustomerDetail;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "customer-service",
        url = "http://localhost:8081/api/v1/customers/"
)
public interface CustomerFeign {
    @PostMapping
    ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerDetail customerDetail
    );

}
