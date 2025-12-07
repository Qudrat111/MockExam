package org.example.mockexam.configs.exception;

import org.example.mockexam.enums.ErrorCode;

public final class ReadingTestInactiveException extends MockExamException {
    @Override
    ErrorCode errorCode() {
        return ErrorCode.READING_TEST_INACTIVE;
    }
}
