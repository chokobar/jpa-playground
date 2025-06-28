package org.learn.jpa_playground.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateForm {

    @NotBlank(message = "{userId.required}")
    private String userId;

    @NotBlank(message = "{userName.required}")
    private String userName;

    @NotBlank(message = "{userEmail.required}")
    private String userEmail;

    @NotBlank(message = "{userPhone.required}")
    private String userPhone;
}
