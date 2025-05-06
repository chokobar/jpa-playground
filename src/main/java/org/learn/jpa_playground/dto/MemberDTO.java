package org.learn.jpa_playground.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private String id;
    private String password;
    private String name;
    private int age;
    private String email;
    private String phone;
}