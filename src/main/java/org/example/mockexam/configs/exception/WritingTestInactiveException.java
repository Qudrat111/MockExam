package org.example.mockexam.configs.exception;

import org.example.mockexam.enums.ErrorCode;

public final class WritingTestInactiveException extends MockExamException {
    @Override
    ErrorCode errorCode() {
        return ErrorCode.WRITING_TEST_INACTIVE;
    }
}
