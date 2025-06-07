package org.learn.jpa_playground.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private int uniqueKey;

    private String userId;

    private String userPassword;

    private String userName;

    private String userEmail;

    private String userPhone;

    private String createdDate;

    private String editDate;
}