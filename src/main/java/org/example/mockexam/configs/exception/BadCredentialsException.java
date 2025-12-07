package org.example.mockexam.configs.exception;

import org.example.mockexam.enums.ErrorCode;

public final class BadCredentialsException extends MockExamException {
    @Override
    ErrorCode errorCode() {
        return ErrorCode.BAD_CREDENTIALS;
    }
}
