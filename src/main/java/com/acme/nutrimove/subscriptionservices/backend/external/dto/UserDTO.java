package com.acme.nutrimove.subscriptionservices.backend.external.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;
}