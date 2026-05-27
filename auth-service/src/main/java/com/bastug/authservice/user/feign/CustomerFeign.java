package com.bastug.authservice.user.feign;

import com.bastug.authservice.user.dto.CustomerResponse;
import com.bastug.authservice.user.dto.CustomerDetail;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "customer-service",
        url = "http://localhost:8081/api/v1/customers/"
)
public interface CustomerFeign {
    @PostMapping
    ResponseEntity<CustomerResponse> createCustomer(CustomerDetail customerDetail);

    @GetMapping("/by-phone")
    Boolean existsByPhone(@RequestParam String phone);

}
