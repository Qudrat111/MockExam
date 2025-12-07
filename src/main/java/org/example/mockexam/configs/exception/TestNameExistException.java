package org.example.mockexam.configs.exception;

import org.example.mockexam.enums.ErrorCode;

public final class TestNameExistException extends MockExamException{
    @Override
    ErrorCode errorCode() {
        return ErrorCode.TEST_NAME_EXIST;
    }
}
