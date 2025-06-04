package com.acme.nutrimove.subscriptionservices.backend.external.clients;

import com.acme.nutrimove.subscriptionservices.backend.external.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", path = "/api/v1/users")
public interface UserFeignClient {

    @GetMapping("/{userId}")
    UserDTO getUserById(@PathVariable("userId") Long userId);
}
