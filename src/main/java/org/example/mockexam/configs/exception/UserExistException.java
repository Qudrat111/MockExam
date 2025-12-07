package org.example.mockexam.configs.exception;

import org.example.mockexam.enums.ErrorCode;

public final class UserExistException extends MockExamException {
    @Override
    ErrorCode errorCode() {
        return ErrorCode.USER_EXIST;
    }
}
