package org.learn.jpa_playground.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private int uniqueKey;

    private String userId;

    private String userPassword;

    private String userName;

    private String userEmail;

    private String userPhone;
}