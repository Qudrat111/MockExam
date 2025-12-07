package org.example.mockexam.enums;

public enum ErrorCode {
    USER_NOT_FOUND(100),
    USER_EXIST(101),
    BAD_CREDENTIALS(102),
    USER_INACTIVE(103),
    TEST_NAME_EXIST(104),
    READING_TEST_NOT_FOUND(105),
    LISTENING_TEST_NOT_FOUND(106),
    WRITING_TEST_NOT_FOUND(107),
    LISTENING_TEST_INACTIVE(108),
    READING_TEST_INACTIVE(109),
    WRITING_TEST_INACTIVE(110);
    private final int code;

    ErrorCode(Integer code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
