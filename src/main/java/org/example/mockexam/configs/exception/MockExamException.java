package org.example.mockexam.configs.exception;

import org.example.mockexam.enums.ErrorCode;
import org.example.mockexam.modules.dto.BaseMessage;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

public abstract sealed class MockExamException extends RuntimeException permits BadCredentialsException, ListeningTestInactiveException, ListeningTestNotFoundException, ReadingTestInactiveException, ReadingTestNotFoundException, TestNameExistException, UserExistException, UserInactiveException, UserNotFoundException, WritingTestInactiveException, WritingTestNotFoundException {
    abstract ErrorCode errorCode();

    Object[] getErrorMessageArguments() {
        return null;
    }

    BaseMessage getErrorMessage(ResourceBundleMessageSource errorMessageSource) {
        String errorMessage;

        try {
            errorMessage = errorMessageSource.getMessage(
                    errorCode().name(),
                    getErrorMessageArguments(),
                    LocaleContextHolder.getLocale()
            );
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        return new BaseMessage(errorCode().getCode(), errorMessage);
    }
}
