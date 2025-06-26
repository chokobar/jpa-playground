package org.learn.jpa_playground.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "{userId.required}")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "{userId.invalid}")
    private String userId;

    @NotBlank(message = "{userPassword.required}")
    private String userPassword;

    @NotBlank(message = "{userName.required}")
    @Pattern(regexp = "^[가-힣a-zA-Z]+$", message = "{userName.invalid}")
    private String userName;

    @NotBlank(message = "{userEmail.required}")
    private String userEmail;

    @NotBlank(message = "{userPhone.required}")
    private String userPhone;

    private String createdDate;

    private String editDate;
}