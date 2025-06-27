package org.learn.jpa_playground.config;

import org.springframework.validation.Validator;
import org.learn.jpa_playground.dto.MemberDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ValidatorConfig implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberDTO dto = (MemberDTO) target;

        if (dto.getUserId() != null && !dto.getUserId().isBlank()) {
            if(!dto.getUserId().matches("^[a-zA-Z0-9]+$")) {
                errors.rejectValue("userId", "userId.invalid");
            }
        }

        if (dto.getUserName() != null && !dto.getUserName().isBlank()) {
            if(!dto.getUserName().matches("^[가-힣a-zA-Z]+$")) {
                errors.rejectValue("userName", "userName.invalid");
            }
        }
    }
}