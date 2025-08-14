package org.learn.jpa_playground.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.learn.jpa_playground.enums.UserRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private int uniqueKey;

    @NotBlank(message = "{userId.required}")
    private String userId;

    @NotBlank(message = "{userPassword.required}")
    private String userPassword;

    @NotBlank(message = "{userName.required}")
    private String userName;

    @NotBlank(message = "{userEmail.required}")
    private String userEmail;

    @NotBlank(message = "{userPhone.required}")
    private String userPhone;

    private UserRole userRole;

    private String createdDate;

    private String editDate;

}

