package org.example.mockexam.configs.exception;

import org.example.mockexam.enums.ErrorCode;

public final class ListeningTestInactiveException extends MockExamException {
    @Override
    ErrorCode errorCode() {
        return ErrorCode.LISTENING_TEST_INACTIVE;
    }
}
