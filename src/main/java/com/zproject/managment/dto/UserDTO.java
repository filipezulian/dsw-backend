package com.zproject.managment.dto;

import com.zproject.managment.model.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Profile profile;
}
