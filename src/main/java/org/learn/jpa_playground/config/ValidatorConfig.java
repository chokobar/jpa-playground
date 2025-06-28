package org.learn.jpa_playground.config;

import org.learn.jpa_playground.dto.MemberDTO;
import org.learn.jpa_playground.form.MemberSaveForm;
import org.learn.jpa_playground.form.MemberUpdateForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidatorConfig implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberSaveForm.class.isAssignableFrom(clazz)
                || MemberUpdateForm.class.isAssignableFrom(clazz)
                || MemberDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof MemberSaveForm saveForm) {
            validateUserId(saveForm.getUserId(), errors);
            validateUserName(saveForm.getUserName(), errors);
        } else if (target instanceof MemberUpdateForm updateForm) {
            validateUserId(updateForm.getUserId(), errors);
            validateUserName(updateForm.getUserName(), errors);
        }
    }

    private void validateUserId(String userId, Errors errors) {
        if (userId != null && !userId.isBlank()) {
            if (!userId.matches("^[a-zA-Z0-9]+$")) {
                errors.rejectValue("userId", "userId.invalid");
            }
        }
    }

    private void validateUserName(String userName, Errors errors) {
        if (userName != null && !userName.isBlank()) {
            if (!userName.matches("^[가-힣a-zA-Z]+$")) {
                errors.rejectValue("userName", "userName.invalid");
            }
        }
    }
}
