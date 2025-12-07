package org.example.mockexam.configs.exception;

import org.example.mockexam.enums.ErrorCode;

public final class ReadingTestNotFoundException extends MockExamException {

    @Override
    ErrorCode errorCode() {
        return ErrorCode.READING_TEST_NOT_FOUND;
    }
}
