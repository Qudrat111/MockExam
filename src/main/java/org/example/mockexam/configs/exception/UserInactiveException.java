package org.example.mockexam.configs.exception;

import org.example.mockexam.enums.ErrorCode;

public final class UserInactiveException extends MockExamException {
    @Override
    ErrorCode errorCode() {
        return ErrorCode.USER_INACTIVE;
    }
}
