package com.usermanagementproject.jwtsecurity.dto;

import lombok.Data;

@Data
public class EditDto {

    Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
