package org.learn.jpa_playground.config;

import org.springframework.validation.Validator;
import org.learn.jpa_playground.dto.MemberDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class MemberDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberDTO dto = (MemberDTO) target;

        if (dto.getUserId() != null && !dto.getUserId().matches("^[a-zA-Z0-9]+$")) {
            errors.rejectValue("userId", "userId.invalid",
                    "아이디는 영문자 또는 숫자만 입력 가능합니다.");
        }

        if (dto.getUserName() != null && !dto.getUserName().matches("^[가-힣a-zA-Z]+$")) {
            errors.rejectValue("userName", "userName.invalid",
                    "이름은 한글 또는 영문만 입력할 수 있습니다.");
        }
    }
}